package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/remove")
    public ResponseEntity<String> remove(@RequestParam String input) {
        int length = input.length();

        if (length == 1) {
            return ResponseEntity.badRequest().build();
        } else if (length == 2) {
            return ResponseEntity.ok("");
        } else {
            String trimmedString = input.substring(1, length - 1);
            return ResponseEntity.ok(trimmedString);
        }
    }
}
