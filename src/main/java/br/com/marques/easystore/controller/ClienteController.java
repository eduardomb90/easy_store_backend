package br.com.marques.easystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.marques.easystore.dto.CompradorDTO;
import br.com.marques.easystore.model.Cliente;
import br.com.marques.easystore.services.IClienteService;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@CrossOrigin("http://localhost:4200/")
public class ClienteController {
    @Autowired
    private IClienteService service;

    /*
    @GetMapping("/cliente/{telefone}")
    public ResponseEntity<Cliente> buscarPeloTelefone(@PathVariable(name = "telefone") String telefone) {
        Cliente resultado = service.buscarPeloTelefone(telefone);

        if(resultado != null) {
            return ResponseEntity.ok(resultado);
        }

        return ResponseEntity.notFound().build();
    }
     */

    @GetMapping("/cliente/{cpf}")
    public ResponseEntity<Cliente> buscarPeloCpf(@PathVariable(name = "cpf") String cpf) {
        Cliente resultado = service.buscarPeloCpf(cpf);

        if(resultado != null) {
            return ResponseEntity.ok(resultado);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cliente/nome/{letra}")
    public ResponseEntity<ArrayList<Cliente>> buscarPorLetra(@PathVariable(name = "letra") String letra) {
        return ResponseEntity.ok(service.buscarPorLetra(letra));
    }

    @GetMapping("/cliente")
    public ResponseEntity<ArrayList<Cliente>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/cliente/busca/{keyword}")
    public ResponseEntity<ArrayList<Cliente>> buscarPorPalavraChave(@PathVariable(name = "keyword") String keyword) {
        return ResponseEntity.ok(service.buscarPorPalavraChave(keyword));
    }

    @GetMapping("/cliente/compras/{id}")
    public ResponseEntity<ArrayList<CompradorDTO>> recuperarCompradores(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(service.buscarCompradores(id));
    }

    @GetMapping("/cliente/aniversario/{mes}")
    public ResponseEntity<ArrayList<Cliente>> recuperarAniversariantes(@PathVariable(name = "mes") int mes) {
        return ResponseEntity.ok(service.buscarAniversariantes(mes));
    }
}
