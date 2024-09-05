package br.com.marques.easystore.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.marques.easystore.dao.ClienteDAO;
import br.com.marques.easystore.dto.CompradorDTO;
import br.com.marques.easystore.model.Cliente;

@Component
public class ClienteServiceImpl implements IClienteService {
    @Autowired
    private ClienteDAO dao;

    @Override
    public Cliente buscarPeloTelefone(String telefone) {
        if(telefone.charAt(0) == '0')
            telefone = telefone.substring(1);

        return dao.findByTelefone(telefone);
    }

    @Override
    public Cliente buscarPeloCpf(String cpf) {
        return dao.findByCpf(cpf);
    }

    @Override
    public Cliente atualizarDados(Cliente dadosOriginais) {
        return dao.save(dadosOriginais);
    }

    @Override
    public ArrayList<Cliente> buscarPorLetra(String letra) {
        return dao.findByNomeStartsWith(letra);
    }

    @Override
    public ArrayList<Cliente> buscarPorPalavraChave(String keyword) {
        return dao.findByNomeContaining(keyword);
    }

    @Override
    public ArrayList<Cliente> buscarTodos() {
        return dao.findByOrderByNomeAsc();
    }

    @Override
    public ArrayList<CompradorDTO> buscarCompradores(int idProduto) {
        return dao.recuperarCompradores(idProduto);
    }

    @Override
    public ArrayList<Cliente> buscarAniversariantes(int mes) {
        return dao.recuperarAniversariantes(mes);
    }
}
