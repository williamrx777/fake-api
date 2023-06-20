package com.javanautas.fakeapi.business.service;

import com.javanautas.fakeapi.apiv1.dto.ProductsDTO;
import com.javanautas.fakeapi.business.converter.ProdutoConverter;
import com.javanautas.fakeapi.infrastructure.client.FakeApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class FakeApiService {

    private final FakeApiClient client;
    private final ProdutoConverter converter;
    private final ProdutoService produtoService;
    public List<ProductsDTO> buscaProdutos() {
        try {
        List<ProductsDTO> dto = client.buscaListaProdutos();
        dto.forEach(produto -> {
        Boolean retorno = produtoService.existsPorNome(produto.getNome());
        if(retorno.equals(false)){
            produtoService.salvaProdutos(converter.ToEntity(produto));
        }
        });
        return produtoService.buscaTodosProdutos();
    } catch (Exception e) {
        throw new RuntimeException("Erro ao buscar e gravar produtos no banco de dados"+ e);
    }
}
}
