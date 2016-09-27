package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.LinkDefault;
import br.com.dbatools.factory.ConexaoFactory;

public class LinkDefaultDAO {
	
	public void salvar(LinkDefault p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tb_link_default ");
		sql.append(" (cod_link_default,descricao_link_default,link_default) ");
		sql.append(" values (seq_link_default.nextval,?,?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, p.getDescricao_link_default());
		comando.setString(2, p.getLink_default());

		comando.executeUpdate();

	}
	
	public ArrayList<LinkDefault> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();

                sql.append(" SELECT cod_link_default,descricao_link_default,link_default ");
                sql.append(" FROM tb_link_default ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<LinkDefault> itens = new ArrayList<LinkDefault>();

		while (resultado.next()) {
			
            LinkDefault u = new LinkDefault();
			
            u.setCod_link_default(resultado.getLong("cod_link_default"));
            u.setDescricao_link_default(resultado.getString("descricao_link_default"));
            u.setLink_default(resultado.getString("link_default"));
            
            itens.add(u);

		}
		return itens;

	}

	public void excluir(LinkDefault p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tb_link_default ");
		sql.append("WHERE cod_link_default = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCod_link_default());

		comando.executeUpdate();

	}

    public void editar(LinkDefault p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE tb_link_default ");
	sql.append("set descricao_link_default = ? ,link_default = ?  ");
	sql.append("WHERE ");
	sql.append("cod_link_default = ? ");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());

    comando.setString(1, p.getDescricao_link_default());
    comando.setString(2, p.getLink_default());
    comando.setLong(3, p.getCod_link_default());
	
	comando.executeUpdate();

}
	

}
