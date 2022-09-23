package test;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.naming.NamingContext;

public class DataBase {

	public Connection conn;
	public DataSource ds;
	
	public DataBase() throws NamingException, SQLException {
		InitialContext ctx = new InitialContext();
		NamingContext envCtx = (NamingContext) ctx.lookup("java:comp/env");
		ds = (DataSource) envCtx.lookup("jdbc/usuarios");
		conn = ds.getConnection();
	}
	
	public void closeConn() throws SQLException {
		conn.close();
	}
	
		
	
}