package com.projetodois.service;

import com.projetodois.DAO.ClienteDAO;
import com.projetodois.DAO.IClienteDAO;
import com.projetodois.domain.Cliente;
import com.projetodois.exception.TipoChaveNaoEncontradaException;
import com.projetodois.service.generics.GenericService;

public class ClienteService extends GenericService implements IClienteService {

    public ClienteService(IClienteDAO clienteDAO) {
        super(clienteDAO);
    }

}
