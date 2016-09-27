package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


import br.com.dbatools.domain.Perfil;
import br.com.dbatools.factory.ConexaoFactory;

public class PerfilDAO {
	public void salvar(Perfil f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TB_Perfil ");
		sql.append(" (COD_PERFIL,Nom_Perfil) ");
		sql.append(" values (seq_perfil.nextval,?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getNom_perfil());
		

		comando.executeUpdate();

	}

	public void excluir(Perfil f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TB_Perfil ");
		sql.append("where COD_PERFIL = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, f.getCod_perfil());

		comando.executeUpdate();

	}

	public void editar(Perfil f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE TB_Perfil ");
		sql.append("set nom_perfil = ?  ");
		sql.append(" where cod_perfil = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getNom_perfil());
		comando.setLong(2, f.getCod_perfil());

		comando.executeUpdate();

	}

	public Perfil buscarPorCodigo(Perfil f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select COD_PERFIL,Nom_Perfil ");
		sql.append("from TB_Perfil ");
		sql.append("where COD_PERFIL = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCod_perfil());

		ResultSet resultado = comando.executeQuery();

		Perfil retorno = null;

		if (resultado.next()) {
			retorno = new Perfil();
			retorno.setCod_perfil(resultado.getLong("COD_PERFIL"));
			retorno.setNom_perfil(resultado.getString("NOM_PERFIL"));
			
		}

		return retorno;
	}

	public ArrayList<Perfil> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COD_PERFIL,Nom_Perfil ");
		sql.append("FROM TB_Perfil ");
		sql.append("ORDER BY NOM_PERFIL ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Perfil> lista = new ArrayList<Perfil>();

		while (resultado.next()) {
			Perfil f = new Perfil();
			f.setCod_perfil(resultado.getLong("COD_PERFIL"));
			f.setNom_perfil(resultado.getString("NOM_PERFIL"));
			
         
			lista.add(f);
		}
		return lista;
	}

	public ArrayList<Perfil> buscarPorDescricao(Perfil f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COD_PERFIL,Nom_Perfil ");
		sql.append("FROM TB_Perfil ");
		sql.append("WHERE NOM_PERFIL like ? ");
		sql.append("ORDER BY NOM_PERFIL ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + f.getNom_perfil() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Perfil> lista = new ArrayList<Perfil>();

		while (resultado.next()) {
			Perfil item = new Perfil();
			item.setCod_perfil(resultado.getLong("COD_PERFIL"));
			item.setNom_perfil(resultado.getString("NOM_PERFIL"));
			

			lista.add(item);
		}
		return lista;
	}

	public static void main(String[] args) {
		
		  Perfil f1 = new Perfil(); 
		  f1.setNom_perfil("MARCIO");
		  
		  
		  Perfil f2 = new Perfil(); 
		  f2.setNom_perfil("ALGAR NOVO");
		  
		  
		  PerfilDAO fdao = new PerfilDAO();
		  
		  try { fdao.salvar(f1); fdao.salvar(f2); System.out.println(
		  "Os Perfils foram salvos com sucesso!"); } catch (SQLException e)
		  { // TODO Auto-generated catch block System.out.println(
			  System.out.println("Ocorreu um erro ao tentar Salvar um dos Perfils");
		  e.printStackTrace(); 
		  }
		 
		/* 
		 Perfil f1 = new Perfil(); f1.setCod_perfil(7L);
		 
		 Perfil f2 = new Perfil(); f2.setCod_perfil(6L);
		 
		 PerfilDAO fdao = new PerfilDAO();
		 
		 try { fdao.excluir(f1); fdao.excluir(f2); System.out.println(
		 "Os Perfils foram removidos com sucesso"); } catch (SQLException
		 e) { System.out.println(
		 "Ocorreu um erro ao tentar remover um dos Perfils");
		 e.printStackTrace(); }
		 
		 
		 
		/* Perfil f1 = new Perfil(); 
		 f1.setCod_perfil(1l);
		 f1.setNom_Perfil("ALGAR HERNANE");
		 f1.setCnpj("002.003.006-78");
		 
		 PerfilDAO fdao = new PerfilDAO();
		 
		 try { fdao.editar(f1); System.out.println(
		 "O Perfil foi editado com sucesso!"); } catch (SQLException e) {
		 System.out.println("Ocorreu um erro ao tentar editar o Perfil!");
		 e.printStackTrace(); }
		 
		  
		/*  Perfil f1 = new Perfil(); f1.setCod_perfil(1L);
		  
		  Perfil f2 = new Perfil(); f2.setCod_perfil(1L);
		  
		  PerfilDAO fdao = new PerfilDAO();
		  
		  try { Perfil f3 = fdao.buscarPorCodigo(f1); Perfil f4 =
		  fdao.buscarPorCodigo(f2);
		  
		  System.out.println("Resultado 1: " + f3); System.out.println(
		  "Resultado 2: " + f4);
		  
		  } catch (SQLException e) { System.out.println(
		  "Ocorreu um erro ao tentar pesquisar um dos Perfils!");
		  e.printStackTrace(); }
		 
	
		
		  PerfilDAO fdao = new PerfilDAO();
		  
		  try { ArrayList<Perfil> lista = fdao.listar(); for(Perfil f :
		  lista){ System.out.println("Resultado: "+f); } } catch (SQLException
		  e) { System.out.println(
		  "Ocorreu um erro ao tentar listar os Perfils!");
		  e.printStackTrace(); }
		 

		Perfil f1 = new Perfil();
		f1.setNom_Perfil("ALGAR");
		

		PerfilDAO fdao = new PerfilDAO();

		ArrayList<Perfil> lista;
		try {
			lista = fdao.buscarPorDescricao(f1);

			for (Perfil f : lista) {
				System.out.println("Resultado: " + f);

			}

		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar pesquisar um Perfil!");
			e.printStackTrace();
		}

*/
	}

}
