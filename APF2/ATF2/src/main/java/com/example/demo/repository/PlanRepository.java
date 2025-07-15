package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    List<Plan> findByActivoTrue();
}