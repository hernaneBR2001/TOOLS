package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.TipoConfig;
import br.com.dbatools.factory.ConexaoFactory;

public class TipoConfigDAO {
	
	public void salvar(TipoConfig p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tb_tipo_config ");
		sql.append(" (cod_tipo,tipo) ");
		sql.append(" values (seq_tipo_config.nextval,?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, p.getTipo());

		comando.executeUpdate();

	}
	
	public ArrayList<TipoConfig> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();

                sql.append(" SELECT cod_tipo,tipo ");
                sql.append(" FROM tb_tipo_config ");
                sql.append(" order by 2 ");
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<TipoConfig> itens = new ArrayList<TipoConfig>();

		while (resultado.next()) {
			
            TipoConfig u = new TipoConfig();
			
            u.setCod_tipo(resultado.getLong("COD_TIPO"));
            u.setTipo(resultado.getString("TIPO"));
			
            itens.add(u);

		}
		return itens;

	}

	public void excluir(TipoConfig p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tb_tipo_config ");
		sql.append("WHERE cod_tipo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCod_tipo());

		comando.executeUpdate();

	}

    public void editar(TipoConfig p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE tb_tipo_config ");
	sql.append("set tipo = ?  ");
	sql.append("WHERE ");
	sql.append("cod_tipo = ? ");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());

    comando.setString(1, p.getTipo());
	comando.setLong(2, p.getCod_tipo());
	
	comando.executeUpdate();

}
	

}
