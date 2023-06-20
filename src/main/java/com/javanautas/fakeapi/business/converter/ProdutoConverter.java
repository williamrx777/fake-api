package com.javanautas.fakeapi.business.converter;

import com.javanautas.fakeapi.apiv1.dto.ProductsDTO;
import com.javanautas.fakeapi.infrastructure.entities.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class ProdutoConverter {

    public ProdutoEntity ToEntity(ProductsDTO dto) {
        return ProdutoEntity.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .nome(dto.getNome())
                .categoria(dto.getCategoria())
                .preco(dto.getPreco())
                .descricao(dto.getDescricao())
                .imagem(dto.getImagem())
                .dataInclusao(LocalDateTime.now())
                .build();
    }

    public ProdutoEntity ToEntityUpdate(ProdutoEntity entity, ProductsDTO dto, String id) {
        return ProdutoEntity.builder()
                .id(id)
                .nome(dto.getNome() != null ? dto.getNome() : entity.getNome())
                .categoria(dto.getCategoria() != null ? dto.getCategoria() : entity.getCategoria())
                .preco(dto.getPreco() != null ? dto.getPreco() : entity.getPreco())
                .descricao(dto.getDescricao() != null ? dto.getDescricao() : entity.getDescricao())
                .imagem(dto.getImagem() != null ? dto.getImagem() : entity.getImagem())
                .dataInclusao(entity.getDataInclusao())
                .dataAtualizacao(LocalDateTime.now())
                .build();
    }

    public ProductsDTO toDTO(ProdutoEntity entity) {
        return ProductsDTO.builder()
                .entityId(entity.getId())
                .nome(entity.getNome())
                .categoria(entity.getCategoria())
                .preco(entity.getPreco())
                .descricao(entity.getDescricao())
                .imagem(entity.getImagem())
                .build();
    }

    public List<ProductsDTO> toListDTO(List<ProdutoEntity> entityList){
        return entityList.stream().map(this::toDTO).toList();
    }

}
