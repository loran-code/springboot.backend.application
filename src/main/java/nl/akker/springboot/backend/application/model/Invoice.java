package nl.akker.springboot.backend.application.model;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.model.enums.EWorkOrderStatus;
import nl.akker.springboot.backend.application.repository.CarRepository;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
import nl.akker.springboot.backend.application.repository.WorkOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class Invoice {
//    https://www.baeldung.com/java-pdf-creation
//    https://stackoverflow.com/questions/44220739/invoice-generation-thymeleaf-spring-itextpdf
//    https://www.stackextend.com/java/generate-pdf-document-using-jasperreports-and-spring-boot/

    private final int BTW = 21;
//    private double price = 0;
    private final WorkOrderRepository workOrderRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;


    public void generateInvoice(Long workOrderNumber) {
        WorkOrder invoiceWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrderNumber);

        WorkOrder latestWorkOrder = workOrderRepository.findTopByOrderByCreatedDesc();
        invoiceWorkOrder.setInvoiceNumber(latestWorkOrder.getInvoiceNumber() + 1);

        invoiceWorkOrder.setStatus(EWorkOrderStatus.INVOICED);
        invoiceWorkOrder.setModified(LocalDateTime.now());
        workOrderRepository.save(invoiceWorkOrder);

    }


    public void payInvoice(Long workOrderNumber) {
        WorkOrder payWorkOrder = workOrderRepository.findByWorkOrderNumber(workOrderNumber);

        payWorkOrder.setStatus(EWorkOrderStatus.PAID);
        payWorkOrder.setModified(LocalDateTime.now());
        workOrderRepository.save(payWorkOrder);
    }
}
