package com.projetodois.service.generics;

import com.projetodois.domain.Cliente;
import com.projetodois.exception.TipoChaveNaoEncontradaException;

public interface IGenericService <T> {

    
    boolean salvar(T entity) throws TipoChaveNaoEncontradaException;

    Cliente buscarPorCPF(long cpf);

    void excluir(long cpf);

    void alterar(T entity) throws TipoChaveNaoEncontradaException;

}
