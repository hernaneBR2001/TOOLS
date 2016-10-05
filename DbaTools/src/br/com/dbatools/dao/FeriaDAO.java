package br.com.dbatools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import br.com.dbatools.domain.Feria;
import br.com.dbatools.domain.QtdDia;
import br.com.dbatools.domain.Usuario;
import br.com.dbatools.factory.ConexaoFactory;

public class FeriaDAO {
	
	private DateFormat inputFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy",Locale.US);
    private DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy",Locale.US);

	
	public void salvar(Feria p,String user) throws SQLException, ParseException {
		int i = 1;
		
		
        Date date = inputFormat.parse(p.getData_inicio());        
	    
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tb_planilha_ferias ");
		sql.append(" (cod_planilha_ferias,cod_usuario,qtidade_dias,data_inicio,substituto1,substituto2,substituto3,data_fim) ");
		sql.append(" values (seq_planilha_ferias.nextval,(select cod_usuario from tb_usuario where usuario = ? ),?,to_date(?,'DD/MM/YYYY'),?,?,?, to_date(?,'DD/MM/YYYY') + ? ) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(i++, user);
        comando.setLong(2, p.getQtidade_dias().getQtd_dias());
        //comando.setString(3, p.getData_inicio());
        comando.setString(3, outputFormat.format(date));
        comando.setLong(4, p.getSubstituto1().getCod_usuario());
        comando.setLong(5, p.getSubstituto2().getCod_usuario());
        comando.setLong(6, p.getSubstituto3().getCod_usuario());
        //comando.setString(7,p.getData_inicio());
        comando.setString(7,outputFormat.format(date));
        comando.setLong(8, p.getQtidade_dias().getQtd_dias());
        		
	   comando.executeUpdate();	   

	}
	
	public ArrayList<Feria> listar(String user, String computador) throws SQLException {
		StringBuilder sql = new StringBuilder();
		int i = 1;
		
		sql.append (" select a.cod_planilha_ferias, ");  
		sql.append (" a.nom_usuario, ");  
		sql.append (" a.cod_usuario, ");  
		sql.append (" a.substituto1, ");  
		sql.append (" a.substituto2, ");  
		sql.append (" a.substituto3, ");  
		sql.append (" a.NOM_SUBSTITUTO1, ");  
		sql.append (" a.NOM_SUBSTITUTO2, ");  
		sql.append (" a.NOM_SUBSTITUTO3, ");  
		sql.append (" a.qtidade_dias, ");  
		sql.append (" a.data_inicio, ");  
		sql.append (" a.data_fim, ");  
		sql.append (" a.cod_empresa, ");  
		sql.append (" a.cod_perfil ");  
		sql.append ("  from dbatools_adm.vw_ferias a ");  
		sql.append(" where a.cod_empresa = (select cod_empresa from tb_usuario where usuario = ? ) ");
		sql.append("  and a.cod_perfil = (select cod_perfil from tb_usuario where usuario = ? ) ");
		sql.append("  order by to_date(a.data_inicio) , a.nom_usuario ");
		 
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(i++, user);
		comando.setString(i++, user);
		
		ResultSet resultado = comando.executeQuery();

		ArrayList<Feria> itens = new ArrayList<Feria>();

		while (resultado.next()) {
			
            Feria u = new Feria();
			u.setCod_planilha_ferias(resultado.getLong("cod_planilha_ferias"));
			u.setData_inicio(resultado.getString("data_inicio"));
			u.setData_fim(resultado.getString("data_fim"));
            
            
            Usuario p = new Usuario();
            p.setCod_usuario(resultado.getLong("cod_usuario"));
            p.setNom_usuario(resultado.getString("nom_usuario"));

            QtdDia d = new QtdDia();
            d.setQtidade_dias(resultado.getLong("qtidade_dias"));

            
            Usuario q = new Usuario();
            q.setCod_usuario(resultado.getLong("cod_usuario"));
            q.setNom_usuario(resultado.getString("nom_substituto1"));
            
            Usuario r = new Usuario();
            r.setCod_usuario(resultado.getLong("cod_usuario"));
            r.setNom_usuario(resultado.getString("nom_substituto2"));

            Usuario s = new Usuario();
            s.setCod_usuario(resultado.getLong("cod_usuario"));
            s.setNom_usuario(resultado.getString("nom_substituto3"));

            u.setCod_usuario(p);
            u.setSubstituto1(q);
            u.setSubstituto2(r);
            u.setSubstituto3(s);
            u.setQtidade_dias(d);
            itens.add(u);

		}
		return itens;

	}

	public void excluir(Feria p,String user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		int i = 1;
		
		sql.append("DELETE FROM tb_planilha_ferias ");
		sql.append("WHERE cod_planilha_ferias = ? ");
		sql.append("and cod_usuario = (select cod_usuario from tb_usuario where usuario = ? ) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCod_planilha_ferias());
		comando.setString(2, user);
		
		comando.executeUpdate();

	}

    public void editar(Feria p,String user) throws SQLException, ParseException {
    	
    Date date = inputFormat.parse(p.getData_inicio()); 
    
	StringBuilder sql = new StringBuilder();
	int i = 1;
	sql.append("UPDATE tb_planilha_ferias ");
	sql.append("set cod_usuario = (select cod_usuario from tb_usuario where usuario = ? ), qtidade_dias = ?, data_inicio = (to_date(?,'DD/MM/YYYY'), substituto1 = ?, substituto2 = ?, substituto3 = ?, data_fim = (to_date(?,'DD/MM/YYYY') + ?)  ");
	sql.append("WHERE ");
	sql.append("cod_planilha_ferias = ? ");
	sql.append("and cod_usuario =  (select cod_usuario from tb_usuario where usuario = ? ) ");

	Connection conexao = ConexaoFactory.conectar();

	PreparedStatement comando = conexao.prepareStatement(sql.toString());
	comando.setString(1, user);
    //comando.setLong(1, p.getCod_usuario().getCod_usuario());
    comando.setLong(2, p.getQtidade_dias().getQtd_dias());
    comando.setString(3,p.getData_inicio());
    comando.setLong(4, p.getSubstituto1().getCod_usuario());
    comando.setLong(5, p.getSubstituto2().getCod_usuario());
    comando.setLong(6, p.getSubstituto3().getCod_usuario());

    comando.setString(7, p.getData_inicio());
    comando.setLong(8, p.getQtidade_dias().getQtd_dias());

    
    comando.setLong(9, p.getCod_planilha_ferias());
    comando.setString(10, user);
    
	comando.executeUpdate();

}
    
   

}
