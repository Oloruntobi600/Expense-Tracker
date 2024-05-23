package com.expensetracker.Controller;

import com.expensetracker.Model.User;
import com.expensetracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/api/users")
    public class UserController {

        @Autowired
        private UserService userService;

//        @PostMapping("/register")
//        public ResponseEntity<User> registerUser(@RequestBody User user) {
//            User newUser = userService.registerUser(user);
//            return ResponseEntity.ok(newUser);
//        }
         @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            System.out.println("Received request to register user: " + user.getUsername());
            userService.saveUser(user);
            System.out.println("User registered successfully: " + user.getUsername());
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            Optional<User> user = userService.getUserById(id);
            return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PutMapping("/{id}")
        public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
            Optional<User> optionalUser = userService.getUserById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setUsername(userDetails.getUsername());
                user.setEmail(userDetails.getEmail());
                user.setPassword(userDetails.getPassword());
                User updatedUser = userService.updateUser(user);
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
            Optional<User> optionalUser = userService.getUserById(id);
            if (optionalUser.isPresent()) {
                userService.deleteUser(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }

}
