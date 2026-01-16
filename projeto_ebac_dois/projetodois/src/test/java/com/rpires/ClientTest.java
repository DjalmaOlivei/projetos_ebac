package com.rpires;
import org.junit.Before;
import org.junit.Test;
import com.projetodois.domain.Cliente;
import com.projetodois.exception.TipoChaveNaoEncontradaException;
import com.projetodois.service.ClienteService;
import com.projetodois.service.IClienteService;
import com.rpires.DAO.ClienteDAOMock;
import com.projetodois.DAO.ClienteDAO;
import com.projetodois.DAO.IClienteDAO;

import junit.framework.Assert;

public class ClientTest {

    private IClienteService clienteService;
    private Cliente cliente;

    public ClientTest() {
        IClienteDAO clienteDAO = new ClienteDAOMock();
        clienteService = new ClienteService(clienteDAO); // Initialize clienteService with a mock or actual implementation
    }

    @Before
    public void init(){
        cliente = new Cliente(); // Implement test logic here
        cliente.setCPF(12345678900L);
        cliente.setNome("João Silva");
        cliente.setCidade("São Paulo");
        cliente.setEstado("SP");
        cliente.setTel(11987654321L);
        cliente.setNumero(123);

    }

    @Test
    public void pesquisarClientePorCPF() {
        Cliente clienteConsultado =  clienteService.buscarPorCPF(cliente.getCPF());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException{
        Assert.assertTrue(clienteService.salvar(cliente));
    }

    @Test
    public void excluirCliente() {
        clienteService.excluir(cliente.getCPF());
        // Implement test logic here
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setNome("Rodrigo Pires");
        clienteService.alterar(cliente);
        Assert.assertEquals("Rodrigo Pires", cliente.getNome());
    }

}
