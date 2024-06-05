package br.com.marques.easystore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.marques.easystore.dao.UsuarioDAO;
import br.com.marques.easystore.model.Usuario;

@Component
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioDAO dao;

    @Override
    public Usuario recuperarUsuario(Usuario original) {
        return dao.findByUsernameOrEmail(original.getUsername(), original.getEmail());
    }
}
