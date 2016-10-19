package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dbatools.dao.UsuarioDAO;
import br.com.dbatools.dao.InstalacaoConfigDAO;
import br.com.dbatools.dao.TipoConfigDAO;
import br.com.dbatools.dao.CredencialUsuarioDAO;
import br.com.dbatools.domain.Usuario;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.domain.InstalacaoConfig;
import br.com.dbatools.domain.TipoConfig;
import br.com.dbatools.domain.CredencialUsuario;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBCredencialUsuario")
@ViewScoped
public class CredencialUsuarioBean {
    private ArrayList<CredencialUsuario> itens;
    private ArrayList<CredencialUsuario> itensFiltrados;
    
    private CredencialUsuario credencialusuario;
    private ArrayList<Usuario> comboUsuarios;
    private ArrayList<InstalacaoConfig> comboInstalacaoConfigs;
    private ArrayList<TipoConfig> comboTipoConfigs;
     

    public ArrayList<TipoConfig> getcomboTipoConfigs() {
		return comboTipoConfigs;
	}


	public void setcomboTipoConfigs(ArrayList<TipoConfig> comboTipoConfigs) {
		this.comboTipoConfigs = comboTipoConfigs;
	}

    
    
    public ArrayList<InstalacaoConfig> getComboInstalacaoConfigs() {
		return comboInstalacaoConfigs;
	}


	public void setComboInstalacaoConfigs(ArrayList<InstalacaoConfig> comboInstalacaoConfigs) {
		this.comboInstalacaoConfigs = comboInstalacaoConfigs;
	}


	public ArrayList<CredencialUsuario> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<CredencialUsuario> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<CredencialUsuario> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<CredencialUsuario> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public CredencialUsuario getCredencialUsuario() {
		return credencialusuario;
	}
    
    public void setCredencialUsuario(CredencialUsuario credencialusuario) {
		this.credencialusuario = credencialusuario;
	}
    
    public ArrayList<Usuario> getComboUsuarios() {
		return comboUsuarios;
	}
    
    
    public void setComboUsuarios(ArrayList<Usuario> comboUsuarios) {
		this.comboUsuarios = comboUsuarios;
	}
    
    
	public ArrayList<CredencialUsuario> getItensFiltrados() {
		return itensFiltrados;
	}


	public void setItensFiltrados(ArrayList<CredencialUsuario> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



	public void carregarListagem() {
		try {

			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

		CredencialUsuarioDAO dao = new CredencialUsuarioDAO();
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
			
		credencialusuario = new CredencialUsuario();
		
		UsuarioDAO dao = new UsuarioDAO();
		InstalacaoConfigDAO per = new InstalacaoConfigDAO();
		TipoConfigDAO tic = new TipoConfigDAO();
		
		comboUsuarios = dao.listar(user,computador);
		comboInstalacaoConfigs = per.listar();
		comboTipoConfigs = tic.listar();
		
		} catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	public void novo() {
		try{
		CredencialUsuarioDAO dao = new CredencialUsuarioDAO();
	
		
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
		dao.salvar(credencialusuario,user,computador);
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("CredencialUsuario Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			CredencialUsuarioDAO dao = new CredencialUsuarioDAO();
			
			dao.excluir(credencialusuario);
			
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

			itens = dao.listar(user,computador);
			
			JSFUtil.adicionarMensagemSucesso("CredencialUsuario Removido com Sucesso");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void prepararEditar() {
		try {
			
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

			UsuarioDAO dao = new UsuarioDAO();
			InstalacaoConfigDAO per = new InstalacaoConfigDAO();
			TipoConfigDAO tic = new TipoConfigDAO();
			
			comboUsuarios = dao.listar(user,computador);
			comboInstalacaoConfigs = per.listar();
			comboTipoConfigs = tic.listar();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	
	public void editar() {
		try{
			
		CredencialUsuarioDAO dao = new CredencialUsuarioDAO();
		
		dao.editar(credencialusuario);
		
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("CredencialUsuario editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
