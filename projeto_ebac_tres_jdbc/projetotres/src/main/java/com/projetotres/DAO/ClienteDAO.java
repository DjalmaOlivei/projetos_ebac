package com.projetotres.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

import com.projetotres.DAO.generic.GenericDAO;
import com.projetotres.exception.TipoChaveNaoEncontradoException;
import com.projetotres.factory.ConnectionFctory;
import com.projetotres.domain.Cliente;
import java.util.List;
import java.util.ArrayList;

public class ClienteDAO extends GenericDAO<Cliente> implements IClienteDAO<Cliente> {

    public ClienteDAO() {
        super();
    }

    @Override
    public Integer cadastrar(Cliente cliente) throws TipoChaveNaoEncontradoException {
        try{
            Connection connection = ConnectionFctory.getConnection();
            PreparedStatement stm;
            String sql = "INSERT INTO cliente (cpf, nome, cidade, estado, tel, numero) values (?, ?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, cliente.getCPF());
            stm.setString(2, cliente.getNome());
            stm.setString(3, cliente.getCidade());
            stm.setString(4, cliente.getEstado());
            stm.setLong(5, cliente.getTel());
            stm.setLong(6, cliente.getNumero());
            int result = stm.executeUpdate();
            stm.close();
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer alterar(Cliente entity, long cpf) throws TipoChaveNaoEncontradoException {
        // TODO Auto-generated method stub
        try {
            Connection connection = ConnectionFctory.getConnection();
            PreparedStatement stm;
            Cliente cliente = (Cliente) entity;
            String sql = "UPDATE cliente SET cpf = ?, nome = ?, cidade = ?, estado = ?, tel = ?, numero = ? WHERE cpf = ?";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, cliente.getCPF());
            stm.setString(2, cliente.getNome());
            stm.setString(3, cliente.getCidade());
            stm.setString(4, cliente.getEstado());
            stm.setLong(5, cliente.getTel());
            stm.setLong(6, cliente.getNumero());
            stm.setLong(7, cpf);
            int result = stm.executeUpdate();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        // TODO Auto-generated method stub
        try{
            Connection connection = ConnectionFctory.getConnection();
            PreparedStatement stm;
            List<Cliente> clientes = new ArrayList<>();
            ResultSet rs;
            String sql = "SELECT * FROM cliente";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setCPF(Long.parseLong(rs.getString("cpf")));
                cliente.setNome(rs.getString("nome"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setTel(Long.parseLong(rs.getString("tel")));
                cliente.setNumero(Integer.parseInt(rs.getString("numero")));
                clientes.add(cliente);
            }
            return clientes;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Cliente consultar(Long valor) {

        try{
            Connection connection = ConnectionFctory.getConnection();
            PreparedStatement stm;
            ResultSet rs;
            String sql = "SELECT * FROM cliente WHERE cpf = ?";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, valor);
            rs = stm.executeQuery();
            rs.next();
                Cliente cliente = new Cliente();
                cliente.setCPF(Long.parseLong(rs.getString("cpf")));
                cliente.setNome(rs.getString("nome"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setTel(Long.parseLong(rs.getString("tel")));
                cliente.setNumero(Integer.parseInt(rs.getString("numero")));
                return cliente;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer excluir(Long valor) {
        try{
            Connection connection = ConnectionFctory.getConnection();
            PreparedStatement stm;
            String sql = "DELETE FROM cliente WHERE cpf = ?";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, valor);
            return stm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    

}
