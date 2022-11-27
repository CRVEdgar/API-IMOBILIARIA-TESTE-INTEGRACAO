package com.example.imobiliaria.domain.service;

import com.example.imobiliaria.domain.exception.EntidadeEmUsoException;
import com.example.imobiliaria.domain.exception.ImovelNaoEncontradoException;
import com.example.imobiliaria.domain.model.Imoveis;
import com.example.imobiliaria.domain.repository.ImoveisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImovelService {
    private static final String MSG_IMOVEL_EM_USO
            = "Imovel de código %d não pode ser removido, pois está associada a outro item do banco";

    @Autowired
    private ImoveisRepository imovelRepository;

    @Transactional
    public Imoveis save(Imoveis imovel) {
        return imovelRepository.save(imovel);
    }

    @Transactional
    public void delete(Long idImovel) {
        try {
            imovelRepository.deleteById(idImovel);
        }catch (EmptyResultDataAccessException e) {
            throw new ImovelNaoEncontradoException(idImovel);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(MSG_IMOVEL_EM_USO, idImovel));
        }
    }

    public List<Imoveis> findAll(){
        List<Imoveis> imoveis = imovelRepository.findAll();
        return imoveis;
    }

    public Imoveis findById(Long idImovel){
        return imovelRepository.findById(idImovel)
                .orElseThrow( () -> new ImovelNaoEncontradoException(idImovel)
                );
    }

    @Transactional
    public Imoveis update(Long idImovel, Imoveis imovel){
        Imoveis imovelParaAtualizar = findById(idImovel);
        imovel.setId(imovelParaAtualizar.getId());

        return save(imovel);
    }


}
