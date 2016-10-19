package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dbatools.dao.EmpresaDAO;
import br.com.dbatools.dao.CmdbDAO;
import br.com.dbatools.dao.BaseConhecimentoDAO;
import br.com.dbatools.domain.Empresa;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.domain.Cmdb;
import br.com.dbatools.domain.BaseConhecimento;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBBaseConhecimento")
@ViewScoped
public class BaseConhecimentoBean {
    private ArrayList<BaseConhecimento> itens;
    private ArrayList<BaseConhecimento> itensFiltrados;
    
    private BaseConhecimento baseconhecimento;
    private ArrayList<Empresa> comboEmpresas;
    private ArrayList<Cmdb> comboCmdbs;
    
    
    public ArrayList<BaseConhecimento> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<BaseConhecimento> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<BaseConhecimento> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<BaseConhecimento> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public BaseConhecimento getBaseConhecimento() {
		return baseconhecimento;
	}
    
    public void setBaseConhecimento(BaseConhecimento baseconhecimento) {
		this.baseconhecimento = baseconhecimento;
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
		BaseConhecimentoDAO dao = new BaseConhecimentoDAO();
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

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
			
		baseconhecimento = new BaseConhecimento();
		
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
		BaseConhecimentoDAO dao = new BaseConhecimentoDAO();
		
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

		dao.salvar(baseconhecimento,user);
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("BaseConhecimento Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			BaseConhecimentoDAO dao = new BaseConhecimentoDAO();
			
			dao.excluir(baseconhecimento);
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
		
			itens = dao.listar(user,computador);
			
			JSFUtil.adicionarMensagemSucesso("BaseConhecimento Removido com Sucesso");
			
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
			
		BaseConhecimentoDAO dao = new BaseConhecimentoDAO();
		
		dao.editar(baseconhecimento);

		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
		
		
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("BaseConhecimento editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}

