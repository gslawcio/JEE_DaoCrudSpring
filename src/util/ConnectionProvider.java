package util;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {

	private static DataSource dataSource;
	
	
	public static java.sql.Connection getConnection() throws SQLException {
		return  getDSInstance().getConnection();
	}
	
	
	public static DataSource getDSInstance() {
		if(dataSource == null) {
			try {
				Context conn = new InitialContext();
				Context envConn = (Context)conn.lookup("java:comp/env");
				dataSource = (DataSource)envConn.lookup("jdbc/library");
			}catch (NamingException e) {
				e.printStackTrace();			
			}
		}
		return dataSource;
	}
}
