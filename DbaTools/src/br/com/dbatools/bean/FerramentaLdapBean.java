package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dbatools.dao.UsuarioDAO;
import br.com.dbatools.dao.InstalacaoConfigDAO;
import br.com.dbatools.dao.TipoConfigDAO;
import br.com.dbatools.dao.FerramentaLdapDAO;
import br.com.dbatools.domain.Usuario;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.domain.InstalacaoConfig;
import br.com.dbatools.domain.TipoConfig;
import br.com.dbatools.domain.FerramentaLdap;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBFerramentaLdap")
@ViewScoped
public class FerramentaLdapBean {
    private ArrayList<FerramentaLdap> itens;
    private ArrayList<FerramentaLdap> itensFiltrados;
    
    private FerramentaLdap ferramentaldap;
    private ArrayList<Usuario> comboUsuarios;
 
    private ArrayList<TipoConfig> comboTipoConfigs;
    private String servidorFilter;
    private String programaFilter;
    private String databaseFilter;
    private String ipFilter;
    private String usuarioFilter;
    private String tipoambienteFilter;
  

    public ArrayList<TipoConfig> getcomboTipoConfigs() {
		return comboTipoConfigs;
	}


	public void setcomboTipoConfigs(ArrayList<TipoConfig> comboTipoConfigs) {
		this.comboTipoConfigs = comboTipoConfigs;
	}

    
    


	public ArrayList<FerramentaLdap> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<FerramentaLdap> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<FerramentaLdap> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<FerramentaLdap> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public FerramentaLdap getFerramentaLdap() {
		return ferramentaldap;
	}
    
    public void setFerramentaLdap(FerramentaLdap ferramentaldap) {
		this.ferramentaldap = ferramentaldap;
	}
    
    public ArrayList<Usuario> getComboUsuarios() {
		return comboUsuarios;
	}
    
    
    public void setComboUsuarios(ArrayList<Usuario> comboUsuarios) {
		this.comboUsuarios = comboUsuarios;
	}
    
    
	public ArrayList<FerramentaLdap> getItensFiltrados() {
		return itensFiltrados;
	}


	public void setItensFiltrados(ArrayList<FerramentaLdap> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



	public String getTipoambienteFilter() {
		return tipoambienteFilter;
	}


	public void setTipoambienteFilter(String tipoambienteFilter) {
		this.tipoambienteFilter = tipoambienteFilter;
	}


	public void carregarListagem() {
		try {
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

			FerramentaLdapDAO dao = new FerramentaLdapDAO();
		
			itens = dao.listar(user,computador,servidorFilter,ipFilter,databaseFilter,tipoambienteFilter,programaFilter );
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

		ferramentaldap = new FerramentaLdap();
		
		UsuarioDAO dao = new UsuarioDAO();
		InstalacaoConfigDAO per = new InstalacaoConfigDAO();
		TipoConfigDAO tic = new TipoConfigDAO();
		
		comboUsuarios = dao.listar(user,computador);
	
		comboTipoConfigs = tic.listar();
		
		} catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}


	public String getServidorFilter() {
		return servidorFilter;
	}


	public void setServidorFilter(String servidorFilter) {
		this.servidorFilter = servidorFilter;
	}


	public String getProgramaFilter() {
		return programaFilter;
	}


	public void setProgramaFilter(String programaFilter) {
		this.programaFilter = programaFilter;
	}


	public String getDatabaseFilter() {
		return databaseFilter;
	}


	public void setDatabaseFilter(String databaseFilter) {
		this.databaseFilter = databaseFilter;
	}


	public String getIpFilter() {
		return ipFilter;
	}


	public void setIpFilter(String ipFilter) {
		this.ipFilter = ipFilter;
	}


	public String getUsuarioFilter() {
		return usuarioFilter;
	}


	public void setUsuarioFilter(String usuarioFilter) {
		this.usuarioFilter = usuarioFilter;
	}
	
	
}
