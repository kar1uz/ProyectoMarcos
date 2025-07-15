package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Plan;
import com.example.demo.repository.PlanRepository;

@RestController
@RequestMapping("/api")
public class PlanRestController {

    @Autowired
    private PlanRepository planRepository;

    @GetMapping("/planes")
    public List<Plan> getAllPlanes() {
        return planRepository.findAll();
    }
}