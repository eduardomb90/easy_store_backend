package br.com.marques.easystore.services;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.marques.easystore.dto.FiltroPedidoDTO;
import br.com.marques.easystore.dto.VendasPorDataDTO;
import br.com.marques.easystore.model.Pedido;

public interface IPedidoService {
    public ArrayList<Pedido> buscarTodos();
    public Pedido inserirPedido(Pedido novo);
    public ArrayList<Pedido> buscarPorStatus(int status);
    public Pedido mudarStatus(int idPedido, int novoStatus);
    public ArrayList<Pedido> buscarPorPeriodo(LocalDate inicio, LocalDate fim);
    public ArrayList<Pedido> buscarFiltro(FiltroPedidoDTO filtro);

    public ArrayList<VendasPorDataDTO> recuperarTotaisUltimoMes();
}
