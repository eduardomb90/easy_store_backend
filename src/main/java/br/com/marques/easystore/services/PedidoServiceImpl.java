package br.com.marques.easystore.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.marques.easystore.dao.ClienteDAO;
import br.com.marques.easystore.dao.PedidoDAO;
import br.com.marques.easystore.dto.FiltroPedidoDTO;
import br.com.marques.easystore.dto.VendasPorDataDTO;
import br.com.marques.easystore.model.Cliente;
import br.com.marques.easystore.model.ItemPedido;
import br.com.marques.easystore.model.Pedido;

@Component
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    private PedidoDAO dao;
    
    @Autowired
    private ClienteDAO clienteDao;


    @Override
    public Pedido inserirPedido(Pedido novo) {

        try {

            double total = 0.0;
            for(ItemPedido item: novo.getItensPedido()){
                item.setPrecoUnitario(item.getProduto().getPreco());
                item.setPrecoTotal(item.getPrecoUnitario() * item.getQtdeItem());
                total += item.getPrecoTotal();
            }

            for(ItemPedido item: novo.getItensPedido()){
                item.setPedido(novo);
            }

            novo.setStatus(Pedido.NOVO_PEDIDO);
            novo.setValorTotal(total);
            dao.save(novo);
            return novo;
        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public ArrayList<Pedido> buscarPorStatus(int status) {
        return dao.findAllByStatusOrderByDataPedido(status);
    }

    @Override
    public Pedido mudarStatus(int id, int novoStatus) {
        try {
            Pedido pedido = dao.findById(id).get();
            pedido.setStatus(novoStatus);
            dao.save(pedido);
            return pedido;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArrayList<Pedido> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return dao.findAllByDataPedidoBetweenOrderByDataPedidoDesc(inicio, fim);
    }

    @Override
    public ArrayList<Pedido> buscarTodos() {
        return (ArrayList<Pedido>) dao.findAllByOrderByDataPedidoDesc();
    }

    @Override
    public ArrayList<VendasPorDataDTO> recuperarTotaisUltimoMes() {
        List<Object[]> results = dao.recuperarVendasPorData();
        ArrayList<VendasPorDataDTO> vendas = new ArrayList<>();

        for (Object[] result : results) {
            double total = ((Number) result[0]).doubleValue();
            LocalDate data = ((java.sql.Date) result[1]).toLocalDate();
            vendas.add(new VendasPorDataDTO(total, data));
        }

        return vendas;
    }

    @Override
    public ArrayList<Pedido> buscarFiltro(FiltroPedidoDTO filtro) {
        
        boolean temData = filtro.getDataInicio() != null && filtro.getDataFim() != null;
        boolean temNome = filtro.getNome() != null && !filtro.getNome().isEmpty();
        boolean temStatus = filtro.getEntregue() != 0 || filtro.getPago() != 0 || filtro.getCancelado() != 0 ;

        Collection<Integer> statusList = new ArrayList<Integer>();
        if (filtro.getPago() == 1) statusList.add(Pedido.PAGO);
        if (filtro.getEntregue() == 1) statusList.add(Pedido.ENTREGUE);
        if (filtro.getCancelado() == 1) statusList.add(Pedido.CANCELADO);

        if(!temData && !temNome && !temStatus){
            return dao.findAllByOrderByDataPedidoDesc();
        }
        else if(!temData && !temNome && temStatus) {
            return dao.findAllByStatusInOrderByDataPedidoDesc(statusList);
        }
        else if(!temData && temNome && !temStatus) {
            ArrayList<Cliente> clientesList = clienteDao.findByNomeContaining(filtro.getNome());
            return dao.findAllByClienteInOrderByDataPedidoDesc(clientesList);
        }
        else if(!temData && temNome && temStatus) {
            ArrayList<Cliente> clientesList = clienteDao.findByNomeContaining(filtro.getNome());
            return dao.findAllByClienteInAndStatusInOrderByDataPedidoDesc(clientesList, statusList);
        }
        else if(temData && !temNome && !temStatus) {
            return dao.findAllByDataPedidoBetweenOrderByDataPedidoDesc(filtro.getDataInicio(), filtro.getDataFim());
        }
        else if(temData && !temNome && temStatus) {
            return dao.findAllByDataPedidoBetweenAndStatusInOrderByDataPedidoDesc(filtro.getDataInicio(), filtro.getDataFim(), statusList);
        }
        else if(temData && temNome && !temStatus) {
            ArrayList<Cliente> clientesList = clienteDao.findByNomeContaining(filtro.getNome());
            return dao.findAllByDataPedidoBetweenAndClienteInOrderByDataPedidoDesc(filtro.getDataInicio(), filtro.getDataFim(), clientesList);
        }
        else if(temData && temNome && temStatus) {
            ArrayList<Cliente> clientesList = clienteDao.findByNomeContaining(filtro.getNome());
            return dao.findAllByDataPedidoBetweenAndClienteInAndStatusInOrderByDataPedidoDesc(filtro.getDataInicio(), filtro.getDataFim(), clientesList, statusList);
        }

        return null;
    }
}
