package com.javanautas.fakeapi.apiv1.dto;

import com.javanautas.fakeapi.business.service.FakeApiService;
import com.javanautas.fakeapi.business.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
@Tag(name = "fake-api")
public class FakeApiController {

    private final FakeApiService service;
    private final ProdutoService produtoService;
    @Operation(summary = "Busca produtos e Salvar", method = "POST")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @PostMapping("/api")
    public ResponseEntity<List<ProductsDTO>> salvaProdutosApi() {
        return ResponseEntity.ok(service.buscaProdutos());
    }

    @Operation(summary = "Salvar novos produtos", method = "POST")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os produtos"),
    })
    @PostMapping("/")
    public ResponseEntity<ProductsDTO> salvaProdutos(@RequestBody ProductsDTO dto) {
        return ResponseEntity.ok(produtoService.salvaProdutos(dto));
    }

    @Operation(summary = "Fazer update de novos produtos", method = "PUT")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Produto alterado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao alterar os produtos"),
    })
    @PutMapping("/")
    public ResponseEntity<ProductsDTO> updateProdutos(@RequestParam ("id")String id, @RequestBody ProductsDTO dto) {
        return ResponseEntity.ok(produtoService.updateProduto(id,dto));
    }

    @Operation(summary = "Fazer delete dos produtos", method = "DELETE")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Produto deletar com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar os produtos"),
    })
    @DeleteMapping("/")
    public ResponseEntity<Void> deleteProduto(@RequestParam ("nome")String nome) {
        produtoService.deletaProduto(nome);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Buscar todos os produtos", method = "GET")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Produto alterado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao alterar os produtos"),
    })
    @GetMapping("/")
    public ResponseEntity<List<ProductsDTO>> buscaTodosProdutos() {
        return ResponseEntity.ok(produtoService.buscaTodosProdutos());
    }

    @Operation(summary = "Buscar todos os produtos por nome", method = "GET")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Produto alterado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao alterar os produtos"),
    })
    @GetMapping("/{nome}")
    public ResponseEntity<ProductsDTO> buscaProdutoPorNome(@PathVariable ("nome") String nome) {
        return ResponseEntity.ok(produtoService.buscaProdutoPorNome(nome));
    }

}
