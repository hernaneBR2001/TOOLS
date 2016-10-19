package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dbatools.dao.EmpresaDAO;
import br.com.dbatools.dao.UsuarioDAO;
import br.com.dbatools.dao.CmdbPostgresDAO;
import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Usuario;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.factory.ConexaoPostgres;
import br.com.dbatools.domain.CmdbPostgres;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBCmdbPostgres")
@ViewScoped
public class CmdbPostgresBean {
    private ArrayList<CmdbPostgres> itens;
    private ArrayList<CmdbPostgres> itensFiltrados;
    
    private CmdbPostgres cmdbpostgres;
    private ArrayList<Empresa> comboEmpresas;
    private ArrayList<Usuario> comboUsuarios;
    
    
    public ArrayList<CmdbPostgres> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<CmdbPostgres> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<CmdbPostgres> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<CmdbPostgres> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public CmdbPostgres getCmdbPostgres() {
		return cmdbpostgres;
	}
    
    public void setCmdbPostgres(CmdbPostgres cmdbpostgres) {
		this.cmdbpostgres = cmdbpostgres;
	}
    
    public ArrayList<Empresa> getComboEmpresas() {
		return comboEmpresas;
	}
    

    public ArrayList<Usuario> getComboUsuarios() {
		return comboUsuarios;
	}

    
    public void setComboEmpresas(ArrayList<Empresa> comboEmpresas) {
		this.comboEmpresas = comboEmpresas;
	}
    
    public void setComboUsuarios(ArrayList<Usuario> comboUsuarios) {
		this.comboUsuarios = comboUsuarios;
	}
    

	public void carregarListagem() {
		try {
		CmdbPostgresDAO dao = new CmdbPostgresDAO();
		itens = dao.listar();
		ConexaoPostgres.fecharConexao();
		} catch(SQLException ex) {
		   ex.printStackTrace();
		   JSFUtil.adicionarMensagemErro(ex.getMessage());
			
		}
	}
	
	public void prepararNovo(){
		try{
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

		cmdbpostgres = new CmdbPostgres();
		
		EmpresaDAO dao = new EmpresaDAO();
		
		UsuarioDAO use = new UsuarioDAO();
		
		comboEmpresas = dao.listar();
		comboUsuarios = use.listar(user,computador);
		
		} catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	public void novo() {
		try{
		CmdbPostgresDAO dao = new CmdbPostgresDAO();
		dao.salvar(cmdbpostgres);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("CmdbPostgres Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			CmdbPostgresDAO dao = new CmdbPostgresDAO();
			
			dao.excluir(cmdbpostgres);
			
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("CmdbPostgres Removido com Sucesso");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void prepararEditar() {
		try {
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

			EmpresaDAO dao = new EmpresaDAO();
			
			UsuarioDAO use = new UsuarioDAO();
			
			comboEmpresas = dao.listar();
			comboUsuarios = use.listar(user,computador);
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	
	public void editar() {
		try{
			
		CmdbPostgresDAO dao = new CmdbPostgresDAO();
		
		dao.editar(cmdbpostgres);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("CmdbPostgres editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
