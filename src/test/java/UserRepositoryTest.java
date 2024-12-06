package org.example;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RentalApplication.class)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindUser() {
        // Arrange: создаем пользователей
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@example.com");
        user1.setUsername("johndoe");
        user1.setPassword("password123");
        user1.setRole("USER");

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setLastName("Smith");
        user2.setEmail("jane.smith@example.com");
        user2.setUsername("janesmith");
        user2.setPassword("password456");
        user2.setRole("ADMIN");

        // Сохраняем пользователей
        userRepository.save(user1);
        userRepository.save(user2);

        // Act: находим всех пользователей
        List<User> users = userRepository.findAll();

        // Assert: проверяем, что количество пользователей совпадает с ожидаемым
        assertEquals(13, users.size(), "Expected 13 users to be found");


    }
}

