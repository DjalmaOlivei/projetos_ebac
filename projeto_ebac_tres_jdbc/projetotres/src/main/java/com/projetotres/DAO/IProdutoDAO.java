package com.projetotres.DAO;

public interface IProdutoDAO<T>{

    Integer cadastrar(T entity) throws Exception;
    Integer alterar(T entity, long cpf) throws Exception;
    Integer excluir(Long valor);
    T consultar(Long valor);
    java.util.Collection<T> buscarTodos();

}
