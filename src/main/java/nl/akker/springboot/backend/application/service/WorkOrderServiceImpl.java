package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.ApiRequestException;
import nl.akker.springboot.backend.application.exceptions.NotFoundException;
import nl.akker.springboot.backend.application.model.dbmodels.Additional;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Activity;
import nl.akker.springboot.backend.application.model.dbmodels.Car;
import nl.akker.springboot.backend.application.model.dbmodels.Component;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.model.enums.EWorkOrderStatus;
import nl.akker.springboot.backend.application.repository.ActivityRepository;
import nl.akker.springboot.backend.application.repository.CarRepository;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
import nl.akker.springboot.backend.application.repository.WorkOrderRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkOrderServiceImpl implements WorkOrderService {

    private final WorkOrderRepository workOrderRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ActivityRepository activityRepository;

    @Override
    public Collection<WorkOrder> getWorkOrders() {
        return workOrderRepository.findAll();
    }

    @Override
    public WorkOrder getWorkOrderById(Long id) {
        return workOrderRepository.findById(id).orElseThrow(() -> new NotFoundException("Work order with id " + id + " not found"));
    }

    @Override
    public WorkOrder getWorkOrderByWorkOrderNumber(Long workOrderNumber) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrderNumber)) {
            throw new NotFoundException("The specified work order number " + workOrderNumber + " has not been found");
        }
        return workOrderRepository.getWorkOrderByWorkOrderNumber(workOrderNumber);
    }

    @Override
    public ReturnObject createWorkOrder(WorkOrder workOrder) {
        if (workOrderRepository.existsByCarLicensePlate(workOrder.getCar().getLicensePlate())) {
            throw new ApiRequestException("A work order for the specified license plate already exist.");
        } else if (!customerRepository.existsByCarsLicensePlate(workOrder.getCar().getLicensePlate())) {
            throw new ApiRequestException("Not possible to make a work order for a car without an owner. Make sure the specified license plate has a customer attached to it.");
        } else {

            ReturnObject returnObject = new ReturnObject();
            Car car = carRepository.findCarByLicensePlate(workOrder.getCar().getLicensePlate());
            WorkOrder createWorkOrder = workOrder;

            if (car.getCreated() == null) {
                car.setCreated(LocalDateTime.now());
                car.setModified(LocalDateTime.now());
            }

            // Get latest work order number from database
            WorkOrder latestWorkOrder = workOrderRepository.findTopByOrderByCreatedDesc();
            // increment work order number
            createWorkOrder.setWorkOrderNumber(latestWorkOrder.getWorkOrderNumber() + 1);

            // Get latest invoice number from database
            WorkOrder latestInvoiceNumber = workOrderRepository.findTopByOrderByCreatedDesc();
            // increment invoice number
            createWorkOrder.setInvoiceNumber(latestInvoiceNumber.getInvoiceNumber() + 1);

            createWorkOrder.setCar(car);
            createWorkOrder.setStatus(EWorkOrderStatus.APPOINTMENT_FOR_INSPECTION);
            createWorkOrder.setAppointmentDate(workOrder.getAppointmentDate());
            createWorkOrder.setCreated(LocalDateTime.now());
            createWorkOrder.setModified(LocalDateTime.now());
            workOrderRepository.save(createWorkOrder);

            returnObject.setObject(createWorkOrder);
            returnObject.setMessage("Work order has been created");

            return returnObject;
        }
    }

    @Override
    public String carCheckIn(Long workOrderNumber) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrderNumber)) {
            throw new NotFoundException("The specified work order number " + workOrderNumber + " has not been found");
        }

        WorkOrder updateWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrderNumber);

        Activity carInspection = activityRepository.findByActivityNumber(1);
        List<Activity> activities = new ArrayList<>();
        activities.add(carInspection);

        updateWorkOrder.setStatus(EWorkOrderStatus.INSPECTION);
        updateWorkOrder.setActivities(activities);
        updateWorkOrder.setModified(LocalDateTime.now());
        workOrderRepository.save(updateWorkOrder);

        return "The work order status has been updated to: INSPECTION";
    }

    @Override
    public String customerAgreed(Long workOrderNumber) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrderNumber)) {
            throw new NotFoundException("The specified work order number " + workOrderNumber + " has not been found");
        }

        WorkOrder updateWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrderNumber);

        updateWorkOrder.setStatus(EWorkOrderStatus.IN_REPAIR); // Customer agreed to repair costs
        updateWorkOrder.setAppointmentDate(updateWorkOrder.getAppointmentDate()); // The date the repair takes place
        updateWorkOrder.setModified(LocalDateTime.now());
        workOrderRepository.save(updateWorkOrder);

        return "Work order status has been updated to: IN_REPAIR";
    }

    @Override
    public String customerDeclined(Long workOrderNumber) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrderNumber)) {
            throw new NotFoundException("The specified work order number " + workOrderNumber + " has not been found");
        }

        WorkOrder updateWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrderNumber);

        // generate invoice
        generateInvoice(updateWorkOrder);

        // Customer declined to repair costs and will be invoiced for inspection activity
        updateWorkOrder.setStatus(EWorkOrderStatus.CUSTOMER_DECLINED);
        updateWorkOrder.setAppointmentDate(null);
        updateWorkOrder.setModified(LocalDateTime.now());
        workOrderRepository.save(updateWorkOrder);

        return "Work order status has been updated to: CUSTOMER_DECLINED";
    }

    @Override
    public ReturnObject finishedRepair(WorkOrder workOrder) {
        if (!workOrderRepository.existsByWorkOrderNumber(workOrder.getWorkOrderNumber())) {
            throw new NotFoundException("The specified work order number " + workOrder.getWorkOrderNumber() + " has not been found");
        }

        ReturnObject returnObject = new ReturnObject();
        WorkOrder updateWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrder.getWorkOrderNumber());

        // generate invoice
        generateInvoice(updateWorkOrder);

        // Repair is finished and will be invoiced for the inspection + components + labor
        updateWorkOrder.setStatus(EWorkOrderStatus.INVOICED);
        updateWorkOrder.setAppointmentDate(null);
        updateWorkOrder.setModified(LocalDateTime.now());
        workOrderRepository.save(updateWorkOrder);

        returnObject.setObject(updateWorkOrder);
        returnObject.setMessage("Car repair finished. Work order status has been updated to: INVOICED. Invoice is saved at location: src/main/resources/static/carpapers/");

        return returnObject;
    }


    public void generateInvoice(WorkOrder workOrder) {

        if (workOrder != null) {
            WorkOrder updateWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrder.getWorkOrderNumber());

            // create pdf file and populate it with work order data.
            try {
                PDDocument document = new PDDocument();
                PDPage page = new PDPage();
                document.addPage(page);

                PDPageContentStream contentStream = new PDPageContentStream(document, page);

                contentStream.setFont(PDType1Font.TIMES_ROMAN, 16);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(25, 750);

                double costs = 0;
                String invoiceNumber = "invoice number: " + updateWorkOrder.getInvoiceNumber();
                String customer = "customer: " + updateWorkOrder.getCar().getCustomer().getFirstname() + " "
                        + updateWorkOrder.getCar().getCustomer().getLastname();
                String car = "car: " + updateWorkOrder.getCar().getLicensePlate();

                contentStream.showText("date: " + LocalDate.now());
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText(invoiceNumber);
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText(customer);
                contentStream.newLine();
                contentStream.showText(car);
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();

                contentStream.showText("ACTIVITIES");
                contentStream.newLine();
                for (Activity activity : updateWorkOrder.getActivities()) {
                    String activities = activity.getDescription() + " ----- price: " + activity.getPrice();
                    costs = costs + activity.getPrice();
                    contentStream.showText(activities);
                    contentStream.newLine();
                }

                contentStream.newLine();
                contentStream.newLine();

                contentStream.showText("COMPONENTS");
                contentStream.newLine();
                for (Component component : updateWorkOrder.getComponents()) {
                    String components = component.getAmount() + " x " + component.getDescription() + " ----- price: " + component.getPrice();
                    costs = (costs + (component.getPrice() * component.getAmount()));
                    contentStream.showText(components);
                    contentStream.newLine();
                }

                if (updateWorkOrder.getAdditionals() != null) {
                    contentStream.newLine();
                    contentStream.newLine();

                    contentStream.showText("ADDITIONALS");
                    contentStream.newLine();
                    for (Additional additional : updateWorkOrder.getAdditionals()) {
                        String additionals = additional.getAmount() + " x " + additional.getDescription() + " ----- price: " + additional.getPrice();
                        costs = (costs + (additional.getPrice() * additional.getAmount()));
                        contentStream.showText(additionals);
                        contentStream.newLine();
                    }
                }

                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("price: " + costs);
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("tax: 21%");
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("total: " + (costs + (costs * 0.21)));

                contentStream.endText();
                contentStream.close();

                String folder = "src/main/resources/static/invoices/";
                document.save(folder + "invoice_" + updateWorkOrder.getInvoiceNumber() + ".pdf");
                document.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public ReturnObject payInvoice(WorkOrder workOrder) {
        ReturnObject returnObject = new ReturnObject();

        if (workOrder != null) {
            WorkOrder payWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrder.getWorkOrderNumber());

            payWorkOrder.setStatus(EWorkOrderStatus.PAID);
            payWorkOrder.setModified(LocalDateTime.now());
            workOrderRepository.save(payWorkOrder);

            returnObject.setObject(payWorkOrder);
            returnObject.setMessage("Invoice has been paid by customer " + payWorkOrder.getCar().getCustomer().getFirstname() +
                    " " + payWorkOrder.getCar().getCustomer().getLastname());

            return returnObject;
        }
        returnObject.setMessage("The specified work order does not exist");

        return returnObject;
    }

    @Override
    public void deleteWorkOrder(Long id) {
        if (!workOrderRepository.existsById(id)) {
            throw new ApiRequestException("Work order with id " + id + " has not been found");
        }
        workOrderRepository.deleteById(id);
    }
}

