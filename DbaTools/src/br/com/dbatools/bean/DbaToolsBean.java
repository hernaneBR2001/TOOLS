package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dbatools.dao.EmpresaDAO;
import br.com.dbatools.dao.CmdbDAO;
import br.com.dbatools.dao.DbaToolsDAO;
import br.com.dbatools.domain.Empresa;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.domain.Cmdb;
import br.com.dbatools.domain.DbaTools;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBDbaTools")
@ViewScoped
public class DbaToolsBean {
    private ArrayList<DbaTools> itens;
    private ArrayList<DbaTools> itensFiltrados;
    
    private DbaTools dbatools;
    private ArrayList<Empresa> comboEmpresas;
    private ArrayList<Cmdb> comboCmdbs;
    
    
    public ArrayList<DbaTools> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<DbaTools> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<DbaTools> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<DbaTools> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public DbaTools getDbaTools() {
		return dbatools;
	}
    
    public void setDbaTools(DbaTools dbatools) {
		this.dbatools = dbatools;
	}
    
    public ArrayList<Empresa> getComboEmpresas() {
		return comboEmpresas;
	}
    

    public ArrayList<Cmdb> getComboCmdbs() {
		return comboCmdbs;
	}

    
    public void setComboEmpresas(ArrayList<Empresa> comboEmpresas) {
		this.comboEmpresas = comboEmpresas;
	}
    
    public void setComboCmdbs(ArrayList<Cmdb> comboCmdbs) {
		this.comboCmdbs = comboCmdbs;
	}
    

	public void carregarListagem() {
		try {
	
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
			
		DbaToolsDAO dao = new DbaToolsDAO();
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
			
		dbatools = new DbaTools();
		
		EmpresaDAO dao = new EmpresaDAO();
		
		CmdbDAO use = new CmdbDAO();
		
		comboEmpresas = dao.listar();
		comboCmdbs = use.listar(user,computador);
		
		} catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	public void novo() {
		try{
		DbaToolsDAO dao = new DbaToolsDAO();
		dao.salvar(dbatools);
	
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
		
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("DbaTools Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			DbaToolsDAO dao = new DbaToolsDAO();
			
			dao.excluir(dbatools);
			
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
		
			itens = dao.listar(user,computador);
			
			JSFUtil.adicionarMensagemSucesso("DbaTools Removido com Sucesso");
			
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
			
			CmdbDAO use = new CmdbDAO();
			
			comboEmpresas = dao.listar();
			comboCmdbs = use.listar(user,computador);
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	
	public void editar() {
		try{
			
		DbaToolsDAO dao = new DbaToolsDAO();
		
		dao.editar(dbatools);
		
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
	
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("DbaTools editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}

