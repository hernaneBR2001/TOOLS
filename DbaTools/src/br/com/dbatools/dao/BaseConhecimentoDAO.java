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
import br.com.dbatools.domain.BaseConhecimento;
import br.com.dbatools.domain.Cmdb;
import br.com.dbatools.factory.ConexaoFactory;

public class BaseConhecimentoDAO {
	
	public void salvar(BaseConhecimento p,String user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		int i = 1;
		sql.append("INSERT INTO TB_CONHECIMENTO ");
		sql.append(" (cod_conhecimento,cod_empresa,servidor,database,titulo_doc,link) ");
		sql.append(" values (seq_conhecimento.nextval,(select cod_empresa from tb_usuario where usuario = ? ),?,?,?,?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(i++, user);
        //comando.setLong(1, p.getEmpresa().getCod_empresa());
        comando.setString(2, p.getServidor().getServidor());
        comando.setString(3, p.getDatabase().getDatabase());
        comando.setString(4, p.getTitulo_doc());
        comando.setString(5, p.getLink());
        
     
        
	   comando.executeUpdate();
	   ConexaoFactory.fecharConexao();

	}
	
	public ArrayList<BaseConhecimento> listar(String user, String computador) throws SQLException {
		StringBuilder sql = new StringBuilder();
		int i = 1;
		sql.append(" select a.cod_conhecimento, ");
		sql.append("        a.nom_empresa, ");
		sql.append("        a.cod_empresa, ");
		sql.append("        a.servidor, ");
		sql.append("        a.database, ");
		sql.append("        a.titulo_doc, ");
		sql.append("        a.link, ");
		sql.append("        a.cod_usuario, ");
		sql.append("        a.cod_tipo, ");
		sql.append("        a.comando2, ");
		sql.append("        a.comando3, ");
		sql.append("        a.comando4 ");
		sql.append(" from vw_conhecimento a ");
		sql.append(" where ");
		sql.append(" a.cod_empresa = (select cod_empresa from tb_usuario where usuario = ? ) ");
		sql.append("and cod_usuario = (select cod_usuario from tb_usuario where usuario = ? )");
		sql.append("and cod_tipo = (select cod_tipo from tb_tipo_config where tipo = ? ) ");
		sql.append(" order by a.servidor,a.database,a.titulo_doc");
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(i++, user);
		comando.setString(i++, user);
		comando.setString(i++, computador);
		
		ResultSet resultado = comando.executeQuery();

		ArrayList<BaseConhecimento> itens = new ArrayList<BaseConhecimento>();

		while (resultado.next()) {
			
            BaseConhecimento u = new BaseConhecimento();
			u.setCod_conhecimento(resultado.getLong("cod_conhecimento"));
			u.setTitulo_doc(resultado.getString("titulo_doc"));
            u.setLink(resultado.getString("link"));
            u.setComando2(resultado.getString("comando2"));
            u.setComando3(resultado.getString("comando3"));
            u.setComando4(resultado.getString("comando4"));
            u.setCod_tipo(resultado.getString("cod_tipo"));
            
            
            Cmdb p = new Cmdb();
            p.setServidor(resultado.getString("servidor"));

            Cmdb r = new Cmdb();
            r.setDatabase(resultado.getString("database"));
            
            
            Empresa e = new Empresa();
            e.setCod_empresa(resultado.getLong("cod_empresa"));
            e.setNom_empresa(resultado.getString("nom_empresa"));
            
            u.setServidor(p);
            u.setDatabase(r);
            u.setEmpresa(e);
            
            itens.add(u);
            
		}
		return itens;
		
	}

	
	

	
	
	public void excluir(BaseConhecimento p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TB_CONHECIMENTO ");
		sql.append("WHERE cod_conhecimento = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCod_conhecimento());
	
		comando.executeUpdate();
		ConexaoFactory.fecharConexao();

	}

    public void editar(BaseConhecimento p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE TB_CONHECIMENTO ");
	sql.append("set cod_empresa = ? ,servidor = ? ,database = ? ,titulo_doc = ? ,link = ?  ");
	sql.append("WHERE ");
	sql.append("cod_conhecimento = ? ");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());

    comando.setLong(1, p.getEmpresa().getCod_empresa());
    comando.setString(2, p.getServidor().getServidor());
    comando.setString(3, p.getDatabase().getDatabase());
    comando.setString(4, p.getTitulo_doc());
    comando.setString(5, p.getLink());
    
    
    comando.setLong(6, p.getCod_conhecimento());
	
	comando.executeUpdate();
	ConexaoFactory.fecharConexao();

}
	

}
