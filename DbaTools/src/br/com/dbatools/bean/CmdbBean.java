package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dbatools.dao.EmpresaDAO;
import br.com.dbatools.dao.UsuarioDAO;
import br.com.dbatools.dao.CmdbDAO;
import br.com.dbatools.domain.Empresa;
import br.com.dbatools.domain.Usuario;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.domain.Cmdb;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBCmdb")
@ViewScoped
public class CmdbBean {
    private ArrayList<Cmdb> itens;
    private ArrayList<Cmdb> itensFiltrados;
    
    private Cmdb cmdb;
    private ArrayList<Empresa> comboEmpresas;
    private ArrayList<Usuario> comboUsuarios;
    
    
    public ArrayList<Cmdb> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<Cmdb> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<Cmdb> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<Cmdb> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public Cmdb getCmdb() {
		return cmdb;
	}
    
    public void setCmdb(Cmdb cmdb) {
		this.cmdb = cmdb;
	}
    
    public ArrayList<Empresa> getComboEmpresas() {
		return comboEmpresas;
	}
    

    public ArrayList<Usuario> getComboUsuarios() {
		return comboUsuarios;
	}

    
    public void setComboEmpresas(ArrayList<Empresa> comboEmpresas) {
		this.comboEmpresas = comboEmpresas;
	}
    
    public void setComboUsuarios(ArrayList<Usuario> comboUsuarios) {
		this.comboUsuarios = comboUsuarios;
	}
    

	public void carregarListagem() {
		try {

			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

		CmdbDAO dao = new CmdbDAO();
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
	
	   cmdb = new Cmdb();
		
		EmpresaDAO dao = new EmpresaDAO();
		
		UsuarioDAO use = new UsuarioDAO();
		
		comboEmpresas = dao.listar();
		comboUsuarios = use.listar(user,computador);
		
		} catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	public void novo() {
		try{

			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
			
		CmdbDAO dao = new CmdbDAO();
		dao.salvar(cmdb,user);
		
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("Cmdb Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			CmdbDAO dao = new CmdbDAO();
			
			dao.excluir(cmdb);
			
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
		
			itens = dao.listar(user,computador);
			
			JSFUtil.adicionarMensagemSucesso("Cmdb Removido com Sucesso");
			
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
			
			UsuarioDAO use = new UsuarioDAO();
		
			
			comboEmpresas = dao.listar();
			comboUsuarios = use.listar(user,computador);
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	
	public void editar() {
		try{
			
		CmdbDAO dao = new CmdbDAO();
		
		dao.editar(cmdb);
		
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

		
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("Cmdb editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
