package com.projetodois.domain;

import java.io.Serializable;

public class Cliente  implements Persistente {
    
    private Long CPF;
    private String nome;
    private String cidade;
    private String estado;
    private Long tel;
    private Integer numero;


    public void setCPF(Long CPF) {
        this.CPF = CPF;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setTel(Long tel) {
        this.tel = tel;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public Long getCPF() {
        return CPF;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public Long getTe() {
        return tel;
    }

    public Integer getNumero() {
        return numero;
    }


}
