package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.InstalacaoConfig;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.TipoConfig;
import br.com.dbatools.domain.Usuario;
import br.com.dbatools.domain.DbaTools;
import br.com.dbatools.domain.Cmdb;
import br.com.dbatools.factory.ConexaoFactory;

public class DbaToolsDAO {
	
	public void salvar(DbaTools p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tb_dbatools ");
		sql.append(" (cod_dbatools,descricao,diretorio) ");
		sql.append(" values (seq_dbatools.nextval,?,?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

	
        comando.setString(1, p.getDescricao());
        comando.setString(2, p.getDiretorio());
        
     
        
	   comando.executeUpdate();
	   ConexaoFactory.fecharConexao();
	   
	}
	
	public ArrayList<DbaTools> listar(String user, String computador) throws SQLException {
		StringBuilder sql = new StringBuilder();
		int i = 1;
		sql.append(" select a.cod_dbatools, ");
		sql.append("        a.descricao, ");
		sql.append("        a.diretorio, ");
		sql.append("        a.cod_tipo, ");
		sql.append("        a.comando2 ");
		sql.append(" from vw_dbatools a ");
		sql.append("where");
		sql.append(" a.cod_tipo = (select cod_tipo from tb_tipo_config where tipo = ? ) and  ");
		sql.append(" a.cod_usuario =  (select cod_usuario from tb_usuario where usuario = ? )  ");
		sql.append(" order by a.descricao ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(i++, computador);
		comando.setString(i++, user);
		
		ResultSet resultado = comando.executeQuery();

		ArrayList<DbaTools> itens = new ArrayList<DbaTools>();

		while (resultado.next()) {
			
            DbaTools u = new DbaTools();
            u.setCod_dbatools(resultado.getLong("cod_dbatools"));
            u.setDescricao(resultado.getString("descricao"));
			u.setDiretorio(resultado.getString("diretorio"));
			u.setCod_tipo(resultado.getString("cod_tipo"));
            u.setComando2(resultado.getString("comando2"));
                        
            itens.add(u);

		}
		return itens;

	}

	
	

	
	
	public void excluir(DbaTools p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tb_dbatools ");
		sql.append("WHERE cod_dbatools = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCod_dbatools());
	
		comando.executeUpdate();
		ConexaoFactory.fecharConexao();
		
	}

    public void editar(DbaTools p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE tb_dbatools ");
	sql.append("set descricao = ? ,diretorio = ?   ");
	sql.append("WHERE ");
	sql.append("cod_dbatools = ? ");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());
    comando.setString(1, p.getDescricao());
    comando.setString(2, p.getDiretorio());
    
    
    comando.setLong(3, p.getCod_dbatools());
	
	comando.executeUpdate();
	ConexaoFactory.fecharConexao();

}
	

}
