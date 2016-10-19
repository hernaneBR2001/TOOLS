package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dbatools.dao.TipoConfigDAO;
import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.TipoConfig;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBTipoConfig")
@ViewScoped
public class TipoConfigBean {
    private ArrayList<TipoConfig> itens;
    private ArrayList<TipoConfig> itensFiltrados;
    
    private TipoConfig tipoconfig;
    private ArrayList<Empresa> comboEmpresas;
    private ArrayList<Perfil> comboPerfils;
    
    
    public ArrayList<Perfil> getComboPerfils() {
		return comboPerfils;
	}


	public void setComboPerfils(ArrayList<Perfil> comboPerfils) {
		this.comboPerfils = comboPerfils;
	}


	public ArrayList<TipoConfig> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<TipoConfig> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<TipoConfig> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<TipoConfig> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public TipoConfig getTipoConfig() {
		return tipoconfig;
	}
    
    public void setTipoConfig(TipoConfig tipoconfig) {
		this.tipoconfig = tipoconfig;
	}
    
    public ArrayList<Empresa> getComboEmpresas() {
		return comboEmpresas;
	}
    
    
    public void setComboEmpresas(ArrayList<Empresa> comboEmpresas) {
		this.comboEmpresas = comboEmpresas;
	}
    
    
	public ArrayList<TipoConfig> getItensFiltrados() {
		return itensFiltrados;
	}


	public void setItensFiltrados(ArrayList<TipoConfig> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



	public void carregarListagem() {
		try {
		TipoConfigDAO dao = new TipoConfigDAO();
		itens = dao.listar();
		ConexaoFactory.fecharConexao();
		} catch(SQLException ex) {
		   ex.printStackTrace();
		   JSFUtil.adicionarMensagemErro(ex.getMessage());
			
		}
	}
	
	public void prepararNovo(){
		tipoconfig = new TipoConfig();
	
	}
	
	public void novo() {
		try{
		TipoConfigDAO dao = new TipoConfigDAO();
		dao.salvar(tipoconfig);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("TipoConfig Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			TipoConfigDAO dao = new TipoConfigDAO();
			
			dao.excluir(tipoconfig);
			
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("TipoConfig Removido com Sucesso");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void prepararEditar() {

	}
	
	
	public void editar() {
		try{
			
		TipoConfigDAO dao = new TipoConfigDAO();
		
		dao.editar(tipoconfig);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("TipoConfig editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
