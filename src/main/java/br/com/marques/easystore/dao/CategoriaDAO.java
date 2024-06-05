package br.com.marques.easystore.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.marques.easystore.model.Categoria;

public interface CategoriaDAO extends CrudRepository<Categoria, Integer> {
    // consultas customizadas
    public ArrayList<Categoria> findByNomeContaining(String palavra);
}
