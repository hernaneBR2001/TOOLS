package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dbatools.dao.CmdbDAO;
import br.com.dbatools.dao.CadastroDbAppDAO;
import br.com.dbatools.domain.Cmdb;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.domain.CadastroDbApp;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBCadastroDbApp")
@ViewScoped
public class CadastroDbAppBean {
    private ArrayList<CadastroDbApp> itens;
    private ArrayList<CadastroDbApp> itensFiltrados;
    
    private CadastroDbApp cadastrodbapp;
    private ArrayList<Cmdb> comboCmdbs;
    
    
    public ArrayList<CadastroDbApp> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<CadastroDbApp> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<CadastroDbApp> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<CadastroDbApp> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public CadastroDbApp getCadastroDbApp() {
		return cadastrodbapp;
	}
    
    public void setCadastroDbApp(CadastroDbApp cadastrodbapp) {
		this.cadastrodbapp = cadastrodbapp;
	}
    
    public ArrayList<Cmdb> getComboCmdbs() {
		return comboCmdbs;
	}
    
    
    public void setComboCmdbs(ArrayList<Cmdb> comboCmdbs) {
		this.comboCmdbs = comboCmdbs;
	}
    
    

	public void carregarListagem() {
		try {

			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

		
	    CadastroDbAppDAO dao = new CadastroDbAppDAO();
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
				
	    cadastrodbapp = new CadastroDbApp();
		
		CmdbDAO dao = new CmdbDAO();
	
		comboCmdbs = dao.listar(user,computador);
		} catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	public void novo() {
		try{
		CadastroDbAppDAO dao = new CadastroDbAppDAO();
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
	
		
		dao.salvar(cadastrodbapp);
	
		
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("CadastroDbApp Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			CadastroDbAppDAO dao = new CadastroDbAppDAO();
			
			dao.excluir(cadastrodbapp);

			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
		
			itens = dao.listar(user,computador);
			
			JSFUtil.adicionarMensagemSucesso("CadastroDbApp Removido com Sucesso");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void prepararEditar() {
		try {
			
			CmdbDAO dao = new CmdbDAO();
				
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

			comboCmdbs = dao.listar(user,computador);
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	
	public void editar() {
		try{
			
		CadastroDbAppDAO dao = new CadastroDbAppDAO();
		
		dao.editar(cadastrodbapp);
		
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

		
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("CadastroDbApp editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
