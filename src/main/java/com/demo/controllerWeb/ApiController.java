package com.demo.controllerWeb;

import com.demo.DTO.UserDTO;
import com.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/data")
    public ResponseEntity<Map<String, String>> getData(Principal principal) {
        String username = principal.getName();
        Map<String, String> response = Map.of(
                "message", "Данные для пользователя: " + username,
                "status", "OK"
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/public")
    public ResponseEntity<Map<String, String>> publicApi() {
        return ResponseEntity.ok(Map.of("message", "Это публичный API"));
    }


}
