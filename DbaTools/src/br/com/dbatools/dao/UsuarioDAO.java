package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.Usuario;
import br.com.dbatools.factory.ConexaoFactory;

public class UsuarioDAO {
	
	public void salvar(Usuario p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TB_USUARIO ");
		sql.append(" (cod_usuario,nom_usuario,usuario,usuarioldap,senha,telefone,email,cod_empresa,cod_perfil) ");
		sql.append(" values (seq_usuario_app.nextval,?,?,?,enc_people.ENC_SENHA(?,seq_usuario_app.currval,'OpenSesame'),?,?,?,?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, p.getNom_usuario());
		comando.setString(2, p.getUsuario());
		comando.setString(3, p.getUsuarioldap());
		comando.setString(4, p.getSenha());
		comando.setString(5, p.getTelefone());
		comando.setString(6, p.getEmail());
		comando.setLong(7, p.getEmpresa().getCod_empresa());
		comando.setLong(8, p.getPerfil().getCod_perfil());
		

		comando.executeUpdate();
		ConexaoFactory.fecharConexao();

	}
	
	public ArrayList<Usuario> listar(String user, String computador) throws SQLException {
		StringBuilder sql = new StringBuilder();
		int i = 1;
                sql.append(" SELECT U.COD_USUARIO,U.NOM_USUARIO,U.USUARIO,u.usuarioldap,enc_people.dec_senha(U.senha, U.cod_USUARIO,'OpenSesame') SENHA,U.TELEFONE,U.EMAIL, ");
                sql.append("       E.COD_EMPRESA,E.NOM_EMPRESA, ");
                sql.append("       P.COD_PERFIL,P.NOM_PERFIL ");
                sql.append(" FROM TB_USUARIO U, TB_EMPRESA E, TB_PERFIL P ");
                sql.append(" WHERE ");
                sql.append(" U.COD_EMPRESA = E.COD_EMPRESA AND ");
                sql.append(" U.COD_PERFIL = P.COD_PERFIL and ");
                sql.append(" U.COD_EMPRESA = (select cod_empresa from tb_usuario where usuario = ?) AND ");
                sql.append(" U.COD_PERFIL = (select cod_perfil from tb_usuario where usuario = ?)  ");
                sql.append(" order by 2,3 ");
                
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(i++, user);
		comando.setString(i++, user);
		ResultSet resultado = comando.executeQuery();

		ArrayList<Usuario> itens = new ArrayList<Usuario>();

		while (resultado.next()) {
			
			Empresa e = new Empresa();
			e.setCod_empresa(resultado.getLong("COD_EMPRESA"));
			e.setNom_empresa(resultado.getString("NOM_EMPRESA"));

			Perfil p = new Perfil();
			
			p.setCod_perfil(resultado.getLong("COD_PERFIL"));
			p.setNom_perfil(resultado.getString("NOM_PERFIL"));
                        
			Usuario u = new Usuario();
			
            u.setCod_usuario(resultado.getLong("COD_USUARIO"));
            u.setNom_usuario(resultado.getString("NOM_USUARIO"));
            u.setUsuario(resultado.getString("USUARIO"));
            u.setSenha(resultado.getString("SENHA"));
            u.setTelefone(resultado.getString("TELEFONE"));
            u.setEmail(resultado.getString("EMAIL"));
            u.setUsuarioldap(resultado.getString("usuarioldap"));
			
			u.setEmpresa(e);
            u.setPerfil(p);
			
            itens.add(u);

		}
		return itens;

	}

	public void excluir(Usuario p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tb_USUARIO ");
		sql.append("WHERE cod_usuario = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCod_usuario());

		comando.executeUpdate();

	}

    public void editar(Usuario p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE TB_USUARIO ");
	sql.append("set nom_usuario = ? ,usuario = ? ,usuarioldap = ?,senha = enc_people.ENC_SENHA(?,?,'OpenSesame') ,telefone = ? , email = ?, COD_EMPRESA = ? , COD_PERFIL = ? ");
	sql.append("WHERE ");
	sql.append("cod_usuario = ?");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());

    comando.setString(1, p.getNom_usuario());
	comando.setString(2, p.getUsuario());
	comando.setString(3, p.getUsuarioldap());
	comando.setString(4, p.getSenha());
	comando.setLong(5, p.getCod_usuario());
	
	comando.setString(6, p.getTelefone());
	comando.setString(7, p.getEmail());
	comando.setLong(8, p.getEmpresa().getCod_empresa());
	comando.setLong(9, p.getPerfil().getCod_perfil());
	comando.setLong(10, p.getCod_usuario());
	
	comando.executeUpdate();
	ConexaoFactory.fecharConexao();

}
	

}
