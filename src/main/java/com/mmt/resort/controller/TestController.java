package com.mmt.resort.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/public")
    public String publicApi() {
        return "PUBLIC OK";
    }

    @GetMapping("/secure")
    public String secureApi() {
        return "SECURE OK";
    }
}

