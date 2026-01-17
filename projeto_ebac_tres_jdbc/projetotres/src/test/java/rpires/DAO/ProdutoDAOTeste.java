package rpires.DAO;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.projetotres.DAO.ProdutoDAO;
import com.projetotres.DAO.IProdutoDAO;
import com.projetotres.domain.Produto;

public class ProdutoDAOTeste {

    
    @Test
    public void testSomeMethod() {
        IProdutoDAO pd = new ProdutoDAO();
        Produto p = new Produto(1,"banana",5);
        
        // Testar o método salvar
        try {
            Assert.assertTrue(pd.cadastrar(p) == 1);
        }catch(Exception e){
            e.printStackTrace();
        }
        // Testar o método buscarPorCPF
        Produto result = (Produto) pd.consultar(p.getId());
        Assert.assertEquals(result.getId(), p.getId());
        
        long idAntigo = p.getId();
        p.setId(00000000000L);
        p.setNome("BATATA");  
        p.setPreco(2);
        
        //  Testar o método alterar
        try{
            Assert.assertTrue(pd.alterar(p, idAntigo) == 1);
            result = (Produto) pd.consultar(p.getId());
            Assert.assertEquals(result.getId(), p.getId());
        }catch(Exception e){
            e.printStackTrace();
        }

        // Testar o método buscarTodos
        List<Produto> produtos = (List) pd.buscarTodos();
        Assert.assertNotNull(produtos);
        produtos.stream().forEach(c -> System.out.println("Cliente encontrado: " + c.toString()));

        // Testar o método excluir
        int resultDelete = pd.excluir(p.getId());
        Assert.assertTrue(resultDelete == 1);
    }


}
