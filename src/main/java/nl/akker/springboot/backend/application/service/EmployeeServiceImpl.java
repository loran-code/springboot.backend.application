package nl.akker.springboot.backend.application.service;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.exceptions.ApiRequestException;
import nl.akker.springboot.backend.application.exceptions.NotFoundException;
import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.Car;
import nl.akker.springboot.backend.application.model.dbmodels.Customer;
import nl.akker.springboot.backend.application.model.dbmodels.Employee;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.model.enums.EWorkOrderStatus;
import nl.akker.springboot.backend.application.repository.CarRepository;
import nl.akker.springboot.backend.application.repository.CustomerRepository;
import nl.akker.springboot.backend.application.repository.EmployeeRepository;
import nl.akker.springboot.backend.application.repository.WorkOrderRepository;
import org.hibernate.jdbc.Work;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final WorkOrderRepository workOrderRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    private final PasswordEncoder encoder;

    @Override
    public Collection<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee with id " + id + " not found"));
    }

    @Override
    public ReturnObject createEmployee(Employee employee) {
        if (employeeRepository.existsByUsername(employee.getUsername()) ||
                employeeRepository.existsByEmail(employee.getEmail())) {
            throw new ApiRequestException("The specified details are already taken. Make sure your username and email are unique");
        }
        ReturnObject returnObject = new ReturnObject();

        Employee createEmployee = employee;
        createEmployee.setPassword(encoder.encode(createEmployee.getPassword()));
        createEmployee.setCreated(LocalDateTime.now());
        createEmployee.setModified(LocalDateTime.now());
        employeeRepository.save(createEmployee);

        returnObject.setObject(createEmployee);
        returnObject.setMessage("Employee has been created");

        return returnObject;
    }

    @Override
    public ReturnObject updateEmployee(Long id, Employee employee) {
        if (!employeeRepository.existsById(id)) {
            throw new ApiRequestException("Employee with " + id + " has not been found thus can not be updated");
        }
        ReturnObject returnObject = new ReturnObject();

        Employee updateEmployee = employeeRepository.findById(id).orElse(null);
        updateEmployee.setEmail(employee.getEmail());
        updateEmployee.setUsername(employee.getUsername());
        updateEmployee.setPassword(encoder.encode(updateEmployee.getPassword()));
        updateEmployee.setModified(LocalDateTime.now());

        if (employeeRepository.existsByUsername(employee.getUsername()) ||
                employeeRepository.existsByEmail(employee.getEmail())) {
            throw new ApiRequestException("The specified details are already taken. Make sure your username and email are unique");
        } else {
            employeeRepository.save(updateEmployee);
        }

        returnObject.setObject(updateEmployee);
        returnObject.setMessage("Employee has been updated");

        return returnObject;
    }

    @Override
    public ReturnObject partialUpdateEmployee(Long id, Map<String, String> fields) {
        if (!employeeRepository.existsById(id)) {
            throw new ApiRequestException("Customer with id " + id + " has not been found thus can not be updated");
        }
        ReturnObject returnObject = new ReturnObject();
        Employee updateEmployee = employeeRepository.findById(id).orElse(null);
        for (String field : fields.keySet()) {
            switch (field) {
                case "email" -> updateEmployee.setEmail(fields.get(field));
                case "username" -> updateEmployee.setUsername(fields.get(field));
                case "password" -> updateEmployee.setPassword(encoder.encode(fields.get(field)));
            }
        }
        updateEmployee.setModified(LocalDateTime.now());
        employeeRepository.save(updateEmployee);

        returnObject.setMessage("The employee's details have been updated");
        returnObject.setObject(updateEmployee);

        return returnObject;
    }

    public List<WorkOrder> getWorkOrdersByStatus(EWorkOrderStatus eWorkOrderStatus) {
        return workOrderRepository.findByStatus(eWorkOrderStatus);
    }

    @Override
    public ReturnObject callCustomers() {
        ReturnObject returnObject = new ReturnObject();
        List<WorkOrder> workorders = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        workorders.addAll(getWorkOrdersByStatus(EWorkOrderStatus.CUSTOMER_DECLINED));
        workorders.addAll(getWorkOrdersByStatus(EWorkOrderStatus.INVOICED));

        for (WorkOrder workOrder : workorders) {
            Customer customer = customerRepository.findById(workOrder.getCar().getCustomer().getId()).orElse(null);

            if(customer == null){
                returnObject.setMessage("No customer found for work order: " + workOrder.getWorkOrderNumber());
                return returnObject;
            }
            else{
                customers.add(customer);
            }
        }

        if ((long) workorders.size() != 0) {
            returnObject.setMessage("The following customers need to be called to inform about their invoice.");
            returnObject.setObject(customers);
            return returnObject;
        }
        returnObject.setMessage("Could not find any work orders with status \"declined\" or \"invoiced\".");
        return returnObject;
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ApiRequestException("Employee with id " + id + " has not been found");
        }
        employeeRepository.deleteById(id);
    }
}

