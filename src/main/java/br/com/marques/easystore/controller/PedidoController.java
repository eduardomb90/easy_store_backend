package br.com.marques.easystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.marques.easystore.model.Pedido;
import br.com.marques.easystore.services.IPedidoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PedidoController {

    @Autowired
    private IPedidoService service;

    @PostMapping("/pedido")
    public ResponseEntity<Pedido> inserirNovoPedido(@RequestBody Pedido novo) {
        novo = service.inserirPedido(novo);

        if (novo != null) {
            return ResponseEntity.status(201).body(novo);
        }
        else {
            return ResponseEntity.status(400).build();
        }
    }
}
