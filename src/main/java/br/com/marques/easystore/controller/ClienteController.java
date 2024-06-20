package br.com.marques.easystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.marques.easystore.model.Cliente;
import br.com.marques.easystore.services.IClienteService;

// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
// @CrossOrigin("http://localhost:4200/")
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
}
