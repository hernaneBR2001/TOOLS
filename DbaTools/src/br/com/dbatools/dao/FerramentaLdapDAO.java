package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.FerramentaLdap;
import br.com.dbatools.factory.ConexaoFactory;

public class FerramentaLdapDAO {
	
	public ArrayList<FerramentaLdap> listar(String user, String computador, String servidor,String ip, String database, String tipoambiente, String programa) throws SQLException {
		StringBuilder sql = new StringBuilder();

		if (servidor == null || servidor.isEmpty() )
			 servidor="%";
		
		if (ip == null || ip.isEmpty() )
			 ip="%";
		
		if (database == null || database.isEmpty() )
			 database="%";
	
		
		if (tipoambiente == null || tipoambiente.isEmpty() )
			 tipoambiente="%";

	
		if (programa == null || programa.isEmpty() )
			 programa="%";

		
		int i = 1;
                sql.append(" SELECT A.tipo,A.nom_usuario,A.servidor,A.ip,A.database,a.versao,a.tipo_ambiente,A.usuario,A.programa,A.comando2 ");
                sql.append(" FROM vw_ferramentas_ldap A ");
                sql.append("  where A.cod_empresa = (select B.cod_empresa from tb_usuario B where B.usuario = ? ) AND "); 
                sql.append("     A.tipo = (select C.cod_tipo from tb_tipo_config C where C.tipo = ? ) and  ");
                sql.append("  A.usuario = ?  ");      
                sql.append("    and upper(A.servidor) like upper(?) ");
                sql.append("    and upper(A.ip) like upper(?) ");
               	sql.append("    and upper(A.database) like upper(?) ");
               	sql.append("    and upper(A.tipo_ambiente) like upper(?) ");
               	sql.append("    and upper(A.programa) like upper(?) ");
               	sql.append(" and a.cod_usuario = (select B.cod_usuario from tb_usuario B where B.usuario = ? )");
                sql.append("    and rownum < 300 ");
                sql.append("    order by 1,2,3,4,5 ");

                
                

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
	
		comando.setString(1, user);
		comando.setString(2, computador);
		comando.setString(3, user);
        comando.setString(4, servidor);
		comando.setString(5, ip);	
		comando.setString(6, database);
		comando.setString(7, tipoambiente);
		comando.setString(8, programa);
		comando.setString(9, user);
		
		
		ResultSet resultado = comando.executeQuery();

		ArrayList<FerramentaLdap> itens = new ArrayList<FerramentaLdap>();

		while (resultado.next()) {
			
            FerramentaLdap u = new FerramentaLdap();
			u.setTipo(resultado.getString("tipo"));
			u.setNom_usuario(resultado.getString("nom_usuario"));
            u.setServidor(resultado.getString("servidor"));
            u.setIp(resultado.getString("ip"));
            u.setDatabase(resultado.getString("database"));
            u.setVersao(resultado.getString("versao"));
            u.setTipo_ambiente(resultado.getString("tipo_ambiente"));
            u.setUsuario_bd_app(resultado.getString("usuario"));
            u.setPrograma(resultado.getString("programa"));
            u.setComando2(resultado.getString("comando2"));
            
            itens.add(u);

		}
		return itens;

	}

}
