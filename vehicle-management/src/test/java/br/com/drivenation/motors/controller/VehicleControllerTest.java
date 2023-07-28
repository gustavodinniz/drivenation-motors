package br.com.drivenation.motors.controller;

import br.com.drivenation.motors.dto.request.CreateVehicleRequest;
import br.com.drivenation.motors.dto.response.GetAllVehicleResponse;
import br.com.drivenation.motors.enums.VehicleStatus;
import br.com.drivenation.motors.service.VehicleService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusTest
class VehicleControllerTest {

    @InjectMock
    VehicleService vehicleService;

    private CreateVehicleRequest createVehicleRequest;

    @Test
    void shouldCreateVehicleSuccessfully() {
        givenCreateVehicleRequest();
        givenVehicleServiceCreateVehicle();
        whenCreateVehicleCalled();
        thenExpectVehicleServiceCreateVehicleCalledOnce();
    }

    @Test
    void shouldGetAllVehiclesSuccessfully() {
        givenVehicleServiceGetAllVehicles();
        whenGetAllVehiclesCalled();
        thenExpectVehicleServiceGetAllVehiclesCalledOnce();
    }


    // given

    private void givenVehicleServiceGetAllVehicles() {
        doReturn(List.of(GetAllVehicleResponse.builder().build())).when(vehicleService).getAllVehicles();
    }

    private void givenCreateVehicleRequest() {
        createVehicleRequest = CreateVehicleRequest.builder()
                .model("Uno")
                .year(2021)
                .color("Red")
                .manufacturer("Fiat")
                .chassisNumber("123456789")
                .price(10000.00)
                .status(VehicleStatus.UNDER_MAINTENANCE)
                .build();
    }

    private void givenVehicleServiceCreateVehicle() {
        doNothing().when(vehicleService).createVehicle(any(CreateVehicleRequest.class));
    }

    // when

    private void whenGetAllVehiclesCalled() {
        given()
                .when().get("/vehicles")
                .then()
                .statusCode(200);
    }

    private void whenCreateVehicleCalled() {
        given()
                .contentType(ContentType.JSON)
                .body(createVehicleRequest)
                .when()
                .post("/vehicles")
                .then()
                .statusCode(201);
    }

    // then

    private void thenExpectVehicleServiceGetAllVehiclesCalledOnce() {
        verify(vehicleService).getAllVehicles();
    }

    private void thenExpectVehicleServiceCreateVehicleCalledOnce() {
        verify(vehicleService).createVehicle(any(CreateVehicleRequest.class));
    }

}