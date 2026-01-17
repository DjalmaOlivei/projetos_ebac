package com.projetotres.DAO.generic;

import com.projetotres.exception.TipoChaveNaoEncontradoException;
import com.projetotres.factory.ConnectionFctory;
import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class GenericDAO<T> implements IGenericDAO<T> {

    public GenericDAO() {
    }

    @Override
    public  Integer cadastrar(T entity) throws TipoChaveNaoEncontradoException {
        return null;
    }

    @Override
    public Integer excluir(Long valor) {
        // Implementar lógica de exclusão
        return null;
    }

    @Override
    public Integer alterar(T entity) throws TipoChaveNaoEncontradoException {
        // Implementar lógica de alteração
        return null;
    }

    @Override
    public T consultar(Long valor) {
        // Implementar lógica de consulta
        return null;
    }

    @Override
    public java.util.Collection<T> buscarTodos() {
        // Implementar lógica para buscar todos os registros
        return null;
    }
}