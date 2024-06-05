package br.com.marques.easystore.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.marques.easystore.model.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer>{
    public Usuario findByUsernameOrEmail(String username, String email);
}
