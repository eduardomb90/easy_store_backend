package br.com.marques.easystore.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

import br.com.marques.easystore.model.Cliente;
import br.com.marques.easystore.model.Pedido;

public interface PedidoDAO extends CrudRepository<Pedido, Integer> {
    public ArrayList<Pedido> findAllByCliente(Cliente cliente);
}
