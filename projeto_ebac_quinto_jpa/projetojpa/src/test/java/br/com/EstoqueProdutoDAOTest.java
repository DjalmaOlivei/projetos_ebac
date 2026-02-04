package br.com;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;

import br.com.domain.EstoqueProduto;
import br.com.domain.Produto;
import br.com.exceptions.DAOException;
import br.com.exceptions.MaisDeUmRegistroException;
import br.com.exceptions.TableException;
import br.com.exceptions.TipoChaveNaoEncontradaException;
import br.com.dao.EstoqueProdutoDAO;
import br.com.dao.IProdutoDAO;

import java.util.List;
import br.com.dao.ProdutoDAO;

public class EstoqueProdutoDAOTest {

    public EstoqueProdutoDAO epDAO = new EstoqueProdutoDAO();
    public IProdutoDAO produtoDao = new ProdutoDAO();

    @Test
    public void testeConsulta()throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException{
        EstoqueProduto ep = criaEstoqueProduto(criaProduto());
        epDAO.cadastrar(ep);
        Assert.assertNotNull(epDAO.consultar(ep.getCadastro()));
        String produtoid = ep.getProduto().getCodigo();
        epDAO.excluir(ep.getCadastro());
        produtoDao.excluir(produtoid);
    }

    @Test
    public void testeCadastro()throws TipoChaveNaoEncontradaException, DAOException{
        EstoqueProduto ep = criaEstoqueProduto(criaProduto());
        Assert.assertTrue(epDAO.cadastrar(ep));
        String produtoid = ep.getProduto().getCodigo();
        epDAO.excluir(ep.getCadastro());
        produtoDao.excluir(produtoid);
        
    }

    @Test
    public void testeExclusao()throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException{
        EstoqueProduto ep = criaEstoqueProduto(criaProduto());
        
        epDAO.cadastrar(ep);
        String produtoid = ep.getProduto().getCodigo();
        epDAO.excluir(ep.getCadastro());
        produtoDao.excluir(produtoid);
        Assert.assertNull(epDAO.consultar(ep.getCadastro()));

    }

    @Test
    public void testeAtualiza()throws TipoChaveNaoEncontradaException, DAOException{
        EstoqueProduto ep = criaEstoqueProduto(criaProduto());
        int quantidadeAntes = ep.getQuantidade();
        epDAO.cadastrar(ep);
        ep.setQuantidade(ep.getQuantidade()+1);
        epDAO.alterar(ep);
        Assert.assertTrue(ep.getQuantidade() != quantidadeAntes);
        String produtoid = ep.getProduto().getCodigo();
        epDAO.excluir(ep.getCadastro());
        produtoDao.excluir(produtoid);

    }

    @Test
    public void testeBuscarTodos() throws TipoChaveNaoEncontradaException, DAOException{
        EstoqueProduto ep = criaEstoqueProduto(criaProduto());
        EstoqueProduto ep2 = criaEstoqueProduto2(criaProduto2());
            epDAO.cadastrar(ep);
            epDAO.cadastrar(ep2);
            List<EstoqueProduto> resultList = (List<EstoqueProduto>)epDAO.buscarTodos();
            resultList.forEach(n -> System.out.println(n.toString()));
            Assert.assertNotNull(resultList);
            String produtoid = ep.getProduto().getCodigo();
            epDAO.excluir(ep.getCadastro());
            produtoDao.excluir(produtoid);
            String produtoid2 = ep2.getProduto().getCodigo();
            epDAO.excluir(ep2.getCadastro());
            produtoDao.excluir(produtoid2);
    }

    public Produto criaProduto()throws DAOException, TipoChaveNaoEncontradaException {
        Produto produto = new Produto();
		produto.setCodigo("A2");
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
		produto.setFabricante("fabricante 1");
		produtoDao.cadastrar(produto);
        return produto;
    }

    public Produto criaProduto2() throws TipoChaveNaoEncontradaException, DAOException{
        Produto produto2 = new Produto();
		produto2.setCodigo("A4");
		produto2.setDescricao("Produto 2");
		produto2.setNome("Produto 2");
		produto2.setValor(BigDecimal.TEN);
		produto2.setFabricante("fabricante 2");
		produtoDao.cadastrar(produto2);
        return produto2;
    }

    public EstoqueProduto criaEstoqueProduto(Produto produto){
        EstoqueProduto estoque = new EstoqueProduto();
        estoque.setCadastro(123456L);
        estoque.setProduto(produto);
        estoque.setQuantidade(10);
        return estoque;
    }

    public EstoqueProduto criaEstoqueProduto2(Produto produto){
        EstoqueProduto estoque = new EstoqueProduto();
        estoque.setCadastro(654321L);
        estoque.setProduto(produto);
        estoque.setQuantidade(50);
        return estoque;
    }


}
