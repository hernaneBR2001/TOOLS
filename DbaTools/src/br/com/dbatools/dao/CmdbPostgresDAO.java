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
import br.com.dbatools.domain.CmdbPostgres;
import br.com.dbatools.factory.ConexaoPostgres;

public class CmdbPostgresDAO {
	
	public void salvar(CmdbPostgres p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO view_cmdb_dba_tools ");
		sql.append(" (OPCAO,SERVIDOR,IP,DATABASE,TIPO,USERSO,REFERENCIA,VERSAO_SO,CONTATO1,CONTATO2,CONTATO3) ");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?) ");

		Connection conexao = ConexaoPostgres.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

	
        comando.setString(1, p.getOpcao());
        comando.setString(2, p.getServidor());
        comando.setString(3, p.getIp());
        comando.setString(4, p.getDatabase());
        comando.setString(5, p.getTipo());
        comando.setString(6, p.getUserso());
        comando.setString(7, p.getReferencia());
        comando.setString(8, p.getVersao_so());
        comando.setString(9, p.getContato1());
        comando.setString(10,p.getContato2());
        comando.setString(11,p.getContato3());
        
        
		comando.executeUpdate();

	}
	
	public ArrayList<CmdbPostgres> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();


		sql.append (" SELECT   *    ");
		sql.append ("  FROM  view_cmdb_dba_tools  ");

		Connection conexao = ConexaoPostgres.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<CmdbPostgres> itens = new ArrayList<CmdbPostgres>();

		while (resultado.next()) {
			
            CmdbPostgres u = new CmdbPostgres();
			
            u.setOpcao(resultado.getString("OPCAO"));
            u.setServidor(resultado.getString("SERVIDOR"));
            u.setIp(resultado.getString("IP"));
            u.setDatabase(resultado.getString("DATABASE"));
            u.setTipo(resultado.getString("TIPO"));
            u.setUserso(resultado.getString("USERSO"));  
            u.setReferencia(resultado.getString("REFERENCIA"));
            u.setVersao_so(resultado.getString("VERSAO_SO"));
            u.setContato1(resultado.getString("CONTATO1"));
            u.setContato2(resultado.getString("CONTATO2"));
            u.setContato3(resultado.getString("CONTATO3"));
            itens.add(u);

		}
		return itens;

	}

	public void excluir(CmdbPostgres p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM view_cmdb_dba_tools ");
		sql.append("WHERE DATABASE = ? ");

		Connection conexao = ConexaoPostgres.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDatabase());
		
		

		comando.executeUpdate();

	}

    public void editar(CmdbPostgres p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE view_cmdb_dba_tools ");
	sql.append("set SERVIDOR = ? ,IP = ? ,VERSAO_SO = ? ,DATABASE = ? ,VERSAO_DATABASE = ? ,TIPO_AMBIENTE = ? ,SGDB = ? ,REFERENCIA = ? ,CONTATO1 = ? ,CONTATO2 = ? ,CONTATO3 = ?  ");
	sql.append("WHERE ");
	sql.append("DATABASE = ? ");

	Connection conexao = ConexaoPostgres.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());

    comando.setString(1, p.getServidor());
    comando.setString(2, p.getIp());
    comando.setString(3, p.getVersao_so());
    comando.setString(4, p.getDatabase());
    comando.setString(5, p.getUserso());
    comando.setString(6, p.getTipo());
    comando.setString(7, p.getOpcao());
    comando.setString(8, p.getReferencia());
    comando.setString(9, p.getContato1());
    comando.setString(10, p.getContato2());
    comando.setString(11, p.getContato3());
    
    
    comando.setString(12, p.getDatabase());
	
	comando.executeUpdate();

}
	

}
