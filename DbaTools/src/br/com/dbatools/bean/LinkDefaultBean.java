package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dbatools.dao.LinkDefaultDAO;
import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.domain.LinkDefault;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBLinkDefault")
@ViewScoped
public class LinkDefaultBean {
    private ArrayList<LinkDefault> itens;
    private ArrayList<LinkDefault> itensFiltrados;
    
    private LinkDefault linkdefault;
    private ArrayList<Empresa> comboEmpresas;
    private ArrayList<Perfil> comboPerfils;
    
    
    public ArrayList<Perfil> getComboPerfils() {
		return comboPerfils;
	}


	public void setComboPerfils(ArrayList<Perfil> comboPerfils) {
		this.comboPerfils = comboPerfils;
	}


	public ArrayList<LinkDefault> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<LinkDefault> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<LinkDefault> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<LinkDefault> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public LinkDefault getLinkDefault() {
		return linkdefault;
	}
    
    public void setLinkDefault(LinkDefault linkdefault) {
		this.linkdefault = linkdefault;
	}
    
    public ArrayList<Empresa> getComboEmpresas() {
		return comboEmpresas;
	}
    
    
    public void setComboEmpresas(ArrayList<Empresa> comboEmpresas) {
		this.comboEmpresas = comboEmpresas;
	}
    
    
	public ArrayList<LinkDefault> getItensFiltrados() {
		return itensFiltrados;
	}


	public void setItensFiltrados(ArrayList<LinkDefault> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



	public void carregarListagem() {
		try {
		LinkDefaultDAO dao = new LinkDefaultDAO();
		itens = dao.listar();
		ConexaoFactory.fecharConexao();
		} catch(SQLException ex) {
		   ex.printStackTrace();
		   JSFUtil.adicionarMensagemErro(ex.getMessage());
			
		}
	}
	
	public void prepararNovo(){
		linkdefault = new LinkDefault();
	
	}
	
	public void novo() {
		try{
		LinkDefaultDAO dao = new LinkDefaultDAO();
		dao.salvar(linkdefault);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("LinkDefault Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			LinkDefaultDAO dao = new LinkDefaultDAO();
			
			dao.excluir(linkdefault);
			
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("LinkDefault Removido com Sucesso");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void prepararEditar() {

	}
	
	
	public void editar() {
		try{
			
		LinkDefaultDAO dao = new LinkDefaultDAO();
		
		dao.editar(linkdefault);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("LinkDefault editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
