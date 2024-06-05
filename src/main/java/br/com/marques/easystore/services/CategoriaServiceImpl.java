package br.com.marques.easystore.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.marques.easystore.dao.CategoriaDAO;
import br.com.marques.easystore.model.Categoria;

@Component
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    private CategoriaDAO dao;

    @Override
    public Categoria inserirNovaCategoria(Categoria categoria) {
        try {
            if(categoria.getNome() != null && categoria.getNome().trim().length() > 0){
                dao.save(categoria);
                return categoria;
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("DEBUG = "+ e.getMessage());
        }
        catch (Exception e){
            System.out.println("DEBUG = "+ e.getMessage());
        }

        return null;
    }

    @Override
    public Categoria alterarCategoria(Categoria categoria) {

        try {
            if(categoria.getId() != null && categoria.getNome() != null && categoria.getNome().trim().length() > 0){
                dao.save(categoria);
                return categoria;
            }
        }
        catch (Exception e) {
            System.out.println("DEBUG = "+ e.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Categoria> recuperarTodasCategorias() {
        return (ArrayList<Categoria>)dao.findAll();
    }

    @Override
    public ArrayList<Categoria> recuperarPorPalavraChave(String palavraChave) {
        if(palavraChave != null){
            return dao.findByNomeContaining(palavraChave);
        }

        return null;
    }
}
