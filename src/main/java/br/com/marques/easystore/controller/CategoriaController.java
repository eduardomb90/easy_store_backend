package br.com.marques.easystore.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.marques.easystore.model.Categoria;
import br.com.marques.easystore.services.ICategoriaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class CategoriaController {

    @Autowired
    private ICategoriaService service;

    @GetMapping("/categorias")
    public ResponseEntity<ArrayList<Categoria>> listarTodas() {
        return ResponseEntity.ok().body(service.recuperarTodasCategorias());
    }

    //http://localhost:8080/categorias/search?key=palavraChave
    @GetMapping("/categorias/search") 
    public ResponseEntity<ArrayList<Categoria>> recuperarPorPalavraChave(@RequestParam(name = "key") String palavraChave) {
        if(palavraChave != null)
            return ResponseEntity.ok().body(service.recuperarPorPalavraChave(palavraChave));

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/categoria")
    public ResponseEntity<Categoria> adicionarNova(@RequestBody Categoria categoria) {
        Categoria resultado = service.inserirNovaCategoria(categoria);

        if(categoria.getId() != null)
            categoria.setId(null);

        if(resultado != null)
            return ResponseEntity.status(201).body(resultado);

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("categoria")
    public ResponseEntity<Categoria> alterarDados(@RequestBody Categoria categoria) {
        Categoria resultado = service.alterarCategoria(categoria);

        if(resultado != null)
            return ResponseEntity.ok(resultado);

        return ResponseEntity.badRequest().build();
    }
}
