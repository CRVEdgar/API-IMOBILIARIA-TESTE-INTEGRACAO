package com.example.imobiliaria.domain.repository;

import com.example.imobiliaria.domain.model.Alugueis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlugueisRepository extends JpaRepository<Alugueis, Long> {
}
