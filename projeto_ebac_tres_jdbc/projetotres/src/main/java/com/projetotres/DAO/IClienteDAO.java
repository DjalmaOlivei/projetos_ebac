package com.projetotres.DAO;

import com.projetotres.DAO.generic.IGenericDAO;

public interface IClienteDAO<T>  {

    Integer cadastrar(T entity) throws Exception;
    Integer alterar(T entity, long cpf) throws Exception;
    Integer excluir(Long valor);
    T consultar(Long valor);
    java.util.Collection<T> buscarTodos();

}
