package br.com.rpires.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.spi.DirStateFactory.Result;

import br.com.rpires.domain.EstoqueProduto;
import br.com.rpires.domain.Produto;
import br.com.rpires.exceptions.DAOException;
import br.com.rpires.exceptions.ProdutoForaDeEstoqueException;
import br.com.rpires.dao.generic.GenericDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class EstoqueProdutoDAO extends GenericDAO<EstoqueProduto, Long> implements IEstoqueProdutoDAO{

    public EstoqueProduto getEsoqueProduto(Produto produto){
        try{
        Connection connection = getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM tb_estoque_produto WHERE id_produto_fk = ?");
        stm.setLong(1, produto.getId());
        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            EstoqueProduto estoque = new EstoqueProduto();
            estoque.setId(rs.getLong("ID"));
            estoque.setCadastro(rs.getLong("cadastro"));
            estoque.setProduto(produto);
            estoque.setQuantidade(rs.getInt("quantidade"));
            return estoque;
        }else{
            throw new ProdutoForaDeEstoqueException("Produto indisponível!!!");
        }
        }catch(DAOException | SQLException |ProdutoForaDeEstoqueException e){
            e.printStackTrace();
            return null;
        }
        
    }
    
    @Override
    public Class<EstoqueProduto> getTipoClasse() {
        return EstoqueProduto.class;
    }

    @Override
    public void atualiarDados(EstoqueProduto entity, EstoqueProduto entityCadastrado) {
        entityCadastrado.setCadastro(entity.getCadastro());
    }

    @Override
    protected String getQueryInsercao() {
        StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO tb_estoque_produto ");
		sb.append("(ID, cadastro, id_produto_fk, quantidade)");
		sb.append("VALUES (nextval('sq_estoque'),?,?,?)");
		return sb.toString();
    }

    @Override
    protected String getQueryExclusao() {
        return "DELETE FROM tb_estoque_produto WHERE CADASTRO = ?";
    }

    @Override
    protected String getQueryAtualizacao() {
        StringBuilder sb = new StringBuilder();
		sb.append("UPDATE tb_estoque_produto ");
		sb.append("SET cadastro = ?,");
		sb.append("id_produto_fk = ?,");
		sb.append("quantidade = ?");
		sb.append(" WHERE cadastro = ?");
		return sb.toString();
    }

    @Override
    protected void setParametrosQueryInsercao(PreparedStatement stmInsert, EstoqueProduto entity) throws SQLException {
        stmInsert.setLong(1, entity.getCadastro());
		stmInsert.setLong(2, entity.getProduto().getId());
        stmInsert.setInt(3, entity.getQuantidade());
    }

    @Override
    protected void setParametrosQueryExclusao(PreparedStatement stmDelete, Long valor) throws SQLException {
        stmDelete.setLong(1, valor);    
    }

    @Override
    protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, EstoqueProduto entity)
            throws SQLException {
        stmUpdate.setLong(1, entity.getCadastro());
		stmUpdate.setLong(2, entity.getProduto().getId());
		stmUpdate.setLong(3, entity.getQuantidade());
        stmUpdate.setLong(4, entity.getCadastro());
    }

    @Override
    protected void setParametrosQuerySelect(PreparedStatement stmUpdate, Long valor) throws SQLException {
        stmUpdate.setLong(1, valor);
    }

    @Override
    public EstoqueProduto consultar(Long cadastro){
        try{
            Connection connection = getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM tb_estoque_produto WHERE cadastro = ?");
            stm.setLong(1, cadastro);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                EstoqueProduto estoque = new EstoqueProduto();
                estoque.setId(rs.getLong("ID"));
                estoque.setCadastro(rs.getLong("cadastro"));
                estoque.setQuantidade(rs.getInt("quantidade"));
                return estoque;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<EstoqueProduto> buscarTodos() throws DAOException {
        try {
            Connection connection = getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM tb_estoque_produto");
            ResultSet rs = stm.executeQuery();
            List<EstoqueProduto> list = new ArrayList<>();
            while(rs.next()){
                EstoqueProduto estoque = new EstoqueProduto();
                estoque.setId(rs.getLong("ID"));
                estoque.setCadastro(rs.getLong("cadastro"));
                estoque.setQuantidade(rs.getInt("quantidade"));
                list.add(estoque);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
}
