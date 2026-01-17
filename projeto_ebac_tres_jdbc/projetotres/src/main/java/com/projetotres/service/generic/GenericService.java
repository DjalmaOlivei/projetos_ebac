package com.projetotres.service.generic;

import com.projetotres.DAO.IClienteDAO;

import java.util.List;

import com.projetotres.DAO.ClienteDAO;
import com.projetotres.domain.Cliente;
import com.projetotres.exception.TipoChaveNaoEncontradoException;

public class GenericService<T> implements IGenericService<T> {

    private IClienteDAO clienteDAO;

    public GenericService(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public Cliente buscarPorCPF(long cpf) {
        // Implement search logic here
        return (Cliente) clienteDAO.consultar(cpf);
    }


    public Integer salvar(T entity) throws TipoChaveNaoEncontradoException {
        try{
        return clienteDAO.cadastrar(entity);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public Integer excluir(long cpf) {
        return clienteDAO.excluir(cpf);
    }

    
    public Integer alterar(T entity, long cpf) throws TipoChaveNaoEncontradoException {
        try{
        return clienteDAO.alterar((Cliente)entity, cpf);
        }catch(Exception e){
        e.printStackTrace();
        return null;
        }
    }

    @Override
    public List<Cliente> buscarTodos() {
        return (List<Cliente>) clienteDAO.buscarTodos();
    }

}
