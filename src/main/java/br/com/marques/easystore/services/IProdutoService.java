package br.com.marques.easystore.services;

import java.util.ArrayList;

import br.com.marques.easystore.model.Categoria;
import br.com.marques.easystore.model.Produto;

public interface IProdutoService {
    public Produto inserirNovoProduto(Produto produto);
    public Produto alterarProduto(Produto produto);
    public ArrayList<Produto> listarTodos();
    public ArrayList<Produto> listarDisponiveis();
    public ArrayList<Produto> listarIndisponiveis();
    public ArrayList<Produto> listarDestaques();
    public ArrayList<Produto> listarPorCategoria(Categoria categoria);
    public Produto recuperarPorId(int id);
    public ArrayList<Produto> listarPorPalavraChave(String palavraChave);
}
