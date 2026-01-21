/**
 * 
 */
package br.com.rpires.dao.generic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author rodrigo.pires
 *
 */
public class ConnectionFactory {

	private static final Dotenv dotenv = Dotenv.configure().directory("./").load();
	
	private static Connection connection;
	
	private ConnectionFactory(Connection connection) {
		
	}
	
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = initConnection();
			return connection;
		} else if (connection.isClosed()) {
			connection = initConnection();
			return connection;
		} else {
			return connection;
		}
	}
	
	private static Connection initConnection() {
        try {
            return DriverManager.getConnection(
            	"jdbc:postgresql://"+dotenv.get("URLDATABASE"), dotenv.get("USERDATABASE"), dotenv.get("PASSWORDATABASE"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
}
