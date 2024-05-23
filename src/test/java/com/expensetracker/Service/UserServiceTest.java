package com.expensetracker.Service;

import com.expensetracker.Model.User;
import com.expensetracker.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("John Doe");
        user.setEmail("john.doe@example.com");
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.registerUser(user));
    }
    @Test
    public void testGetUserById() {
        // Test getting a user by ID
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUsername("janedoe");
        user.setEmail("jane.doe@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Optional<User> retrievedUser = userService.getUserById(userId);
        assertTrue(retrievedUser.isPresent());
        assertEquals(userId, retrievedUser.get().getId());
        assertEquals("janedoe", retrievedUser.get().getUsername());
    }

    @Test
    public void testGetUserByUsername() {
        // Test getting a user by username
        String username = "janedoe";
        User user = new User();
        user.setUsername(username);
        user.setEmail("jane.doe@example.com");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        Optional<User> retrievedUser = userService.getUserByUsername(username);
        assertTrue(retrievedUser.isPresent());
        assertEquals(username, retrievedUser.get().getUsername());
    }

    @Test
    public void testUpdateUser() {
        // Test updating a user
        User user = new User();
        user.setId(1L);
        user.setUsername("janedoe");
        user.setEmail("jane.doe@example.com");

        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.updateUser(user);
        assertEquals(user, updatedUser);
    }

    @Test
    public void testDeleteUser() {
        // Test deleting a user
        Long userId = 1L;
        userService.deleteUser(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    // Additional tests
}
