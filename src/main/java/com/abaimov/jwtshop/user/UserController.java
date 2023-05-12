package com.abaimov.jwtshop.user;

import com.abaimov.jwtshop.cart.Order;
import com.abaimov.jwtshop.dto.UserCredentialsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserController(UserService userService, UserRepository userRepository, PasswordEncoder encoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @GetMapping
    public String helloUser() {
        return "User access";
    }

    @PatchMapping("/edit")
    public ResponseEntity<?> editUserCredentials(@RequestHeader("Authorization") String token, @RequestBody UserCredentialsDTO credentialsDTO) {
        User user = userService.getUserFromToken(token.substring(7));
        if (credentialsDTO.getFirstname() != null) {
            user.setFirstname(credentialsDTO.getFirstname());
        }
        if (credentialsDTO.getLastname() != null) {
            user.setLastname(credentialsDTO.getLastname());
        }
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/orders")
    public ResponseEntity<Set<Order>> getOrders(@RequestHeader("Authorization") String token){
        User user = userService.getUserFromToken(token.substring(7));
        return ResponseEntity.ok(user.getOrder());
    }
}
