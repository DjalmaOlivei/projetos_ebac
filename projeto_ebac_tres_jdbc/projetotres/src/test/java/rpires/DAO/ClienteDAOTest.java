package rpires.DAO;

import com.projetotres.DAO.ClienteDAO;
import com.projetotres.DAO.IClienteDAO;
import com.projetotres.service.ClienteService;
import com.projetotres.domain.Cliente;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ClienteDAOTest {

    @Test
    public void testSomeMethod() {
        IClienteDAO clienteDAO = new ClienteDAO();
        ClienteService cs = new ClienteService(clienteDAO);
        Cliente cliente = new Cliente(12345678900L, "João Silva", "São Paulo", "SP", 11999999999L, 100);
        
        // Testar o método salvar
        try {
            Assert.assertTrue(cs.salvar(cliente) == 1);
        }catch(Exception e){
            e.printStackTrace();
        }
        // Testar o método buscarPorCPF
        Cliente result = cs.buscarPorCPF(cliente.getCPF());
        Assert.assertEquals(result.getCPF(), cliente.getCPF());
        
        long cpfAntigo = cliente.getCPF();
        cliente.setCPF(00000000000L);
        cliente.setNome("João S");  
        cliente.setCidade("rio de janeiro");
        cliente.setEstado("JJ");
        cliente.setTel(1100000000L);
        cliente.setNumero(200);
        
        //  Testar o método alterar
        try{
            Assert.assertTrue(cs.alterar(cliente, cpfAntigo) == 1);
            result = cs.buscarPorCPF(cliente.getCPF());
            Assert.assertEquals(result.getCPF(), cliente.getCPF());
        }catch(Exception e){
            e.printStackTrace();
        }

        // Testar o método buscarTodos
        List<Cliente> clientes = cs.buscarTodos();
        Assert.assertNotNull(clientes);
        clientes.stream().forEach(c -> System.out.println("Cliente encontrado: " + c.toString()));

        // Testar o método excluir
        int resultDelete = cs.excluir(cliente.getCPF());
        Assert.assertTrue(resultDelete == 1);
    }

}
