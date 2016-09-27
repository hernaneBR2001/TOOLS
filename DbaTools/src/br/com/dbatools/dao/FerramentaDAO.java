package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.xml.internal.ws.util.StringUtils;

import br.com.dbatools.domain.Ferramenta;
import br.com.dbatools.factory.ConexaoFactory;

public class FerramentaDAO {
	
	public ArrayList<Ferramenta> listar(String user, String computador, String servidor,String ip, String database, String usuario, String programa) throws SQLException {
		StringBuilder sql = new StringBuilder();
		
		
		if (servidor == null || servidor.isEmpty() )
			 servidor="%";
		
		if (ip == null || ip.isEmpty() )
			 ip="%";
		
		if (database == null || database.isEmpty() )
			 database="%";
		
		if (usuario == null || usuario.isEmpty() )
			 usuario="%";

		if (programa == null || programa.isEmpty() )
			 programa="%";
		
		
		int i = 1;
                sql.append(" SELECT DISTINCT A.tipo,A.cod_usuario,A.servidor,A.ip,A.database,a.tipo_ambiente,A.usuario_bd_app,A.programa,A.comando2 ");
                sql.append(" FROM vw_ferramentas A ");
                sql.append("  where A.cod_empresa = (select B.cod_empresa from tb_usuario B where B.usuario = ? ) "); 
                sql.append("    and A.tipo = (select C.cod_tipo from tb_tipo_config C where C.tipo = ? ) ");
                sql.append("    and A.usuario = ? ");
                sql.append("    and upper(A.servidor) like upper(?) ");
                sql.append("    and upper(A.ip) like upper(?) ");
               	sql.append("    and upper(A.database) like upper(?) ");                
               	sql.append("    and upper(A.USUARIO_BD_APP) like UPPER (?) ");                
               	sql.append("    and upper(A.programa) like upper(?) ");
                sql.append("    and rownum < 300 ");
                

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, user);
		comando.setString(2, computador);
		comando.setString(3, user);
        comando.setString(4, servidor);
		comando.setString(5, ip);	
		comando.setString(6, database);
		comando.setString(7, usuario);
		comando.setString(8, programa);
		
					
		
		System.out.println(servidor);
		System.out.println(ip);
		System.out.println(database);
		System.out.println(usuario);
		System.out.println(programa);
		
		ResultSet resultado = comando.executeQuery();

		ArrayList<Ferramenta> itens = new ArrayList<Ferramenta>();

		while (resultado.next()) {
			
            Ferramenta u = new Ferramenta();
			u.setTipo(resultado.getString("tipo"));
			u.setNom_usuario(resultado.getString("cod_usuario"));
            u.setServidor(resultado.getString("servidor"));
            u.setIp(resultado.getString("ip"));
            u.setDatabase(resultado.getString("database"));
            u.setTipo_ambiente(resultado.getString("tipo_ambiente"));
            u.setUsuario_bd_app(resultado.getString("usuario_bd_app"));
            u.setPrograma(resultado.getString("programa"));
            u.setComando2(resultado.getString("comando2"));
            
            itens.add(u);

		}
		return itens;

	}

}
