package com.javanautas.fakeapi.infrastructure.client;

import com.javanautas.fakeapi.apiv1.dto.ProductsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "fake-api", url = "https://fakestoreapi.com")

public interface FakeApiClient {
    @GetMapping("/products")
    List<ProductsDTO> buscaListaProdutos();
}
