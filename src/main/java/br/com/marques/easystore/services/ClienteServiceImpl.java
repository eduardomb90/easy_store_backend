package br.com.marques.easystore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.marques.easystore.dao.ClienteDAO;
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
    public Cliente atualizarDados(Cliente dadosOriginais) {
        return dao.save(dadosOriginais);
    }
}
