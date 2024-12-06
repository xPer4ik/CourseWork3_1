import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.model.Vehicle;
import org.example.repository.VehicleRepository;
import org.example.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    public void testSaveVehicle() {
        // Создаем объект транспортного средства
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Test Vehicle");
        vehicle.setType("SUV");

        // Мокаем поведение репозитория
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        // Вызываем метод сервиса
        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);

        // Проверяем, что транспортное средство сохранено
        assertEquals("Test Vehicle", savedVehicle.getName());
        assertEquals("SUV", savedVehicle.getType());

        // Проверяем, что метод save был вызван один раз
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    public void testGetAllVehicles() {
        // Создаем список транспортных средств
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setName("Car1");
        vehicle1.setType("Sedan");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setName("Car2");
        vehicle2.setType("SUV");

        List<Vehicle> vehicles = Arrays.asList(vehicle1, vehicle2);

        // Мокаем поведение репозитория
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        // Вызываем метод сервиса
        List<Vehicle> result = vehicleService.getAllVehicles();

        // Проверяем, что список содержит два элемента
        assertEquals(2, result.size());
        assertEquals("Car1", result.get(0).getName());
        assertEquals("Car2", result.get(1).getName());

        // Проверяем, что метод findAll был вызван один раз
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteVehicle() {
        // Мокаем поведение репозитория
        doNothing().when(vehicleRepository).deleteById(1L);

        // Вызываем метод сервиса
        vehicleService.deleteVehicle(1L);

        // Проверяем, что метод deleteById был вызван один раз
        verify(vehicleRepository, times(1)).deleteById(1L);
    }
}
