package br.com.marques.easystore.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

import br.com.marques.easystore.dto.CompradorDTO;
import br.com.marques.easystore.model.Cliente;

public interface ClienteDAO extends CrudRepository<Cliente, Integer> {
    public Cliente findByEmailOrTelefone(String email, String telefone);
    public Cliente findByTelefone(String telefone);
    public Cliente findByCpf(String cpf);
    public ArrayList<Cliente> findByNomeStartsWith(String prefixo);
    public ArrayList<Cliente> findByNomeContaining(String keyword);
    public ArrayList<Cliente> findByOrderByNomeAsc();

    /*
     * SELECT DISTINCT cli.nome_cliente, cli.email_cliente, cli.telefone_cliente
     * FROM tbl_cliente as cli INNER JOIN tbl_pedido on tbl_pedido.id_cliente = cli.id_cliente
     * INNER JOIN tbl_itempedido on tbl_itempedido.id_pedido = tbl_pedido.id_pedido
     * INNER JOIN tbl_produto on tbl_itempedido.id_produto = tbl_produto.id_produto
     * WHERE tbl_produto.id_produto = 1;
    */

    @Query("SELECT DISTINCT new br.com.marques.easystore.dto.CompradorDTO(cli.nome, cli.email, cli.telefone)"
            + " FROM Cliente cli INNER JOIN Pedido ped on cli.id = ped.cliente.id "
            + " INNER JOIN ItemPedido itm ON itm.pedido.id = ped.id "
            + " INNER JOIN Produto pro ON itm.produto.id = pro.id"
            + " WHERE pro.id = :id")
    public ArrayList<CompradorDTO> recuperarCompradores(@Param("id") int id);

    @Query("SELECT new br.com.marques.easystore.model.Cliente(cli.nome, cli.dataNasc, cli.telefone)"
            +" FROM Cliente cli WHERE month(cli.dataNasc) = :mes")
    public ArrayList<Cliente> recuperarAniversariantes(@Param("mes") int mes);
}
