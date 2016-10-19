package br.com.dbatools.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;



import br.com.dbatools.factory.ConexaoFactory;

public class ConexaoFactory {
	private static final String USUARIO = "DBATOOLS_APP";
	private static final String SENHA = "DB5T8O7S#2016";
	private static final String URL = "jdbc:oracle:thin:@10.11.136.127:1521/EMREP";
	static Connection conexao = null;

	public static Connection conectar() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
    
	public static void fecharConexao(){
		
		try {
			conexao.close();
			
		} catch (SQLException e) {
			System.out.println("Falha ao fechar a Conexão");
		}
	}

	public static void main(String[] args) throws SQLException {
		try {
			conexao = ConexaoFactory.conectar();
			System.out.println("Conexao realizada com sucesso");
		} catch (SQLDataException ex) {
			System.out.println("Falha Conexao");
		}
	}
}
