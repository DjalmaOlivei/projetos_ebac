package rpires;
import org.junit.Assert;
import org.junit.Test;
import com.projetotres.factory.ConnectionFctory;
import java.sql.Connection;

public class ConnectionTest {

    @Test
    public void testConnection() {
        
        try{
            Connection connection = ConnectionFctory.getConnection();
            Assert.assertNotNull(connection);
            connection.close();
        }catch(Exception e){
            e.printStackTrace(); 
        }   
    }

}
