package com.projetotres.service;

import com.projetotres.DAO.IClienteDAO;
import com.projetotres.service.generic.GenericService;

public class ClienteService<T> extends GenericService<T> {

    public ClienteService(IClienteDAO clienteDAO) {
        super(clienteDAO);
    }


}
