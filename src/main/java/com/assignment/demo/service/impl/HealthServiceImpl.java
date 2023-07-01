package com.assignment.demo.service.impl;

import com.assignment.demo.service.HealthService;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl  implements HealthService {

    @Override
    public String getHealth() {
        return "Assignment Service  Version V1";
    }
}
