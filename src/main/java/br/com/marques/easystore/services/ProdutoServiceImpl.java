package br.com.marques.easystore.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.marques.easystore.dao.ProdutoDAO;
import br.com.marques.easystore.model.Categoria;
import br.com.marques.easystore.model.Produto;

@Component
public class ProdutoServiceImpl implements IProdutoService{

    @Autowired
    private ProdutoDAO dao;

    @Override
    public Produto inserirNovoProduto(Produto produto) {

        try {
            dao.save(produto);
            return produto;
        } catch (Exception e) {
            System.out.println("------ ProdutoService.inserirNovoProduto -----");
            e.printStackTrace();
            System.out.println("----------------------------------------------");

        }
        return null;
    }

    @Override
    public Produto alterarProduto(Produto produto) {

        try {
            dao.save(produto);
            return produto;
        } catch (Exception e) {
            System.out.println("------ ProdutoService.alterarProduto -----");
            e.printStackTrace();
            System.out.println("----------------------------------------------");

        }
        return null;
    }

    @Override
    public ArrayList<Produto> listarTodos() {
        return (ArrayList<Produto>)dao.findAll();
    }

    @Override
    public ArrayList<Produto> listarDisponiveis() {
        return dao.findAllByDisponivel(1);
    }

    @Override
    public ArrayList<Produto> listarIndisponiveis() {
        return dao.findAllByDisponivel(0);
    }

    @Override
    public ArrayList<Produto> listarPorCategoria(Categoria categoria) {
        return dao.findAllByDisponivelAndCategoria(1, categoria);
    }

    @Override
    public Produto recuperarPorId(int id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public ArrayList<Produto> listarPorPalavraChave(String palavraChave) {
        return dao.findByNomeContainingOrDetalheContaining(palavraChave, palavraChave);
    }
}
