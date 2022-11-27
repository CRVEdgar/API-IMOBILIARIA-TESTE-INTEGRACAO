package com.example.imobiliaria.domain.repository;

import com.example.imobiliaria.api.model.dto.LocacaoDTO;
import com.example.imobiliaria.domain.model.Locacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

    @Query("from Locacao l where l.ativo = true ")
    List<Locacao> alugueisAtivos();

    Optional<Locacao> findByAtivoAndId(boolean ativo, Long id);

    Page<Locacao> findByAtivo(boolean ativo, Pageable paginacao);

}
