import static org.junit.jupiter.api.Assertions.*;

import org.example.RentalApplication;
import org.example.model.Vehicle;
import org.example.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = RentalApplication.class)  // Укажите главный класс приложения
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void testSaveVehicle() {
        // Создаем объект транспортного средства
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Test Vehicle");
        vehicle.setType("SUV");

        // Сохраняем транспортное средство в базу данных
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        // Проверяем, что транспортное средство сохранено
        assertNotNull(savedVehicle.getId());
        assertEquals("Test Vehicle", savedVehicle.getName());
        assertEquals("SUV", savedVehicle.getType());
    }

    @Test
    public void testFindVehicleById() {
        // Создаем и сохраняем объект транспортного средства
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Test Vehicle");
        vehicle.setType("SUV");
        vehicle = vehicleRepository.save(vehicle);

        // Ищем транспортное средство по ID
        Optional<Vehicle> foundVehicle = vehicleRepository.findById(vehicle.getId());

        // Проверяем, что транспортное средство найдено
        assertTrue(foundVehicle.isPresent());
        assertEquals("Test Vehicle", foundVehicle.get().getName());
        assertEquals("SUV", foundVehicle.get().getType());
    }



    @Test
    public void testDeleteVehicleById() {
        // Создаем и сохраняем объект транспортного средства
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Car1");
        vehicle.setType("Sedan");
        vehicle = vehicleRepository.save(vehicle);

        // Удаляем транспортное средство по ID
        vehicleRepository.deleteById(vehicle.getId());

        // Проверяем, что транспортное средство было удалено
        Optional<Vehicle> deletedVehicle = vehicleRepository.findById(vehicle.getId());
        assertFalse(deletedVehicle.isPresent());
    }
}
