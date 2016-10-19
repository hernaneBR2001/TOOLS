package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dbatools.dao.EmpresaDAO;
import br.com.dbatools.dao.PerfilDAO;
import br.com.dbatools.dao.ContatoDAO;
import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.domain.Contato;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBContato")
@ViewScoped
public class ContatoBean {
    private ArrayList<Contato> itens;
    private ArrayList<Contato> itensFiltrados;
    
    private Contato contato;
    private ArrayList<Empresa> comboEmpresas;
    private ArrayList<Perfil> comboPerfils;
    
    
    public ArrayList<Perfil> getComboPerfils() {
		return comboPerfils;
	}


	public void setComboPerfils(ArrayList<Perfil> comboPerfils) {
		this.comboPerfils = comboPerfils;
	}


	public ArrayList<Contato> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<Contato> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<Contato> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<Contato> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public Contato getContato() {
		return contato;
	}
    
    public void setContato(Contato contato) {
		this.contato = contato;
	}
    
    public ArrayList<Empresa> getComboEmpresas() {
		return comboEmpresas;
	}
    
    
    public void setComboEmpresas(ArrayList<Empresa> comboEmpresas) {
		this.comboEmpresas = comboEmpresas;
	}
    
    
	public ArrayList<Contato> getItensFiltrados() {
		return itensFiltrados;
	}


	public void setItensFiltrados(ArrayList<Contato> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



	public void carregarListagem() {
		try {
	
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
			
		ContatoDAO dao = new ContatoDAO();
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
	
		contato = new Contato();
		
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
		ContatoDAO dao = new ContatoDAO();
		dao.salvar(contato);
	
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
	
		
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("Contato Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			ContatoDAO dao = new ContatoDAO();
			
			dao.excluir(contato);
			
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
		
			
			itens = dao.listar(user,computador);
			
			JSFUtil.adicionarMensagemSucesso("Contato Removido com Sucesso");
			
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
			
		ContatoDAO dao = new ContatoDAO();
		
		dao.editar(contato);
		
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
	
		
		itens = dao.listar(user,computador);
	
		JSFUtil.adicionarMensagemSucesso("Contato editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
