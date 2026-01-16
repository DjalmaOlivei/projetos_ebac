package com.projetodois.service.generics;

import com.projetodois.DAO.IClienteDAO;
import com.projetodois.domain.Cliente;
import com.projetodois.exception.TipoChaveNaoEncontradaException;

public class GenericService <T> implements IGenericService<T> {

    private IClienteDAO clienteDAO;

    public GenericService(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public Cliente buscarPorCPF(long cpf) {
        // Implement search logic here
        return (Cliente) clienteDAO.consultar(cpf);
    }


    public boolean salvar(T entity) throws TipoChaveNaoEncontradaException {
        // Implement saving logic here
        return clienteDAO.cadastrar((Cliente) entity);
    }
    
    public void excluir(long cpf) {
        clienteDAO.excluir(cpf);
    }

    
    public void alterar(T entity) throws TipoChaveNaoEncontradaException {
        clienteDAO.alterar((Cliente)entity);
    }

}
