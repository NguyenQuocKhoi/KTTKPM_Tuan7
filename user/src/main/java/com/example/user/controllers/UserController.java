package com.example.user.controllers;


import com.example.user.dto.ProductDTO;
import com.example.user.dto.UserDTO;
import com.example.user.models.User;
import com.example.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v2")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAll() {
//        List<User> users = userRepository.findAll();
//        return ResponseEntity.ok(users);
//    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getApi() {
        RestTemplate restTemplate = new RestTemplate();

        String request = "http://localhost:8081/api/v1/products";
        ProductDTO[] productDTOS = restTemplate.getForObject(request, ProductDTO[].class);
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (int i = 1; i < Objects.requireNonNull(productDTOS).length; i++) {
            ProductDTO productDTO = productDTOS[i];
            User user = users.get(i);
            UserDTO userDTO = UserDTO.builder().
                    userId(user.getId())
                    .productId(productDTO.getId())
                    .namePerson(user.getNamePerson())
                    .name(productDTO.getName())
                    .price(productDTO.getPrice()).build();

            userDTOS.add(userDTO);
        }
        return ResponseEntity.ok(userDTOS);
    }

}
