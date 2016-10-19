package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ManagedBean
public class GenericDAO {
	@Resource(name="java:/comp/env/jdbc/dbatools")
	private DataSource dbatoolsDS;
	private Connection con;
	
	/**
	 * Retorna uma conexão sem auto commit
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException{
		if (con == null){
			con = dbatoolsDS.getConnection();
			con.setAutoCommit(false);
		}
		return con;
	}
	
	public Connection getConnection(boolean autoCommit) throws SQLException{
		if (con == null){
			con = dbatoolsDS.getConnection();
			con.setAutoCommit(autoCommit);
		}
		return con;
	}	
	
	public GenericDAO(){
		try {
			Context ctx = new InitialContext();
			dbatoolsDS = (DataSource)ctx.lookup("java:/comp/env/jdbc/dbatools");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void commit() throws SQLException{
		con.commit();
	}
	
	public void rollback() throws SQLException{
		con.rollback();
	}
}
