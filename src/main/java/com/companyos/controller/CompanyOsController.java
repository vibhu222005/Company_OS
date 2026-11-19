package com.companyos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyOsController {

    @GetMapping({"/api/health", "/hello"})
    public String health() {
        return "CompanyOS is running";
    }
}
