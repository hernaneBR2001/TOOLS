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
import br.com.dbatools.domain.Cmdb;
import br.com.dbatools.factory.ConexaoFactory;

public class CmdbDAO {
	
	public void salvar(Cmdb p,String user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		int i = 1;
		sql.append("INSERT INTO TB_PLANILHA ");
		sql.append(" (COD_PLANILHA,COD_EMPRESA,SERVIDOR,IP,VERSAO_SO,DATABASE,VERSAO_DATABASE,TIPO_AMBIENTE,SGDB,REFERENCIA,CONTATO1,CONTATO2,CONTATO3) ");
		sql.append(" values (seq_planilha.nextval,(select cod_empresa from tb_usuario where usuario = ? ),?,?,?,?,?,?,?,?,?,?,?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(i++, user);
       //comando.setLong(1, p.getEmpresa().getCod_empresa());
        comando.setString(2, p.getServidor());
        comando.setString(3, p.getIp());
        comando.setString(4, p.getVersao_so());
        comando.setString(5, p.getDatabase());
        comando.setString(6, p.getVersao_database());
        comando.setString(7, p.getTipo_ambiente());
        comando.setString(8, p.getSgdb());
        comando.setString(9, p.getReferencia());
        comando.setLong(10,p.getContato1().getCod_usuario());
        comando.setLong(11,p.getContato2().getCod_usuario());
        comando.setLong(12,p.getContato3().getCod_usuario());
        
		comando.executeUpdate();

	}
	
	public ArrayList<Cmdb> listar(String user, String computador) throws SQLException {
		StringBuilder sql = new StringBuilder();
		int i = 1;

		sql.append(" select  distinct ");
		sql.append(" a.cod_planilha, ");
		sql.append(" a.cod_empresa, ");
		sql.append(" e.nom_empresa, ");
		sql.append(" a.servidor, ");
		sql.append(" a.ip, ");
		sql.append(" a.versao_so, ");
		sql.append(" a.database, ");
		sql.append(" a.versao_database, ");
		sql.append(" a.tipo_ambiente, ");
		sql.append(" a.sgdb, ");
		sql.append(" a.referencia, ");
		sql.append(" a.contato1, ");
		sql.append(" (select ");
		sql.append(" substr(z.nom_usuario,1,instr(z.nom_usuario,' ')-1)||' '||substr(z.nom_usuario, instr(z.nom_usuario, ' ') + 1, instr(substr(z.nom_usuario, instr(z.nom_usuario, ' ') + 1, length(z.nom_usuario)), ' ') - 1)  ");
		sql.append(" from tb_usuario z where z.cod_usuario = a.contato1) nom_usuario1, ");
		sql.append(" a.contato2, ");
		sql.append(" (select ");
		sql.append(" substr(z.nom_usuario,1,instr(z.nom_usuario,' ')-1)||' '||substr(z.nom_usuario, instr(z.nom_usuario, ' ') + 1, instr(substr(z.nom_usuario, instr(z.nom_usuario, ' ') + 1, length(z.nom_usuario)), ' ') - 1) ");
		sql.append(" from tb_usuario z where z.cod_usuario = a.contato2) nom_usuario2, ");
		sql.append(" a.contato3, ");
		sql.append(" (select ");
		sql.append(" substr(z.nom_usuario,1,instr(z.nom_usuario,' ')-1)||' '||substr(z.nom_usuario, instr(z.nom_usuario, ' ') + 1, instr(substr(z.nom_usuario, instr(z.nom_usuario, ' ') + 1, length(z.nom_usuario)), ' ') - 1) ");
		sql.append(" from tb_usuario z where z.cod_usuario = a.contato3) nom_usuario3 ");
		sql.append(" from tb_planilha a, tb_usuario u, tb_empresa e ");
		sql.append(" where ");
		sql.append(" a.cod_empresa = e.cod_empresa and ");
		sql.append(" a.cod_empresa =  (select cod_empresa from tb_usuario where usuario = ? ) ");

		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(i++, user);
		ResultSet resultado = comando.executeQuery();

		ArrayList<Cmdb> itens = new ArrayList<Cmdb>();

		while (resultado.next()) {
			
            Cmdb u = new Cmdb();
			
            u.setCod_planilha(resultado.getLong("cod_planilha"));
            u.setServidor(resultado.getString("servidor"));
            u.setIp(resultado.getString("ip"));
            u.setVersao_so(resultado.getString("versao_so"));
            u.setDatabase(resultado.getString("database"));
            u.setVersao_database(resultado.getString("versao_database"));
            u.setTipo_ambiente(resultado.getString("tipo_ambiente"));
            u.setSgdb(resultado.getString("sgdb"));
            u.setReferencia(resultado.getString("referencia"));
            
            
           Usuario a = new Usuario();
            
           a.setCod_usuario(resultado.getLong("contato1"));
           a.setNom_usuario(resultado.getString("nom_usuario1"));
                 

           Usuario b = new Usuario();
           
           b.setCod_usuario(resultado.getLong("contato2"));
           b.setNom_usuario(resultado.getString("nom_usuario2"));


           Usuario d = new Usuario();
           
           d.setCod_usuario(resultado.getLong("contato3"));
           d.setNom_usuario(resultado.getString("nom_usuario3"));
           
            
            Empresa c = new Empresa();
            
            c.setCod_empresa(resultado.getLong("cod_empresa"));
            c.setNom_empresa(resultado.getString("nom_empresa"));
            
           
            u.setContato1(a);
            u.setContato2(b);
            u.setContato3(d);
            
            u.setEmpresa(c);

            itens.add(u);

		}
		return itens;

	}

	public void excluir(Cmdb p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TB_PLANILHA ");
		sql.append("WHERE COD_PLANILHA = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCod_planilha());
		
		

		comando.executeUpdate();

	}

    public void editar(Cmdb p) throws SQLException {
	StringBuilder sql = new StringBuilder();
	sql.append("UPDATE TB_PLANILHA ");
	sql.append("set SERVIDOR = ? ,IP = ? ,VERSAO_SO = ? ,DATABASE = ? ,VERSAO_DATABASE = ? ,TIPO_AMBIENTE = ? ,SGDB = ? ,REFERENCIA = ? ,CONTATO1 = ? ,CONTATO2 = ? ,CONTATO3 = ?  ");
	sql.append("WHERE ");
	sql.append("COD_PLANILHA = ? ");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());

    comando.setString(1, p.getServidor());
    comando.setString(2, p.getIp());
    comando.setString(3, p.getVersao_so());
    comando.setString(4, p.getDatabase());
    comando.setString(5, p.getVersao_database());
    comando.setString(6, p.getTipo_ambiente());
    comando.setString(7, p.getSgdb());
    comando.setString(8, p.getReferencia());
    comando.setLong(9, p.getContato1().getCod_usuario());
    comando.setLong(10, p.getContato2().getCod_usuario());
    comando.setLong(11, p.getContato3().getCod_usuario());
    
    
    comando.setLong(12, p.getCod_planilha());
	
	comando.executeUpdate();

}
	

}
