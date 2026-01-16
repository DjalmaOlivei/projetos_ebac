package pacote.crud.com;

import pacote.crud.com.dao.*;
import pacote.crud.com.domain.*;
import pacote.crud.com.exceptions.*;

import javax.swing.*;
import java.util.Map;

public class App {

    private static IClienteDAO iClienteDAO;

    public static void main(String args[] ){
    iClienteDAO = new ClienteMapDAO();
    String opcao = "";

    while (!opcao.equals("5")){
        opcao = getOption();

        // Cadastra
        if(opcao.equals("1"))iClienteDAO.cadastrar(getInputCliente());
        //Consulta
        else if (opcao.equals("2")){
            JOptionPane.showMessageDialog(null,iClienteDAO.consultar(getCPFinput()).toString(),"Cliente",JOptionPane.INFORMATION_MESSAGE);
        }//Exclui
        else if (opcao.equals("3")) {
            Long cpf = getCPFinput();
            if(iClienteDAO.consultar(cpf) != null){
                iClienteDAO.excluir(getCPFinput());
            }else JOptionPane.showMessageDialog(null,"Cliente não encontrado","Erro",JOptionPane.ERROR_MESSAGE);
        }//Altera
        else if (opcao.equals("4")) {
            Cliente cliente = getInputCliente();
            if(iClienteDAO.consultar(cliente.getCpf()) != null){
            iClienteDAO.alterar(cliente);
            }else JOptionPane.showMessageDialog(null,"Cliente não encontrado","Erro",JOptionPane.ERROR_MESSAGE);
        //Lista
        } else if (opcao.equals("0")) {
            JOptionPane.showMessageDialog(null, iClienteDAO.buscarTodos().toString(),"Clientes",JOptionPane.INFORMATION_MESSAGE);
        }

    }




    }

    private static String getOption(){
        try{
            String option = JOptionPane.showInputDialog(null,"escolha opção:\n 0 : Mostrar Todos\n 1 : Cadastro\n 2 : Consultar\n 3 : Exclusão\n 4 : Alterar\n 5 : Sair","Cadastro", JOptionPane.INFORMATION_MESSAGE);
            if(option.equals("0")||option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4") || option.equals("5")) return option;
            throw new InvalidOptionException("Opção invalida");
        } catch (InvalidOptionException e) {
            JOptionPane.showMessageDialog(null, "Input inválido\n tente novamente","Erro",JOptionPane.ERROR_MESSAGE);
            return getOption();
        }
    }

    private static Cliente getInputCliente(){
        String[] input = JOptionPane.showInputDialog(null,"Escreva os dados 'Nome,CPF,telefone,end,número,cidade,estado' como a seguir separado por vírgula!!!\nJoão da Silva,12312312,11998765432,end,1234,cidade,SP","Cadastro",JOptionPane.INFORMATION_MESSAGE).split(",");
        Cliente cliente =null;
        try {
            cliente = new Cliente(input[0],Long.valueOf(input[1]),Long.valueOf(input[2]),input[3],Integer.valueOf(input[4]),input[5],input[6]);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Input inválido\n tente novamente","Erro",JOptionPane.ERROR_MESSAGE);
            return getInputCliente();
        }
        return cliente;
    }

    private static Long getCPFinput(){
        try{
        return Long.valueOf(JOptionPane.showInputDialog(null,"Digite o CPF!!!","Busca",JOptionPane.INFORMATION_MESSAGE));
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Apenas números","Erro",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}

