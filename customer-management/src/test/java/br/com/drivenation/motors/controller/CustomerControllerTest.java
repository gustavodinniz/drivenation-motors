package br.com.drivenation.motors.controller;

import br.com.drivenation.motors.dto.request.CreateCustomerAddressRequest;
import br.com.drivenation.motors.dto.request.CreateCustomerRequest;
import br.com.drivenation.motors.enumeration.CustomerStatus;
import br.com.drivenation.motors.service.CustomerService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@QuarkusTest
class CustomerControllerTest {

    @InjectMock
    CustomerService customerService;

    private CreateCustomerRequest createCustomerRequest;

    @Test
    void shouldCreateCustomerSuccessfully() {
        givenCreateCustomerRequest();
        givenCustomerServiceCreateCustomer();
        whenCreateCustomerCalled();
        thenExpectCustomerServiceCreateCustomerCalledOnce();
    }

    // given

    private void givenCustomerServiceCreateCustomer() {
        doNothing().when(customerService).createCustomer(any(CreateCustomerRequest.class));
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
                .build();
    }

    // when

    private void whenCreateCustomerCalled() {
        given()
                .contentType(ContentType.JSON)
                .body(createCustomerRequest)
                .when()
                .post("/customers")
                .then()
                .statusCode(201);
    }

    // then

    private void thenExpectCustomerServiceCreateCustomerCalledOnce() {
        verify(customerService).createCustomer(any(CreateCustomerRequest.class));
    }

}