package com.rpires;

import org.junit.Before;
import org.junit.Test;

import com.projetodois.domain.Cliente;
import com.projetodois.exception.TipoChaveNaoEncontradaException;
import com.projetodois.DAO.IClienteDAO;
import com.rpires.DAO.ClienteDAOMock;

import junit.framework.Assert;

public class ClienteDaoTest {

        private IClienteDAO clienteDAO;
        private Cliente cliente;

    public ClienteDaoTest() {
        clienteDAO = new ClienteDAOMock();
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
    public void pesquisarCliente() {
        Cliente clienteConsultado = clienteDAO.consultar(cliente.getCPF());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException{
        Assert.assertTrue(clienteDAO.cadastrar(cliente));
    }

    @Test
    public void excluirCliente() {
        clienteDAO.excluir(cliente.getCPF());
        // Implement test logic here
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setNome("Rodrigo Pires");
        clienteDAO.alterar(cliente);
        Assert.assertEquals("Rodrigo Pires", cliente.getNome());
    }

}
