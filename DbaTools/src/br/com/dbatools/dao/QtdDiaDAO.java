package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.QtdDia;
import br.com.dbatools.factory.ConexaoFactory;

public class QtdDiaDAO {
	
	public void salvar(QtdDia p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tb_qtd_dias ");
		sql.append(" (qtidade_dias,qtd_dias) ");
		sql.append(" values (seq_qtd_dias.nextval,?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
       
		comando.setLong(1, p.getQtd_dias());
		

		
		comando.executeUpdate();

	}
	
	public ArrayList<QtdDia> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();

                sql.append(" SELECT  qtidade_dias,qtd_dias ");
                sql.append(" FROM tb_qtd_dias order by 2 ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<QtdDia> itens = new ArrayList<QtdDia>();

		while (resultado.next()) {
			
                        
			QtdDia u = new QtdDia();

			u.setQtidade_dias(resultado.getLong("qtidade_dias"));
            u.setQtd_dias(resultado.getLong("qtd_dias"));			
			
            itens.add(u);

		}
		return itens;

	}

	public void excluir(QtdDia p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tb_qtd_dias ");
		sql.append("WHERE qtidade_dias = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getQtidade_dias());

		comando.executeUpdate();

	}

    public void editar(QtdDia p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE tb_qtd_dias ");
	sql.append("set qtd_dias = ?  ");
	sql.append("WHERE ");
	sql.append("qtidade_dias = ?");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());

    comando.setLong(1, p.getQtd_dias());
	
	comando.setLong(2, p.getQtidade_dias());
	
	comando.executeUpdate();

}
	

}
