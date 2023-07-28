package br.com.drivenation.motors.controller;

import br.com.drivenation.motors.dto.request.CreateVehicleRequest;
import br.com.drivenation.motors.dto.request.UpdateVehicleRequest;
import br.com.drivenation.motors.dto.response.GetAllVehicleResponse;
import br.com.drivenation.motors.dto.response.GetVehicleByIdResponse;
import br.com.drivenation.motors.dto.response.UpdateVehicleResponse;
import br.com.drivenation.motors.enums.VehicleStatus;
import br.com.drivenation.motors.service.VehicleService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.bson.types.ObjectId;
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

    private UpdateVehicleRequest updateVehicleRequest;

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

    @Test
    void shouldDeleteVehicleSuccessfully() {
        givenVehicleServiceDeleteVehicle();
        whenDeleteVehicleCalled();
        thenExpectVehicleServiceDeleteVehicleCalledOnce();
    }

    @Test
    void shouldUpdateVehicleSuccessfully() {
        givenUpdateVehicleRequest();
        givenVehicleServiceUpdateVehicle();
        whenUpdateVehicleCalled();
        thenExpectVehicleServiceUpdateVehicleCalledOnce();
    }

    @Test
    void shouldGetVehicleByIdSuccessfully() {
        givenVehicleServiceGetVehicleById();
        whenGetVehicleByIdCalled();
        thenExpectVehicleServiceGetVehicleByIdCalledOnce();
    }

    // given
    private void givenVehicleServiceGetVehicleById() {
        doReturn(GetVehicleByIdResponse.builder().build()).when(vehicleService).getVehicleById(any(ObjectId.class));
    }

    private void givenVehicleServiceUpdateVehicle() {
        doReturn(UpdateVehicleResponse.builder().build()).when(vehicleService).updateVehicle(any(ObjectId.class), any(UpdateVehicleRequest.class));
    }

    private void givenUpdateVehicleRequest() {
        updateVehicleRequest = UpdateVehicleRequest.builder()
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

    private void givenVehicleServiceDeleteVehicle() {
        doNothing().when(vehicleService).deleteVehicle(any(ObjectId.class));
    }

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

    // when

    private void whenGetVehicleByIdCalled() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", "64c1d11f7846e2f23389e96e")
                .when()
                .get("/vehicles/{id}")
                .then()
                .statusCode(200);
    }

    private void whenUpdateVehicleCalled() {
        given()
                .pathParam("id", "64c1d11f7846e2f23389e96e")
                .contentType(ContentType.JSON)
                .body(updateVehicleRequest)
                .when().put("/vehicles/{id}")
                .then()
                .statusCode(200);
    }

    private void whenDeleteVehicleCalled() {
        given()
                .pathParam("id", "64c1d11f7846e2f23389e96e")
                .when().delete("/vehicles/{id}")
                .then()
                .statusCode(204);
    }

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

    private void thenExpectVehicleServiceUpdateVehicleCalledOnce() {
        verify(vehicleService).updateVehicle(any(ObjectId.class), any(UpdateVehicleRequest.class));
    }

    private void thenExpectVehicleServiceDeleteVehicleCalledOnce() {
        verify(vehicleService).deleteVehicle(any(ObjectId.class));
    }

    private void thenExpectVehicleServiceGetAllVehiclesCalledOnce() {
        verify(vehicleService).getAllVehicles();
    }

    private void thenExpectVehicleServiceCreateVehicleCalledOnce() {
        verify(vehicleService).createVehicle(any(CreateVehicleRequest.class));
    }

    private void thenExpectVehicleServiceGetVehicleByIdCalledOnce() {
        verify(vehicleService).getVehicleById(any(ObjectId.class));
    }

}