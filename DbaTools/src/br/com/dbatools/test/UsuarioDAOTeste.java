package br.com.dbatools.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import br.com.dbatools.dao.CmdbPostgresDAO;
import br.com.dbatools.domain.CmdbPostgres;



public class UsuarioDAOTeste {

	@Test
	public void listar() throws SQLException {
		CmdbPostgresDAO dao = new CmdbPostgresDAO();
		
		ArrayList<CmdbPostgres> lista = dao.listar();
		
		for(CmdbPostgres p : lista ){
			
			System.out.println(p.getIp());
			System.out.println("");
			System.out.println(p.getContato1());
			System.out.println("");
		}
	}


}
