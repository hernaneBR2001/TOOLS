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
import br.com.dbatools.domain.CadastroDbAppServidor;
import br.com.dbatools.domain.Cmdb;
import br.com.dbatools.factory.ConexaoFactory;

public class CadastroDbAppServidorDAO {
	
	public void salvar(CadastroDbAppServidor p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TB_CADASTRO_DB_APP ");
		sql.append(" (cod_cadastro,tipo,nom_cadastro,usuario_bd_app,senha_bd_app) ");
		sql.append(" values (seq_usuario_app.nextval,'SERVIDOR', ? , ? ,enc_people.enc_SENHA(?,seq_usuario_app.CURRVAL,'OpenSesame')) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

	
        comando.setString(1, p.getCmdb().getServidor());
        comando.setString(2, p.getUsuario_bd_app());
        comando.setString(3, p.getSenha_bd_app());
     
        
	   comando.executeUpdate();

	}
	
	public ArrayList<CadastroDbAppServidor> listar(String user, String computador) throws SQLException {
		StringBuilder sql = new StringBuilder();
		int i = 1;
		sql.append(" SELECT distinct a.cod_cadastro, b.cod_planilha, b.ip, ");
		sql.append("        a.tipo, ");
		sql.append("        a.nom_cadastro, ");
		sql.append("        a.usuario_bd_app, ");
		sql.append("        enc_people.dec_senha(a.senha_bd_app, A.cod_cadastro,'OpenSesame') senha_bd_app ");
		sql.append("  FROM TB_CADASTRO_DB_APP A, TB_PLANILHA B ");
		sql.append(" WHERE A.NOM_CADASTRO = B.SERVIDOR ");
		sql.append("and b.cod_empresa = (select cod_empresa from tb_usuario where usuario = ?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(i++, user);
		
		ResultSet resultado = comando.executeQuery();

		ArrayList<CadastroDbAppServidor> itens = new ArrayList<CadastroDbAppServidor>();

		while (resultado.next()) {
			
            CadastroDbAppServidor u = new CadastroDbAppServidor();
			
            u.setCod_cadastro(resultado.getLong("cod_cadastro"));
            u.setTipo(resultado.getString("tipo"));
            u.setUsuario_bd_app(resultado.getString("usuario_bd_app"));
            u.setSenha_bd_app(resultado.getString("senha_bd_app"));

            Cmdb p = new Cmdb();
            p.setCod_planilha(resultado.getLong("cod_planilha"));
            p.setServidor(resultado.getString("nom_cadastro"));
            p.setIp(resultado.getString("ip"));
            
            u.setCmdb(p);
            
            itens.add(u);

		}
		return itens;

	}

	public void excluir(CadastroDbAppServidor p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TB_CADASTRO_DB_APP ");
		sql.append("WHERE cod_cadastro = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCod_cadastro());
	
		comando.executeUpdate();

	}

    public void editar(CadastroDbAppServidor p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE TB_CADASTRO_DB_APP ");
	sql.append("set tipo = ?, nom_cadastro = ?, usuario_bd_app = ?, senha_bd_app = enc_people.ENC_SENHA(?,?,'OpenSesame')  ");
	sql.append("WHERE ");
	sql.append("cod_cadastro = ? ");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());

    comando.setString(1, p.getTipo());
    comando.setString(2, p.getCmdb().getServidor());
    comando.setString(3, p.getUsuario_bd_app());
    comando.setString(4, p.getSenha_bd_app());
    comando.setLong(5, p.getCod_cadastro());
    comando.setLong(6, p.getCod_cadastro());
	
	comando.executeUpdate();

}
	

}
