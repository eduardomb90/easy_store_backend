package br.com.marques.easystore.services;

import br.com.marques.easystore.model.Cliente;

public interface IClienteService {
    public Cliente buscarPeloTelefone(String telefone);
    public Cliente buscarPeloCpf(String cpf);
    public Cliente atualizarDados(Cliente dadosOriginais);
}
