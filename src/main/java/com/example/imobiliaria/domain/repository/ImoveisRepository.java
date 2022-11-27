package com.example.imobiliaria.domain.repository;

import com.example.imobiliaria.domain.model.Imoveis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImoveisRepository extends JpaRepository<Imoveis, Long> {
}
