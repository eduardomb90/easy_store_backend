package br.com.marques.easystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

import br.com.marques.easystore.dto.FiltroPedidoDTO;
import br.com.marques.easystore.dto.VendasPorDataDTO;
import br.com.marques.easystore.model.Cliente;
import br.com.marques.easystore.model.Pedido;
import br.com.marques.easystore.services.IClienteService;
import br.com.marques.easystore.services.IPedidoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class PedidoController {

    @Autowired
    private IPedidoService service;

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/pedido")
    public ResponseEntity<ArrayList<Pedido>> recuperarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

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

    @GetMapping("/pedido/{id}")
    public ResponseEntity<Pedido> alterarStatus(@PathVariable(name = "id") int id,  @RequestParam(name = "status") int status) {
        try {
            Pedido pedido = service.mudarStatus(id, status);
            if(pedido != null) {
                return ResponseEntity.ok(pedido);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/pedido/recentes")
    public ResponseEntity<ArrayList<VendasPorDataDTO>> recuperarUltimoMes() {
        return ResponseEntity.ok(service.recuperarTotaisUltimoMes());
    }

    @PostMapping("pedido/filtrar")
    public ResponseEntity<ArrayList<Pedido>> recuperarPedidosFiltrados(@RequestBody FiltroPedidoDTO filtro) {
        return ResponseEntity.ok(service.buscarFiltro(filtro));
    }
}
