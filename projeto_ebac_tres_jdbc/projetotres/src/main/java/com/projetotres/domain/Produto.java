package com.projetotres.domain;

public class Produto {
    
    Long id;
    String nome;
    int preco;
    

    public Produto() {
    }

    public Produto(long id, String nome, int preco) {
        this.nome = nome;
        this.preco = preco;
        this.id = id;
    }

    
    
    @Override
    public String toString() {
        return "Produto [nome=" + nome + ", preco=" + preco + ", id=" + id + "]";
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getPreco() {
        return preco;
    }
    public void setPreco(int preco) {
        this.preco = preco;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

}
