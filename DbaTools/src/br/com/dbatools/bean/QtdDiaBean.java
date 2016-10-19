package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dbatools.dao.EmpresaDAO;
import br.com.dbatools.dao.PerfilDAO;
import br.com.dbatools.dao.QtdDiaDAO;
import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.domain.QtdDia;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBQtdDia")
@ViewScoped
public class QtdDiaBean {
    private ArrayList<QtdDia> itens;
    private ArrayList<QtdDia> itensFiltrados;
    
    private QtdDia qtddia;
    private ArrayList<Empresa> comboEmpresas;
    private ArrayList<Perfil> comboPerfils;
    
    
    public ArrayList<Perfil> getComboPerfils() {
		return comboPerfils;
	}


	public void setComboPerfils(ArrayList<Perfil> comboPerfils) {
		this.comboPerfils = comboPerfils;
	}


	public ArrayList<QtdDia> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<QtdDia> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<QtdDia> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<QtdDia> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public QtdDia getQtdDia() {
		return qtddia;
	}
    
    public void setQtdDia(QtdDia qtddia) {
		this.qtddia = qtddia;
	}
    
    public ArrayList<Empresa> getComboEmpresas() {
		return comboEmpresas;
	}
    
    
    public void setComboEmpresas(ArrayList<Empresa> comboEmpresas) {
		this.comboEmpresas = comboEmpresas;
	}
    
    
	public ArrayList<QtdDia> getItensFiltrados() {
		return itensFiltrados;
	}


	public void setItensFiltrados(ArrayList<QtdDia> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



	public void carregarListagem() {
		try {
		QtdDiaDAO dao = new QtdDiaDAO();
		itens = dao.listar();
		ConexaoFactory.fecharConexao();
		} catch(SQLException ex) {
		   ex.printStackTrace();
		   JSFUtil.adicionarMensagemErro(ex.getMessage());
			
		}
	}
	
	public void prepararNovo(){
		try{
		qtddia = new QtdDia();
		
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
		QtdDiaDAO dao = new QtdDiaDAO();
		dao.salvar(qtddia);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("QtdDia Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			QtdDiaDAO dao = new QtdDiaDAO();
			
			dao.excluir(qtddia);
			
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("QtdDia Removido com Sucesso");
			
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
			
		QtdDiaDAO dao = new QtdDiaDAO();
		
		dao.editar(qtddia);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("QtdDia editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
