package br.com.drivenation.motors.service;

import br.com.drivenation.motors.dto.request.CreateVehicleRequest;
import br.com.drivenation.motors.dto.request.UpdateVehicleRequest;
import br.com.drivenation.motors.entity.VehicleEntity;
import br.com.drivenation.motors.enums.VehicleStatus;
import br.com.drivenation.motors.repository.VehicleRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusTest
class VehicleServiceImplTest {

    @Inject
    VehicleServiceImpl vehicleService;

    @InjectMock
    VehicleRepository vehicleRepository;

    private CreateVehicleRequest createVehicleRequest;

    private UpdateVehicleRequest updateVehicleRequest;

    private ObjectId vehicleId;

    @Test
    void shouldCreateVehicleSuccessfully() {
        givenCreateVehicleRequest();
        givenVehicleRepositoryPersist();
        whenCreateVehicleCalled();
        thenExpectVehicleRepositoryPersistCalledOnce();
    }

    @Test
    void shouldGetAllVehiclesSuccessfully() {
        givenVehicleRepositoryFindAllReturnsListOfVehicleEntity();
        whenGetAllVehiclesCalled();
        thenExpectVehicleRepositoryFindAllCalledOnce();
    }

    @Test
    void shouldDeleteVehicleSuccessfully() {
        givenVehicleId();
        givenVehicleRepositoryDeleteById();
        whenDeleteVehicleCalled();
        thenExpectVehicleRepositoryDeleteByIdCalledOnce();
    }

    @Test
    void shouldUpdateVehicleSuccessfully() {
        givenVehicleId();
        givenUpdateVehicleRequest();
        givenVehicleRepositoryFindByIdReturnsVehicleEntity();
        givenVehicleRepositoryUpdate();
        whenUpdateVehicleCalled();
        thenExpectVehicleRepositoryFindByIdCalledOnce();
        thenExpectVehicleRepositoryUpdateCalledOnce();
    }

    @Test
    void shouldNotUpdateVehicleWhenVehicleNotFound() {
        givenVehicleId();
        givenUpdateVehicleRequest();
        givenVehicleRepositoryFindByIdReturnsNull();
        whenUpdateVehicleThrowsNotFoundException();
        thenExpectVehicleRepositoryFindByIdCalledOnce();
        thenExpectVehicleRepositoryUpdateNotCalled();
    }

    @Test
    void shouldGetVehicleByIdSuccessfully() {
        givenVehicleId();
        givenVehicleRepositoryFindByIdReturnsVehicleEntity();
        whenGetVehicleByIdCalled();
        thenExpectVehicleRepositoryFindByIdCalledOnce();
    }

    @Test
    void shouldNotGetVehicleByIdWhenVehicleNotFound() {
        givenVehicleId();
        givenVehicleRepositoryFindByIdReturnsNull();
        whenGetVehicleByIdThrowsNotFoundException();
        thenExpectVehicleRepositoryFindByIdCalledOnce();
    }


    // given

    private void givenVehicleRepositoryFindByIdReturnsNull() {
        doReturn(null).when(vehicleRepository).findById(any(ObjectId.class));
    }

    private void givenVehicleRepositoryUpdate() {
        doNothing().when(vehicleRepository).update(any(VehicleEntity.class));
    }

    private void givenVehicleRepositoryFindByIdReturnsVehicleEntity() {
        doReturn(new VehicleEntity()).when(vehicleRepository).findById(any(ObjectId.class));
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

    private void givenVehicleId() {
        vehicleId = new ObjectId("64c1d11f7846e2f23389e96e");
    }

    private void givenVehicleRepositoryDeleteById() {
        doReturn(true).when(vehicleRepository).deleteById(any(ObjectId.class));
    }

    private void givenVehicleRepositoryFindAllReturnsListOfVehicleEntity() {
        var panacheQuery = mock(PanacheQuery.class);
        doReturn(List.of(new VehicleEntity())).when(panacheQuery).list();
        doReturn(panacheQuery).when(vehicleRepository).findAll();
    }

    private void givenVehicleRepositoryPersist() {
        doNothing().when(vehicleRepository).persist(any(VehicleEntity.class));
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

    private void whenGetVehicleByIdThrowsNotFoundException() {
        assertThrows(NotFoundException.class, () -> vehicleService.getVehicleById(vehicleId));
    }

    private void whenGetVehicleByIdCalled() {
        vehicleService.getVehicleById(vehicleId);
    }

    private void whenUpdateVehicleThrowsNotFoundException() {
        assertThrows(NotFoundException.class, () -> vehicleService.updateVehicle(vehicleId, updateVehicleRequest));
    }

    private void whenUpdateVehicleCalled() {
        vehicleService.updateVehicle(vehicleId, updateVehicleRequest);
    }

    private void whenDeleteVehicleCalled() {
        vehicleService.deleteVehicle(vehicleId);
    }

    private void whenGetAllVehiclesCalled() {
        vehicleService.getAllVehicles();
    }

    private void whenCreateVehicleCalled() {
        vehicleService.createVehicle(createVehicleRequest);
    }

    // then

    private void thenExpectVehicleRepositoryDeleteByIdCalledOnce() {
        verify(vehicleRepository).deleteById(any(ObjectId.class));
    }

    private void thenExpectVehicleRepositoryPersistCalledOnce() {
        verify(vehicleRepository).persist(any(VehicleEntity.class));
    }

    private void thenExpectVehicleRepositoryFindAllCalledOnce() {
        verify(vehicleRepository).findAll();
    }

    private void thenExpectVehicleRepositoryUpdateCalledOnce() {
        verify(vehicleRepository).update(any(VehicleEntity.class));
    }

    private void thenExpectVehicleRepositoryFindByIdCalledOnce() {
        verify(vehicleRepository).findById(any(ObjectId.class));
    }

    private void thenExpectVehicleRepositoryUpdateNotCalled() {
        verify(vehicleRepository, never()).update(any(VehicleEntity.class));
    }

}