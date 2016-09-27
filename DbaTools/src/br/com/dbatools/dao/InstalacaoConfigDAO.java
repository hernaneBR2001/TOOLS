package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.InstalacaoConfig;
import br.com.dbatools.factory.ConexaoFactory;

public class InstalacaoConfigDAO {
	
	public void salvar(InstalacaoConfig p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tb_instalacao_config ");
		sql.append(" (cod_instalacao,nom_instalacao,caminho,comando) ");
		sql.append(" values (seq_instalacao_config.nextval,?,?,?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, p.getNom_instalacao());
		comando.setString(2, p.getCaminho());
		comando.setString(3, p.getComando());

		comando.executeUpdate();

	}
	
	public ArrayList<InstalacaoConfig> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();

                sql.append(" SELECT cod_instalacao,nom_instalacao,caminho,comando ");
                sql.append(" FROM tb_instalacao_config ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<InstalacaoConfig> itens = new ArrayList<InstalacaoConfig>();

		while (resultado.next()) {
			
            InstalacaoConfig u = new InstalacaoConfig();
			
            u.setCod_instalacao(resultado.getLong("cod_instalacao"));
            u.setNom_instalacao(resultado.getString("nom_instalacao"));
            u.setCaminho(resultado.getString("caminho"));
            u.setComando(resultado.getString("comando"));
            
            itens.add(u);

		}
		return itens;

	}

	public void excluir(InstalacaoConfig p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tb_instalacao_config ");
		sql.append("WHERE cod_instalacao = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCod_instalacao());

		comando.executeUpdate();

	}

    public void editar(InstalacaoConfig p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE tb_instalacao_config ");
	sql.append("set nom_instalacao = ?,caminho = ?,comando = ?  ");
	sql.append("WHERE ");
	sql.append("cod_instalacao = ? ");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());

    comando.setString(1, p.getNom_instalacao());
    comando.setString(2, p.getCaminho());
    comando.setString(3, p.getComando());
    
	comando.setLong(4, p.getCod_instalacao());
	
	comando.executeUpdate();

}
	

}
