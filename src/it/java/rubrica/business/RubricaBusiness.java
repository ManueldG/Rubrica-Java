package it.java.rubrica.business;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.MysqlDataSource;

import it.java.rubrica.model.Contatto;


public class RubricaBusiness {
	
	private Connection conn;
	private static RubricaBusiness rb;
	
	public static RubricaBusiness getInstance() {
		if(rb==null) {
			rb = new RubricaBusiness();
		}
		return rb;      
	}
	
	private Connection getConnections() throws SQLException{
		
		if(conn == null) {
			
			MysqlDataSource dataSource = new MysqlDataSource();
			
			dataSource.setServerName("localhost");
			dataSource.setPortNumber(3306);
			dataSource.setUser("root");
			dataSource.setPassword("root");
			dataSource.setDatabaseName("rubrica");
			
			conn = dataSource.getConnection();
			
			}
		
		return conn;
		
	}
	
	public int aggiungiContatto(Contatto c) throws SQLException {
		
		String sql = "INSERT INTO contatti(nome,cognome,telefono) VALUES(?,?,?) ";
		PreparedStatement ps = getConnections().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); 
		
		ps.setString(1, c.getNome());
		ps.setString(2, c.getCognome());
		ps.setString(3, c.getTelefono());
		
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		
		return rs.getInt(1);
		
	}
	
}
