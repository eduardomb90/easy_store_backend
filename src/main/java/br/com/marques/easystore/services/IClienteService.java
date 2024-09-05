package br.com.marques.easystore.services;

import br.com.marques.easystore.dto.CompradorDTO;
import br.com.marques.easystore.model.Cliente;
import java.util.ArrayList;

public interface IClienteService {
    public Cliente buscarPeloTelefone(String telefone);
    public Cliente buscarPeloCpf(String cpf);
    public Cliente atualizarDados(Cliente dadosOriginais);
    public ArrayList<Cliente> buscarPorLetra(String letra);
    public ArrayList<Cliente> buscarPorPalavraChave(String keyword);
    public ArrayList<Cliente> buscarTodos();
    public ArrayList<Cliente> buscarAniversariantes(int mes);

    public ArrayList<CompradorDTO> buscarCompradores(int idProduto);
}
