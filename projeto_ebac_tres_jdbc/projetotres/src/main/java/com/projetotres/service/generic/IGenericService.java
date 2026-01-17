package com.projetotres.service.generic;

import com.projetotres.domain.Cliente;
import com.projetotres.exception.TipoChaveNaoEncontradoException;
import java.util.List;

public interface IGenericService<T> {

    Integer salvar(T entity) throws TipoChaveNaoEncontradoException;

    Cliente buscarPorCPF(long cpf);

    List<Cliente> buscarTodos();

    Integer excluir(long cpf);

    Integer alterar(T entity, long cpf) throws TipoChaveNaoEncontradoException;


}
