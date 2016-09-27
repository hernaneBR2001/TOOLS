package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.factory.ConexaoFactory;

public class EmpresaDAO {
	public void salvar(Empresa f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TB_Empresa ");
		sql.append(" (cod_empresa,nom_empresa,cnpj) ");
		sql.append(" values (seq_empresa.nextval,?,?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getNom_empresa());
		comando.setString(2, f.getCnpj());

		comando.executeUpdate();

	}

	public void excluir(Empresa f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TB_Empresa ");
		sql.append("where cod_empresa = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, f.getCod_empresa());

		comando.executeUpdate();

	}

	public void editar(Empresa f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE TB_Empresa ");
		sql.append("set nom_empresa = ? , ");
		sql.append(" cnpj = ? ");
		sql.append(" where cod_empresa = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getNom_empresa());
		comando.setString(2, f.getCnpj());
		comando.setLong(3, f.getCod_empresa());

		comando.executeUpdate();

	}

	public Empresa buscarPorCodigo(Empresa f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select cod_empresa,nom_empresa,cnpj ");
		sql.append("from TB_Empresa ");
		sql.append("where cod_empresa = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCod_empresa());

		ResultSet resultado = comando.executeQuery();

		Empresa retorno = null;

		if (resultado.next()) {
			retorno = new Empresa();
			retorno.setCod_empresa(resultado.getLong("cod_empresa"));
			retorno.setNom_empresa(resultado.getString("nom_empresa"));
			retorno.setCnpj(resultado.getString("cnpj"));
		}

		return retorno;
	}

	public ArrayList<Empresa> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cod_empresa,nom_empresa,cnpj ");
		sql.append("FROM TB_Empresa ");
		sql.append("ORDER BY nom_empresa ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Empresa> lista = new ArrayList<Empresa>();

		while (resultado.next()) {
			Empresa f = new Empresa();
			f.setCod_empresa(resultado.getLong("cod_empresa"));
			f.setNom_empresa(resultado.getString("nom_empresa"));
			f.setCnpj(resultado.getString("cnpj"));
         
			lista.add(f);
		}
		return lista;
	}

	public ArrayList<Empresa> buscarPorDescricao(Empresa f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cod_empresa,nom_empresa,cnpj ");
		sql.append("FROM TB_Empresa ");
		sql.append("WHERE nom_empresa like ? ");
		sql.append("ORDER BY nom_empresa ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + f.getNom_empresa() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Empresa> lista = new ArrayList<Empresa>();

		while (resultado.next()) {
			Empresa item = new Empresa();
			item.setCod_empresa(resultado.getLong("cod_empresa"));
			item.setNom_empresa(resultado.getString("nom_empresa"));
			item.setCnpj(resultado.getString("cnpj"));

			lista.add(item);
		}
		return lista;
	}

	public static void main(String[] args) {
		
		/*  Empresa f1 = new Empresa(); 
		  f1.setNom_empresa("MARCIO");
		  f1.setCnpj("000.002.003-89");
		  
		  Empresa f2 = new Empresa(); 
		  f2.setNom_empresa("ALGAR NOVO");
		  f2.setCnpj("001.002.003-98");
		  
		  EmpresaDAO fdao = new EmpresaDAO();
		  
		  try { fdao.salvar(f1); fdao.salvar(f2); System.out.println(
		  "Os Empresas foram salvos com sucesso!"); } catch (SQLException e)
		  { // TODO Auto-generated catch block System.out.println(
			  System.out.println("Ocorreu um erro ao tentar Salvar um dos Empresas");
		  e.printStackTrace(); 
		  }
		 
		 
		 Empresa f1 = new Empresa(); f1.setCod_empresa(7L);
		 
		 Empresa f2 = new Empresa(); f2.setCod_empresa(6L);
		 
		 EmpresaDAO fdao = new EmpresaDAO();
		 
		 try { fdao.excluir(f1); fdao.excluir(f2); System.out.println(
		 "Os Empresas foram removidos com sucesso"); } catch (SQLException
		 e) { System.out.println(
		 "Ocorreu um erro ao tentar remover um dos Empresas");
		 e.printStackTrace(); }
		 
		 
		 
		/* Empresa f1 = new Empresa(); 
		 f1.setCod_empresa(1l);
		 f1.setNom_empresa("ALGAR HERNANE");
		 f1.setCnpj("002.003.006-78");
		 
		 EmpresaDAO fdao = new EmpresaDAO();
		 
		 try { fdao.editar(f1); System.out.println(
		 "O Empresa foi editado com sucesso!"); } catch (SQLException e) {
		 System.out.println("Ocorreu um erro ao tentar editar o Empresa!");
		 e.printStackTrace(); }
		 
		  
		/*  Empresa f1 = new Empresa(); f1.setCod_empresa(1L);
		  
		  Empresa f2 = new Empresa(); f2.setCod_empresa(1L);
		  
		  EmpresaDAO fdao = new EmpresaDAO();
		  
		  try { Empresa f3 = fdao.buscarPorCodigo(f1); Empresa f4 =
		  fdao.buscarPorCodigo(f2);
		  
		  System.out.println("Resultado 1: " + f3); System.out.println(
		  "Resultado 2: " + f4);
		  
		  } catch (SQLException e) { System.out.println(
		  "Ocorreu um erro ao tentar pesquisar um dos Empresas!");
		  e.printStackTrace(); }
		 
	
		
		  EmpresaDAO fdao = new EmpresaDAO();
		  
		  try { ArrayList<Empresa> lista = fdao.listar(); for(Empresa f :
		  lista){ System.out.println("Resultado: "+f); } } catch (SQLException
		  e) { System.out.println(
		  "Ocorreu um erro ao tentar listar os Empresas!");
		  e.printStackTrace(); }
		 

		Empresa f1 = new Empresa();
		f1.setNom_empresa("ALGAR");
		

		EmpresaDAO fdao = new EmpresaDAO();

		ArrayList<Empresa> lista;
		try {
			lista = fdao.buscarPorDescricao(f1);

			for (Empresa f : lista) {
				System.out.println("Resultado: " + f);

			}

		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar pesquisar um Empresa!");
			e.printStackTrace();
		}

*/
	}

}
