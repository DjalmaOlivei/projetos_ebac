package br.com.rpires;

import br.com.rpires.dao.generic.jdbc.ConnectionFactory;
import junit.framework.Assert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

public class ConnectionTest {

    @Test
    public void testConnection() {
        System.out.println("Iniciando conexão...");
        
        Connection connection = null;
        try{
        connection = ConnectionFactory.getConnection();
        //Assert.assertNotNull(connect);
        //connect.close();
        }catch(Exception ex)
            {
                ex.printStackTrace();
            }
            try{

                PreparedStatement stm = connection.prepareStatement("SELECT * FROM TB_CLIENTE;" );
			    ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString("NOME"));
                    System.out.println(rs.getString("ID"));
                    
                }

            }catch(Exception ex)
            {
                
            }
        
    }

}
