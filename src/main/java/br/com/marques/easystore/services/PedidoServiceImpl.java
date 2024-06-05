package br.com.marques.easystore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.marques.easystore.dao.PedidoDAO;
import br.com.marques.easystore.model.ItemPedido;
import br.com.marques.easystore.model.Pedido;

@Component
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    private PedidoDAO dao;

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

            novo.setValorTotal(total);
            dao.save(novo);
            return novo;
        } catch (Exception e) {

            return null;
        }
    }
}
