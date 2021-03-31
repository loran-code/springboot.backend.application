package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.dbmodels.Car;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;
import nl.akker.springboot.backend.application.model.enums.EWorkOrderStatus;
import nl.akker.springboot.backend.application.repository.WorkOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@ContextConfiguration(classes = {WorkOrder.class})
class WorkOrderServiceImplTest {

    @MockBean
    private WorkOrderRepository underTestRepository;

    @Mock
    Car car;

    @BeforeEach
    public void setUp() {
        WorkOrder workOrder1 = new WorkOrder(11L, EWorkOrderStatus.APPOINTMENT_FOR_INSPECTION, LocalDateTime.now(), 111L, car);
        WorkOrder workOrder2 = new WorkOrder(12L, EWorkOrderStatus.INSPECTION, LocalDateTime.now(), 112L, car);
        WorkOrder workOrder3 = new WorkOrder(13L, EWorkOrderStatus.CUSTOMER_DECLINED, LocalDateTime.now(), 113L, car);
        WorkOrder workOrder4 = new WorkOrder(14L, EWorkOrderStatus.IN_REPAIR, LocalDateTime.now(), 114L, car);
        WorkOrder workOrder5 = new WorkOrder(15L, EWorkOrderStatus.INVOICED, LocalDateTime.now(), 115L, car);
        WorkOrder workOrder6 = new WorkOrder(16L, EWorkOrderStatus.PAID, LocalDateTime.now(), 116L, car);

        underTestRepository.save(workOrder1);
        underTestRepository.save(workOrder2);
        underTestRepository.save(workOrder3);
        underTestRepository.save(workOrder4);
        underTestRepository.save(workOrder5);
        underTestRepository.save(workOrder5);

        Mockito.when(underTestRepository.findWorkOrderByStatus(workOrder1.getStatus())).thenReturn(workOrder1);
        Mockito.when(underTestRepository.findByWorkOrderNumber(workOrder1.getWorkOrderNumber())).thenReturn(workOrder1);
        Mockito.when(underTestRepository.findByInvoiceNumber(workOrder1.getInvoiceNumber())).thenReturn(workOrder1);

        Mockito.when(underTestRepository.findWorkOrderByStatus(workOrder2.getStatus())).thenReturn(workOrder2);
        Mockito.when(underTestRepository.findWorkOrderByStatus(workOrder3.getStatus())).thenReturn(workOrder3);
        Mockito.when(underTestRepository.findWorkOrderByStatus(workOrder4.getStatus())).thenReturn(workOrder4);
        Mockito.when(underTestRepository.findWorkOrderByStatus(workOrder5.getStatus())).thenReturn(workOrder5);
        Mockito.when(underTestRepository.findWorkOrderByStatus(workOrder6.getStatus())).thenReturn(workOrder6);
    }

    @Test
    void itShouldGetWorkOrderByWorkOrderNumber() {
        //Given
        long expected = 11L;

        //When
        WorkOrder found = underTestRepository.findByWorkOrderNumber(11L);

        //Then
        assertEquals(expected, found.getWorkOrderNumber());
    }

    @Test
    void itShouldGetWorkOrderByInvoiceNumber() {
        //Given
        long expected = 11L;

        //When
        WorkOrder found = underTestRepository.findByInvoiceNumber(111L);

        //Then
        assertEquals(expected, found.getWorkOrderNumber());
    }

    @Test
    void itShouldCreateWorkOrder() {
        //Given
        long expectedWorkOrderNumber = 11L;
        long expectedInvoiceNumber = 111L;
        EWorkOrderStatus expectedStatus = EWorkOrderStatus.APPOINTMENT_FOR_INSPECTION;
        Car expectedCar = car;

        //When
        WorkOrder found = underTestRepository.findByWorkOrderNumber(11L);

        //Then
        assertEquals(expectedWorkOrderNumber, found.getWorkOrderNumber());
        assertEquals(expectedInvoiceNumber, found.getInvoiceNumber());
        assertEquals(expectedStatus, found.getStatus());
        assertEquals(expectedCar, found.getCar());
    }

    @Test
    void itShouldCarCheckIn() {
        //Given
        EWorkOrderStatus expected = EWorkOrderStatus.INSPECTION;

        //When
        WorkOrder found = underTestRepository.findWorkOrderByStatus(EWorkOrderStatus.INSPECTION);

        //Then
        assertEquals(expected, found.getStatus());
    }

    @Test
    void itShouldCustomerAgreed() {
        //Given
        EWorkOrderStatus expected = EWorkOrderStatus.IN_REPAIR;

        //When
        WorkOrder found = underTestRepository.findWorkOrderByStatus(EWorkOrderStatus.IN_REPAIR);

        //Then
        assertEquals(expected, found.getStatus());
    }

    @Test
    void itShouldCustomerDeclined() {
        //Given
        EWorkOrderStatus expected = EWorkOrderStatus.CUSTOMER_DECLINED;

        //When
        WorkOrder found = underTestRepository.findWorkOrderByStatus(EWorkOrderStatus.CUSTOMER_DECLINED);

        //Then
        assertEquals(expected, found.getStatus());
    }

    @Test
    void itShouldFinishedRepair() {
        //Given
        EWorkOrderStatus expected = EWorkOrderStatus.INVOICED;

        //When
        WorkOrder found = underTestRepository.findWorkOrderByStatus(EWorkOrderStatus.INVOICED);

        //Then
        assertEquals(expected, found.getStatus());
    }

    @Test
    void itShouldPayInvoice() {
        //Given
        EWorkOrderStatus expected = EWorkOrderStatus.PAID;

        //When
        WorkOrder found = underTestRepository.findWorkOrderByStatus(EWorkOrderStatus.PAID);

        //Then
        assertEquals(expected, found.getStatus());
    }
}