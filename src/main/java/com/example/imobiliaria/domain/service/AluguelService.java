package com.example.imobiliaria.domain.service;


import com.example.imobiliaria.domain.exception.NegocioException;
import com.example.imobiliaria.domain.model.Locacao;
import com.example.imobiliaria.domain.repository.AlugueisRepository;
import com.example.imobiliaria.domain.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AluguelService {

    @Autowired
    private AlugueisRepository repository;

    @Autowired
    private LocacaoService locacaoService;

    @Autowired
    private LocacaoRepository locacaoRepository;

    public List<Locacao> findAll() {
        List<Locacao> alugueisList = locacaoRepository.alugueisAtivos();
        return alugueisList;
    }

    public Locacao findById(Long idLocacao){
        /**retorna o aluguel do id informado desde que esteja com com ativo==1*/
        return locacaoRepository.findByAtivoAndId(true,idLocacao)
                .orElseThrow( () -> new NegocioException("O aluguel informado nao está ativo ou id invalido"));
    }

    public void ativarLocacao(Long idLocacao){
        /**metodo ativa uma locação*/

        Locacao locacao = locacaoRepository.findByAtivoAndId(false, idLocacao)
                .orElseThrow( () -> new NegocioException("O aluguel informado já está ativo ou id invalido")
        );

        locacao.setAtivo(true);
        locacaoService.save(locacao);
    }

    public void desativarLocacao(Long idLocacao){
        /**metodo desativa uma locação */

        Locacao locacao = locacaoRepository.findByAtivoAndId(true, idLocacao)
                .orElseThrow( () -> new NegocioException("O aluguel informado nao está ativo ou id invalido")
                );

        locacao.setAtivo(false);
        locacaoService.save(locacao);
    }

    public Page<Locacao> buscaPaginadadeAtivos(Pageable paginacao) {
        return locacaoRepository.findByAtivo(true, paginacao);
    }

}
