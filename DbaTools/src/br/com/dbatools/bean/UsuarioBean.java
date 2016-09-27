package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dbatools.dao.EmpresaDAO;
import br.com.dbatools.dao.PerfilDAO;
import br.com.dbatools.dao.UsuarioDAO;
import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.Usuario;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBUsuario")
@ViewScoped
public class UsuarioBean {
    private ArrayList<Usuario> itens;
    private ArrayList<Usuario> itensFiltrados;
    
    private Usuario usuario;
    private ArrayList<Empresa> comboEmpresas;
    private ArrayList<Perfil> comboPerfils;
    
    
    public ArrayList<Perfil> getComboPerfils() {
		return comboPerfils;
	}


	public void setComboPerfils(ArrayList<Perfil> comboPerfils) {
		this.comboPerfils = comboPerfils;
	}


	public ArrayList<Usuario> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<Usuario> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<Usuario> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<Usuario> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public Usuario getUsuario() {
		return usuario;
	}
    
    public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    public ArrayList<Empresa> getComboEmpresas() {
		return comboEmpresas;
	}
    
    
    public void setComboEmpresas(ArrayList<Empresa> comboEmpresas) {
		this.comboEmpresas = comboEmpresas;
	}
    
    
	public ArrayList<Usuario> getItensFiltrados() {
		return itensFiltrados;
	}


	public void setItensFiltrados(ArrayList<Usuario> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



	public void carregarListagem() {
		try {
	
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
			
		UsuarioDAO dao = new UsuarioDAO();
		itens = dao.listar(user,computador);
		
		} catch(SQLException ex) {
		   ex.printStackTrace();
		   JSFUtil.adicionarMensagemErro(ex.getMessage());
			
		}
	}
	
	public void prepararNovo(){
		try{
			
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
	
		usuario = new Usuario();
		
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
		UsuarioDAO dao = new UsuarioDAO();
		dao.salvar(usuario);
	
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
	
		
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("Usuario Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			
			dao.excluir(usuario);
			
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
		
			
			itens = dao.listar(user,computador);
			
			JSFUtil.adicionarMensagemSucesso("Usuario Removido com Sucesso");
			
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
			
		UsuarioDAO dao = new UsuarioDAO();
		
		dao.editar(usuario);
		
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
	
		
		itens = dao.listar(user,computador);
	
		JSFUtil.adicionarMensagemSucesso("Usuario editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
