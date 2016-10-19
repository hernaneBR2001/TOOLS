package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dbatools.dao.EmpresaDAO;
import br.com.dbatools.dao.PerfilDAO;
import br.com.dbatools.dao.UsuarioPerfilDAO;
import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.UsuarioPerfil;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBUsuarioPerfil")
@ViewScoped
public class UsuarioPerfilBean {
    private ArrayList<UsuarioPerfil> itens;
    private ArrayList<UsuarioPerfil> itensFiltrados;
    
    private UsuarioPerfil usuarioperfil;
    private ArrayList<Empresa> comboEmpresas;
    private ArrayList<Perfil> comboPerfils;
    
    
    public ArrayList<Perfil> getComboPerfils() {
		return comboPerfils;
	}


	public void setComboPerfils(ArrayList<Perfil> comboPerfils) {
		this.comboPerfils = comboPerfils;
	}


	public ArrayList<UsuarioPerfil> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<UsuarioPerfil> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<UsuarioPerfil> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<UsuarioPerfil> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public UsuarioPerfil getUsuarioPerfil() {
		return usuarioperfil;
	}
    
    public void setUsuarioPerfil(UsuarioPerfil usuarioperfil) {
		this.usuarioperfil = usuarioperfil;
	}
    
    public ArrayList<Empresa> getComboEmpresas() {
		return comboEmpresas;
	}
    
    
    public void setComboEmpresas(ArrayList<Empresa> comboEmpresas) {
		this.comboEmpresas = comboEmpresas;
	}
    
    
	public ArrayList<UsuarioPerfil> getItensFiltrados() {
		return itensFiltrados;
	}


	public void setItensFiltrados(ArrayList<UsuarioPerfil> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



	public void carregarListagem() {
		try {
	
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
			
		UsuarioPerfilDAO dao = new UsuarioPerfilDAO();
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
	
		usuarioperfil = new UsuarioPerfil();
		
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
		UsuarioPerfilDAO dao = new UsuarioPerfilDAO();
		dao.salvar(usuarioperfil);
	
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
	
		
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("UsuarioPerfil Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			UsuarioPerfilDAO dao = new UsuarioPerfilDAO();
			
			dao.excluir(usuarioperfil);
			
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
		
			
			itens = dao.listar(user,computador);
			
			JSFUtil.adicionarMensagemSucesso("UsuarioPerfil Removido com Sucesso");
			
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
			
		UsuarioPerfilDAO dao = new UsuarioPerfilDAO();
		
		dao.editar(usuarioperfil);
		
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
	
		
		itens = dao.listar(user,computador);
	
		JSFUtil.adicionarMensagemSucesso("UsuarioPerfil editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
