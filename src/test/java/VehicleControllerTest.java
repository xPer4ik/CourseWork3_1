import org.example.RentalApplication;
import org.example.controller.VehicleController;
import org.example.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.model.Vehicle;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static java.lang.reflect.Array.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = RentalApplication.class) // Указываем основной класс
@AutoConfigureMockMvc
public class VehicleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    private String vehicleName;

    @BeforeEach
    void setUp() {
        vehicleName = "Test Vehicle";
    }

    @Test
    @WithMockUser(username = "testName", roles = "ADMIN")
    void testAddVehicle_Success() throws Exception {
        // Выполняем POST-запрос с параметрами формы
        mockMvc.perform(post("/vehicles/add-vehicle2")
                        .param("name", "Test Vehicle")
                        .param("type", "SUV"))
                .andExpect(status().isFound()) // Ожидаем статус 302 (Found) для редиректа
                .andExpect(redirectedUrl("/booking")); // Ожидаем, что произойдет редирект на /booking


        Vehicle vehicle = new Vehicle();
        vehicle.setName("Test Vehicle");
        vehicle.setType("SUV");
        ArgumentCaptor<Vehicle> vehicleCaptor = ArgumentCaptor.forClass(Vehicle.class);
        verify(vehicleService).saveVehicle(vehicleCaptor.capture());
        Vehicle capturedVehicle = vehicleCaptor.getValue();
        assertEquals("Test Vehicle", capturedVehicle.getName());
        assertEquals("SUV", capturedVehicle.getType());


    }

    @Test
    @WithMockUser(username = "testName", roles = "USER")
    void testAddVehicleForUser() throws Exception {
        mockMvc.perform(post("/add-vehicle")
                        .param("name", "Test Vehicle")
                        .param("type", "SUV"))
                .andExpect(status().isForbidden());
    }

    @Test
    void testAddVehicleForUnauth() throws Exception {
        mockMvc.perform(post("/add-vehicle")
                        .param("name", "Test Vehicle")
                        .param("type", "SUV"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

}
