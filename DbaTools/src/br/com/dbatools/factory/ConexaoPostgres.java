package br.com.dbatools.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;



import br.com.dbatools.factory.ConexaoPostgres;

public class ConexaoPostgres {
	String driver = "org.postgresql.Driver";
	private static final String USUARIO = "postgres";
	private static final String SENHA = "Algar2014";
	private static final String URL = "jdbc:postgresql://10.51.29.101:5432/cmdbuild";
	static Connection conexao = null;

	public static Connection conectar() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
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
			conexao = ConexaoPostgres.conectar();
			System.out.println("Conexao realizada com sucesso");
		} catch (SQLDataException ex) {
			System.out.println("Falha Conexao");
		}
	}
}
