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
import br.com.dbatools.domain.CredencialUsuario;
import br.com.dbatools.factory.ConexaoFactory;

public class CredencialUsuarioDAO {
	
	public void salvar(CredencialUsuario p,String user,String computador) throws SQLException {
		StringBuilder sql = new StringBuilder();
		int i = 1;
		sql.append("INSERT INTO tb_credencial_usuario ");
		sql.append(" (cod_credencial,cod_usuario,nom_instalacao,caminho,comando,cod_tipo) ");
		sql.append(" values (seq_credencial_usuario.nextval,(select cod_usuario from tb_usuario where usuario = ? ),?,?,?,(select cod_tipo from tb_tipo_config where tipo = ?)) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(i++, user);
		
		//comando.setLong(1, p.getUsuario().getCod_usuario());
		comando.setString(2, p.getNomInstalacao());
		comando.setString(3, p.getCaminho());
		comando.setString(4, p.getComando());
		//comando.setLong(5, p.getTipoconfig().getCod_tipo());
		comando.setString(5, computador);
		comando.executeUpdate();
		ConexaoFactory.fecharConexao();

	}
	
	public ArrayList<CredencialUsuario> listar(String user, String computador) throws SQLException {
		StringBuilder sql = new StringBuilder();
		int i = 1;
                sql.append(" select distinct b.cod_credencial,u.cod_usuario, ");
                sql.append(" substr(u.nom_usuario,1,instr(u.nom_usuario,' ')-1)||' '|| ");
                sql.append(" substr(u.nom_usuario, instr(u.nom_usuario, ' ') + 1, instr(substr(u.nom_usuario, instr(u.nom_usuario, ' ') + 1, length(u.nom_usuario)), ' ') - 1) ");
                sql.append(" nom_usuario, ");
                sql.append(" b.nom_instalacao nomInstalacao,b.caminho,b.comando,t.tipo,t.cod_tipo ");
                sql.append(" from tb_credencial_usuario b,tb_usuario u,tb_tipo_config t ");
                sql.append(" where ");
                sql.append(" b.cod_usuario = u.cod_usuario and ");
                sql.append(" b.cod_tipo = t.cod_tipo and ");
                sql.append(" b.cod_usuario = (select cod_usuario from tb_usuario where usuario = ? ) and ");
                sql.append(" b.cod_tipo = (select cod_tipo from tb_tipo_config where tipo = ? ) ");
                sql.append(" order by b.nom_instalacao,t.cod_tipo ");
                

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(i++, user);
		comando.setString(i++, computador);

		ResultSet resultado = comando.executeQuery();

		ArrayList<CredencialUsuario> itens = new ArrayList<CredencialUsuario>();

		while (resultado.next()) {
			
            CredencialUsuario u = new CredencialUsuario();
			
            u.setCod_credencial(resultado.getLong("cod_credencial"));
            u.setCaminho(resultado.getString("caminho"));
            u.setComando(resultado.getString("comando"));
            u.setNomInstalacao(resultado.getString("nomInstalacao"));  
            
            Usuario a = new Usuario();
            
            a.setCod_usuario(resultado.getLong("cod_usuario"));
            a.setNom_usuario(resultado.getString("nom_usuario"));
            
            
            
            TipoConfig c = new TipoConfig();
            
            c.setCod_tipo(resultado.getLong("cod_tipo"));
            c.setTipo(resultado.getString("tipo"));
            
            u.setUsuario(a);
            u.setTipoconfig(c);
            

            itens.add(u);

		}
		return itens;

	}

	public void excluir(CredencialUsuario p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tb_credencial_usuario ");
		sql.append("WHERE cod_credencial = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCod_credencial());

		comando.executeUpdate();
		ConexaoFactory.fecharConexao();

	}

    public void editar(CredencialUsuario p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE tb_credencial_usuario ");
	sql.append("set nom_instalacao = ?, caminho = ? ,comando = ?  ");
	sql.append("WHERE ");
	sql.append("cod_credencial = ? ");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());
    comando.setString(1,p.getNomInstalacao());
    comando.setString(2, p.getCaminho());
    comando.setString(3, p.getComando());
    comando.setLong(4, p.getCod_credencial());
	
	comando.executeUpdate();
	ConexaoFactory.fecharConexao();

}
	

}
