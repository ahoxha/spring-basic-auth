package org.hoxha.springbasicauth.controllers;

import org.hoxha.springbasicauth.model.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping
    String getInfo() {
        return "Hello from the other side!";
    }

    @PostMapping("/messages")
    ResponseEntity<Object> postMessage(@RequestBody Message message) {
        return ResponseEntity.ok().body(message);
    }
}
