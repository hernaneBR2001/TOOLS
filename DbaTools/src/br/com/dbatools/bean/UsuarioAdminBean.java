package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dbatools.dao.EmpresaDAO;
import br.com.dbatools.dao.PerfilDAO;
import br.com.dbatools.dao.UsuarioAdminDAO;
import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.UsuarioAdmin;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBUsuarioAdmin")
@ViewScoped
public class UsuarioAdminBean {
    private ArrayList<UsuarioAdmin> itens;
    private ArrayList<UsuarioAdmin> itensFiltrados;
    
    private UsuarioAdmin usuarioadmin;
    private ArrayList<Empresa> comboEmpresas;
    private ArrayList<Perfil> comboPerfils;
    
    
    public ArrayList<Perfil> getComboPerfils() {
		return comboPerfils;
	}


	public void setComboPerfils(ArrayList<Perfil> comboPerfils) {
		this.comboPerfils = comboPerfils;
	}


	public ArrayList<UsuarioAdmin> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<UsuarioAdmin> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<UsuarioAdmin> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<UsuarioAdmin> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public UsuarioAdmin getUsuarioAdmin() {
		return usuarioadmin;
	}
    
    public void setUsuarioAdmin(UsuarioAdmin usuarioadmin) {
		this.usuarioadmin = usuarioadmin;
	}
    
    public ArrayList<Empresa> getComboEmpresas() {
		return comboEmpresas;
	}
    
    
    public void setComboEmpresas(ArrayList<Empresa> comboEmpresas) {
		this.comboEmpresas = comboEmpresas;
	}
    
    
	public ArrayList<UsuarioAdmin> getItensFiltrados() {
		return itensFiltrados;
	}


	public void setItensFiltrados(ArrayList<UsuarioAdmin> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



	public void carregarListagem() {
		try {
	
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
			
		UsuarioAdminDAO dao = new UsuarioAdminDAO();
		itens = dao.listar(user,computador);
		ConexaoFactory.fecharConexao();
		} catch(SQLException ex) {
		   ex.printStackTrace();
		   JSFUtil.adicionarMensagemErro(ex.getMessage());
			
		}
	}
	
	public void prepararNovo(){
		try{
			
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
	
		usuarioadmin = new UsuarioAdmin();
		
		EmpresaDAO dao = new EmpresaDAO();
		PerfilDAO per = new PerfilDAO();
		
		comboEmpresas = dao.listar();
		comboPerfils = per.listar();
		
		} catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	public void novo() {
		try{
		UsuarioAdminDAO dao = new UsuarioAdminDAO();
		dao.salvar(usuarioadmin);
	
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
	
		
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("UsuarioAdmin Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			UsuarioAdminDAO dao = new UsuarioAdminDAO();
			
			dao.excluir(usuarioadmin);
			
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
		
			
			itens = dao.listar(user,computador);
			
			JSFUtil.adicionarMensagemSucesso("UsuarioAdmin Removido com Sucesso");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void prepararEditar() {
		try {
			
			EmpresaDAO dao = new EmpresaDAO();
			PerfilDAO per = new PerfilDAO();
			
			comboEmpresas = dao.listar();
			comboPerfils = per.listar();
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	
	public void editar() {
		try{
			
		UsuarioAdminDAO dao = new UsuarioAdminDAO();
		
		dao.editar(usuarioadmin);
		
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
	
		
		itens = dao.listar(user,computador);
	
		JSFUtil.adicionarMensagemSucesso("UsuarioAdmin editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
