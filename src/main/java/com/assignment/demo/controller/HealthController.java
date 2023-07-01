package com.assignment.demo.controller;

import com.assignment.demo.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/health")
public class HealthController {
    @Autowired
    private HealthService healthService;
    @GetMapping(value = "/version")
    public String getHealth (){
        return healthService.getHealth();
    }

}
