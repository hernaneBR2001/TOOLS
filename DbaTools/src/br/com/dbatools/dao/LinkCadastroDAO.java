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
import br.com.dbatools.domain.LinkCadastro;
import br.com.dbatools.factory.ConexaoFactory;

public class LinkCadastroDAO {
	
	public void salvar(LinkCadastro p,String user, String computador) throws SQLException {
		int i = 1;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tb_link_cadastro ");
		sql.append(" (cod_link_cadastro,cod_usuario,descricao_link_cadastro,link_cadastro,cod_tipo) ");
		sql.append(" values (seq_link_cadastro.nextval,(select cod_usuario from tb_usuario where usuario = ? ),?,?,(select cod_tipo from tb_tipo_config where tipo = ?)) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, user);
	//	comando.setLong(1, p.getUsuario().getCod_usuario());
		comando.setString(2, p.getDescricao_link_cadastro());
		comando.setString(3, p.getLink_cadastro());
		//comando.setLong(4, p.getTipoconfig().getCod_tipo());
		comando.setString(4, computador);
		

		comando.executeUpdate();

	}
	
	public ArrayList<LinkCadastro> listar(String user, String computador) throws SQLException {
		StringBuilder sql = new StringBuilder();
		int i = 1;

		sql.append(" select a.cod_link_cadastro,  ");
		sql.append("        a.cod_usuario,  ");
		sql.append("        a.nom_usuario,  ");
		sql.append("        a.descricao_link_cadastro,  ");
		sql.append("        a.link_cadastro,  ");
		sql.append("        a.cod_tipo,  ");
		sql.append("        a.tipo,  ");
		sql.append("        a.comando2,  ");
		sql.append("        a.comando3,  ");
		sql.append("        a.comando4  ");
		sql.append(" from dbatools_adm.vw_link_cadastro a  ");
        sql.append("where ");
        sql.append("   a.cod_usuario = (select cod_usuario from tb_usuario where usuario = ? ) and ");
        sql.append("   a.cod_tipo =  (select cod_tipo from tb_tipo_config where tipo = ? )  ");
        sql.append(" order by 3,4,5 ");             
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(i++, user);
		comando.setString(i++, computador);
		
		ResultSet resultado = comando.executeQuery();

		ArrayList<LinkCadastro> itens = new ArrayList<LinkCadastro>();

		while (resultado.next()) {
			
            LinkCadastro u = new LinkCadastro();
			
            u.setCod_link_cadastro(resultado.getLong("cod_link_cadastro"));
            u.setDescricao_link_cadastro(resultado.getString("descricao_link_cadastro"));
            u.setLink_cadastro(resultado.getString("link_cadastro"));
            u.setComando2(resultado.getString("comando2"));
            u.setComando3(resultado.getString("comando3"));
            u.setComando4(resultado.getString("comando4"));
            
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

	public void excluir(LinkCadastro p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tb_link_cadastro ");
		sql.append("WHERE cod_link_cadastro = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCod_link_cadastro());

		comando.executeUpdate();

	}

    public void editar(LinkCadastro p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE tb_link_cadastro ");
	sql.append("set link_cadastro = ?, descricao_link_cadastro = ?  ");
	sql.append("WHERE ");
	sql.append("cod_link_cadastro = ? ");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());

    comando.setString(1, p.getLink_cadastro());
    comando.setString(2, p.getDescricao_link_cadastro());
    comando.setLong(3, p.getCod_link_cadastro());
	
	comando.executeUpdate();

}
	

}
