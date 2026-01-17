package com.projetotres.DAO;

import com.projetotres.domain.Cliente;
import com.projetotres.exception.TipoChaveNaoEncontradoException;
import com.projetotres.factory.ConnectionFctory;
import com.projetotres.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.projetotres.DAO.generic.GenericDAO;


public class ProdutoDAO extends GenericDAO<Produto>  implements IProdutoDAO<Produto> {

    public ProdutoDAO() {
        super();
    }

    @Override
    public Integer alterar(Produto entity, long cpf) throws Exception {
        try{
            Connection connection = ConnectionFctory.getConnection();
            PreparedStatement stm;
            Produto produto =  entity;
            String sql = "UPDATE produto SET id = ?, nome = ?, preco = ? WHERE id = ?";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, produto.getId());
            stm.setString(2, produto.getNome());
            stm.setInt(3, produto.getPreco());
            stm.setLong(4, cpf);
            int result = stm.executeUpdate();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // TODO Auto-generated method stub

    }

    @Override
    public List<Produto> buscarTodos() {
        try{
            Connection connection = ConnectionFctory.getConnection();
            PreparedStatement stm;
            List<Produto> produtos = new ArrayList<>();
            ResultSet rs;
            String sql = "SELECT * FROM produto";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()){
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getInt("preco"));
                produtos.add(produto);
            }
            return produtos;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer cadastrar(Produto entity) throws TipoChaveNaoEncontradoException {
        try{
            Connection connection = ConnectionFctory.getConnection();
            PreparedStatement stm;
            String sql = "INSERT INTO produto (id, nome, preco) values (?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, entity.getId());
            stm.setString(2, entity.getNome());
            stm.setInt(3, entity.getPreco());
            int result = stm.executeUpdate();
            stm.close();
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Produto consultar(Long valor) {
        try{
            Connection connection = ConnectionFctory.getConnection();
            PreparedStatement stm;
            ResultSet rs;
            Produto produto = null;
            String sql = "SELECT * FROM produto WHERE id = ?";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, valor);
            rs = stm.executeQuery();
            rs.next();
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getInt("preco"));
            return produto;
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
            String sql = "DELETE FROM produto WHERE id = ?";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, valor);
            return stm.executeUpdate();}catch(Exception e){
            e.printStackTrace();
            return null;
            }
    }

    

}
