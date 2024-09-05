package br.com.marques.easystore.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import br.com.marques.easystore.model.Cliente;
import br.com.marques.easystore.model.Pedido;

public interface PedidoDAO extends CrudRepository<Pedido, Integer> {
    public ArrayList<Pedido> findAllByCliente(Cliente cliente);
    public ArrayList<Pedido> findAllByDataPedidoBetweenOrderByDataPedidoDesc(LocalDate inicio, LocalDate fim);
    public ArrayList<Pedido> findAllByStatusOrderByDataPedido(int status);
    public ArrayList<Pedido> findAllByOrderByDataPedidoDesc();
    public ArrayList<Pedido> findAllByStatusInOrderByDataPedidoDesc(Collection<Integer> status);
    public ArrayList<Pedido> findAllByClienteInOrderByDataPedidoDesc(Collection<Cliente> cliente);
    public ArrayList<Pedido> findAllByClienteInAndStatusInOrderByDataPedidoDesc(Collection<Cliente> cliente, Collection<Integer> status);
    public ArrayList<Pedido> findAllByDataPedidoBetweenAndStatusInOrderByDataPedidoDesc(LocalDate inicio, LocalDate fim, Collection<Integer> status);
    public ArrayList<Pedido> findAllByDataPedidoBetweenAndClienteInOrderByDataPedidoDesc(LocalDate inicio, LocalDate fim, Collection<Cliente> cliente);
    public ArrayList<Pedido> findAllByDataPedidoBetweenAndClienteInAndStatusInOrderByDataPedidoDesc(LocalDate inicio, LocalDate fim, Collection<Cliente> cliente, Collection<Integer> status);

    @Query(value = "SELECT sum(valor_total) as total, data_pedido as data FROM tbl_pedido "
            +"WHERE data_pedido BETWEEN (date_sub(now(), interval 60 day)) and now() "
            +"GROUP BY (data_pedido) "
            +"ORDER BY data_pedido DESC", nativeQuery = true)
    public List<Object[]> recuperarVendasPorData();
}
