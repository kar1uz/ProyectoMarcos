package com.example.demo.repository;

import com.example.demo.modelo.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
    @Query("SELECT c FROM Contrato c JOIN FETCH c.usuario u JOIN FETCH c.plan p ORDER BY c.idContrato ASC")
    List<Contrato> findAllWithUsuarioAndPlan();
}