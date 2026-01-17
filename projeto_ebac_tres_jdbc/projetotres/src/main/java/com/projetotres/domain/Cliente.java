package com.projetotres.domain;

public class Cliente {

    private Long CPF;
    private String nome;
    private String cidade;
    private String estado;
    private Long tel;
    private Integer numero;

    public Cliente() {
    }
    
    

    @Override
    public String toString() {
        return "Cliente [CPF=" + CPF + ", nome=" + nome + ", cidade=" + cidade + ", estado=" + estado + ", tel=" + tel
                + ", numero=" + numero + "]";
    }



    public Cliente(Long cPF, String nome, String cidade, String estado, Long tel, Integer numero) {
        super();
        CPF = cPF;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.tel = tel;
        this.numero = numero;
    }

    
    public Long getCPF() {
        return CPF;
    }
    public void setCPF(Long cPF) {
        CPF = cPF;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Long getTel() {
        return tel;
    }
    public void setTel(Long tel) {
        this.tel = tel;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    

}
