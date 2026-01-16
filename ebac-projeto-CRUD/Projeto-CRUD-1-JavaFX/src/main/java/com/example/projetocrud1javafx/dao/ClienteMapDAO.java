package com.example.projetocrud1javafx.dao;

import com.example.projetocrud1javafx.domain.Cliente;

import java.util.HashMap;
import java.util.Map;

public class ClienteMapDAO implements com.example.projetocrud1javafx.dao.IClienteDAO {

    private Map<Long, Cliente> map;

    public ClienteMapDAO(){
        this.map = new HashMap<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {
        if(this.map.containsKey(cliente.getCpf())){
            return false;
        }
        this.map.put(cliente.getCpf(),cliente);
        return true;
    }

    @Override
    public void excluir(Long cpf) {
        Cliente clienteCadastrado = this.map.get(cpf);
        if(clienteCadastrado != null){
            this.map.remove(clienteCadastrado.getCpf(),clienteCadastrado);
        }

    }

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCadastrado = this.map.get(cliente.getCpf());
        if (clienteCadastrado != null){
            clienteCadastrado.setNome(cliente.getNome());
            clienteCadastrado.setCidade(cliente.getCidade());
            clienteCadastrado.setEnd(cliente.getEnd());
            clienteCadastrado.setEstado(cliente.getEstado());
            clienteCadastrado.setNumero(cliente.getNumero());
            clienteCadastrado.setTel(cliente.getTel());
        }

    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public HashMap<Long, Cliente> buscarTodos() {
        HashMap map = (HashMap) this.map;
        return map;
    }

}
