package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.dbatools.domain.HostClient;
import br.com.dbatools.factory.ConexaoFactory;

public class HostClientDAO {

	public HostClient getLastLogon(String username, String host) {
		try{ 
			String sql = "select * from dbatools_adm.tb_host_client where host = ? and username = ?";
			Connection conexao = ConexaoFactory.conectar();
	
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, host);
			comando.setString(2, username);
			
			return toHostClient(comando.executeQuery());
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private HostClient toHostClient(ResultSet rs) throws SQLException{
		if (rs.next()){
			HostClient hc = new HostClient();
			hc.setIdHost(rs.getLong("idhost"));
			hc.setHost(rs.getString("host"));
			hc.setUsername(rs.getString("username"));
			hc.setPort(rs.getInt("port"));
			hc.setDtLastConnect(rs.getDate("dtlasconnect"));
			return hc;
		}
		else{
			return null;
		}
	}
}
