package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dbatools.factory.ConexaoFactory;


public class UserDAO {
	 public static boolean login(String usuario, String password) {
	        Connection con = null;
	        PreparedStatement ps = null;
	        try {
	        	
	            con = ConexaoFactory.conectar();
	            ps = con.prepareStatement(
	                    "select b.usuario usuario ,enc_people.dec_senha(b.senha, b.cod_usuario, 'OpenSesame') password from tb_usuario b  where usuario = ? and enc_people.dec_senha(b.senha, b.cod_usuario, 'OpenSesame') = ? ");
	            ps.setString(1, usuario);
	            ps.setString(2, password);
	            System.out.println(ps);
	            System.out.println(usuario);
	            System.out.println(password);
	 
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) // found
	            {
	                System.out.println(rs.getString("usuario"));
	                return true;
	            }
	            else {
	                return false;
	            }
	        } catch (Exception ex) {
	            System.out.println("Error in login() -->" + ex.getMessage());
	            return false;
	        } finally {
	            
	        }
	    }
}
