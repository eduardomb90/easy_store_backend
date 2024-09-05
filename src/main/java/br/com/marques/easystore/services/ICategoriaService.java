package br.com.marques.easystore.services;

import java.util.ArrayList;

import br.com.marques.easystore.model.Categoria;

public interface ICategoriaService {
    public Categoria inserirNovaCategoria(Categoria categoria);
    public Categoria recuperarCategoriaPorId(int id);
    public Categoria alterarCategoria(Categoria categoria);
    public ArrayList<Categoria> recuperarTodasCategorias();
    public ArrayList<Categoria> recuperarPorPalavraChave(String palavraChave);
}
