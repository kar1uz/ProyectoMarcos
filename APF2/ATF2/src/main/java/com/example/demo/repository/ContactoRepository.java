package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
}