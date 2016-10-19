package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dbatools.dao.InstalacaoConfigDAO;
import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.domain.InstalacaoConfig;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBInstalacaoConfig")
@ViewScoped
public class InstalacaoConfigBean {
    private ArrayList<InstalacaoConfig> itens;
    private ArrayList<InstalacaoConfig> itensFiltrados;
    
    private InstalacaoConfig instalacaoconfig;
    private ArrayList<Empresa> comboEmpresas;
    private ArrayList<Perfil> comboPerfils;
    
    
    public ArrayList<Perfil> getComboPerfils() {
		return comboPerfils;
	}


	public void setComboPerfils(ArrayList<Perfil> comboPerfils) {
		this.comboPerfils = comboPerfils;
	}


	public ArrayList<InstalacaoConfig> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<InstalacaoConfig> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<InstalacaoConfig> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<InstalacaoConfig> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public InstalacaoConfig getInstalacaoConfig() {
		return instalacaoconfig;
	}
    
    public void setInstalacaoConfig(InstalacaoConfig instalacaoconfig) {
		this.instalacaoconfig = instalacaoconfig;
	}
    
    public ArrayList<Empresa> getComboEmpresas() {
		return comboEmpresas;
	}
    
    
    public void setComboEmpresas(ArrayList<Empresa> comboEmpresas) {
		this.comboEmpresas = comboEmpresas;
	}
    
    
	public ArrayList<InstalacaoConfig> getItensFiltrados() {
		return itensFiltrados;
	}


	public void setItensFiltrados(ArrayList<InstalacaoConfig> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



	public void carregarListagem() {
		try {
		InstalacaoConfigDAO dao = new InstalacaoConfigDAO();
		itens = dao.listar();
		ConexaoFactory.fecharConexao();
		} catch(SQLException ex) {
		   ex.printStackTrace();
		   JSFUtil.adicionarMensagemErro(ex.getMessage());
			
		}
	}
	
	public void prepararNovo(){
		instalacaoconfig = new InstalacaoConfig();
	
	}
	
	public void novo() {
		try{
		InstalacaoConfigDAO dao = new InstalacaoConfigDAO();
		dao.salvar(instalacaoconfig);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("InstalacaoConfig Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			InstalacaoConfigDAO dao = new InstalacaoConfigDAO();
			
			dao.excluir(instalacaoconfig);
			
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("InstalacaoConfig Removido com Sucesso");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void prepararEditar() {

	}
	
	
	public void editar() {
		try{
			
		InstalacaoConfigDAO dao = new InstalacaoConfigDAO();
		
		dao.editar(instalacaoconfig);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("InstalacaoConfig editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
