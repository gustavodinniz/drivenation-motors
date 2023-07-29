package br.com.drivenation.motors.service.impl;

import br.com.drivenation.motors.dto.request.CreateCustomerAddressRequest;
import br.com.drivenation.motors.dto.request.CreateCustomerRequest;
import br.com.drivenation.motors.entity.CustomerEntity;
import br.com.drivenation.motors.enumeration.CustomerStatus;
import br.com.drivenation.motors.exception.ConflictException;
import br.com.drivenation.motors.repository.CustomerRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusTest
class CustomerServiceImplTest {

    @Inject
    CustomerServiceImpl customerService;

    @InjectMock
    CustomerRepository customerRepository;

    private CreateCustomerRequest createCustomerRequest;

    @Test
    void shouldCreateCustomerSuccessfully() {
        givenCreateCustomerRequest();
        givenCustomerRepositoryPersist();
        whenCreateCustomerCalled();
        thenExpectCustomerRepositoryPersistCalledOnce();
    }

    @Test
    void shouldNotCreateCustomerWhenDocumentAlreadyExists() {
        givenCreateCustomerRequest();
        givenCustomerRepositoryFindByDocumentReturnsOptionalOfCustomerEntity();
        whenCreateCustomerThrowsConflictException();
        thenExpectCustomerRepositoryFindByDocumentCalledOnce();
        thenExpectCustomerRepositoryPersistNotCalled();
    }


    // given

    private void givenCustomerRepositoryFindByDocumentReturnsOptionalOfCustomerEntity() {
        doReturn(Optional.of(CustomerEntity.builder().build()))
                .when(customerRepository)
                .findByDocument(anyString());
    }

    private void givenCustomerRepositoryPersist() {
        doNothing().when(customerRepository).persist(any(CustomerEntity.class));
    }

    private void givenCreateCustomerRequest() {
        createCustomerRequest = CreateCustomerRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .document("12345678910")
                .email("john.doe@example.com")
                .phoneNumber("+55 11 987654321")
                .dateOfBirth("1980-01-01")
                .address(CreateCustomerAddressRequest.builder()
                        .street("123 Fake Street")
                        .number("42")
                        .complement("Apartment 101")
                        .district("Faketown")
                        .city("Fakecity")
                        .state("FS")
                        .country("Fakecountry")
                        .zipCode("123456")
                        .build())
                .status(CustomerStatus.ACTIVE)
                .build();
    }

    // when

    private void whenCreateCustomerThrowsConflictException() {
        assertThrows(ConflictException.class, () -> customerService.createCustomer(createCustomerRequest));
    }

    private void whenCreateCustomerCalled() {
        customerService.createCustomer(createCustomerRequest);
    }

    // then

    private void thenExpectCustomerRepositoryPersistCalledOnce() {
        verify(customerRepository).persist(any(CustomerEntity.class));
    }

    private void thenExpectCustomerRepositoryPersistNotCalled() {
        verify(customerRepository, never()).persist(any(CustomerEntity.class));
    }

    private void thenExpectCustomerRepositoryFindByDocumentCalledOnce() {
        verify(customerRepository).findByDocument(anyString());
    }


}