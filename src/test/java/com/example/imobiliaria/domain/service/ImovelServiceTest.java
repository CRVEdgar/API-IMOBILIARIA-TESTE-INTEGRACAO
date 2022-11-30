package com.example.imobiliaria.domain.service;

import com.example.imobiliaria.domain.model.Imoveis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ImovelServiceTest {

    ImovelService service;

    @BeforeEach
    public void init(){
        service = new ImovelService();
    }

    @Test
    @DisplayName("deve retornar todos os imoveis disponíveis do tipo Apartamento")
    public void buscarApartamentosDisponiveis(){
        String bairroMock = "Vila Nova";
        String tipoImovel = "Apartamento";

        /**@@Method-1: utilizando lambda para realizar o filtro */
        List<Imoveis> imoveisResponse = service.findActiveApartment(bairroMock, tipoImovel);

        /**@@Method-2: utilizando query para realizar o filtro*/
//        List<Imoveis> imoveisResponse = service.findActiveApartmentQueryMethod(bairroMock, tipoImovel);

        assertNotNull(imoveisResponse);

        assertTrue(
                imoveisResponse
                        .stream()
                        .allMatch(imovel -> imovel.getTipo_Imovel().equals("Apartamento"))
        );

        assertFalse(
                imoveisResponse
                        .stream()
                        .allMatch(imovel -> imovel.isAtivo())
        );

        assertTrue(
                imoveisResponse
                        .stream()
                        .allMatch(imovel -> imovel.getBairro().equals(bairroMock))
        );
    }
}
