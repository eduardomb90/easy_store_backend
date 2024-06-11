package br.com.marques.easystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.marques.easystore.model.Cliente;
import br.com.marques.easystore.model.Pedido;
import br.com.marques.easystore.services.IClienteService;
import br.com.marques.easystore.services.IPedidoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDate;


@RestController
public class PedidoController {

    @Autowired
    private IPedidoService service;

     @Autowired
    private IClienteService clienteService;

    @PostMapping("/pedido")
    public ResponseEntity<Pedido> inserirNovoPedido(@RequestBody Pedido novo) {
        novo.setDataPedido(LocalDate.now());

        Cliente cliente = clienteService.atualizarDados(novo.getCliente());

        novo.setCliente(cliente); // atualiza para o cliente que agora já está salvo no Banco de dados.
        novo = service.inserirPedido(novo);

        if (novo != null) {
            return ResponseEntity.status(201).body(novo);
        }
        else {
            return ResponseEntity.status(400).build();
        }
    }
}
