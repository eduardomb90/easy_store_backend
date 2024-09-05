package br.com.marques.easystore.services;

import java.util.ArrayList;

import br.com.marques.easystore.model.Usuario;

public interface IUsuarioService {
    public Usuario recuperarUsuario(Usuario original);
    public Usuario recuperarPorId(int id);
    public ArrayList<Usuario> resuperarTodos();
    public Usuario adicionarNovo(Usuario novo);
    public Usuario atualizarUsuario(Usuario user);
}
