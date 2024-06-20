package br.com.marques.easystore.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

import br.com.marques.easystore.model.Produto;
import br.com.marques.easystore.model.Categoria;

public interface ProdutoDAO extends CrudRepository<Produto, Integer> {
    public ArrayList<Produto> findAllByDisponivel(int disponivel);
    public ArrayList<Produto> findAllByDisponivelAndCategoria(int disponivel, Categoria categoria);
    public ArrayList<Produto> findAllByCategoria(Categoria categoria);
    public ArrayList<Produto> findByNomeContainingOrDetalheContaining(String keyNome, String keyDetalhe);
}
