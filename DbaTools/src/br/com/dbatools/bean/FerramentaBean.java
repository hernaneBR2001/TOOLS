package br.com.dbatools.bean;

import java.io.Console;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.dbatools.dao.FerramentaDAO;
import br.com.dbatools.dao.HostClientDAO;
import br.com.dbatools.dao.TipoConfigDAO;
import br.com.dbatools.dao.UsuarioDAO;
import br.com.dbatools.domain.DBAToolsConnector;
import br.com.dbatools.domain.Ferramenta;
import br.com.dbatools.domain.HostClient;
import br.com.dbatools.domain.TipoConfig;
import br.com.dbatools.domain.Usuario;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBFerramenta")
@SessionScoped
public class FerramentaBean {
    private ArrayList<Ferramenta> itens;
    private ArrayList<Ferramenta> itensFiltrados;
    private String servidorFilter;
    private String programaFilter;
    private String databaseFilter;
    private String ipFilter;
    private String usuarioFilter;
    private String tipoambienteFilter;
    
    
    private Ferramenta ferramenta;
    private ArrayList<Usuario> comboUsuarios;
 
    private ArrayList<TipoConfig> comboTipoConfigs;
     

    public ArrayList<TipoConfig> getcomboTipoConfigs() {
		return comboTipoConfigs;
	}


	public void setcomboTipoConfigs(ArrayList<TipoConfig> comboTipoConfigs) {
		this.comboTipoConfigs = comboTipoConfigs;
	}

    
    


	public ArrayList<Ferramenta> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<Ferramenta> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<Ferramenta> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<Ferramenta> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public Ferramenta getFerramenta() {
		return ferramenta;
	}
    
    public void setFerramenta(Ferramenta ferramenta) {
		this.ferramenta = ferramenta;
	}
    
    public ArrayList<Usuario> getComboUsuarios() {
		return comboUsuarios;
	}
    
    
    public void setComboUsuarios(ArrayList<Usuario> comboUsuarios) {
		this.comboUsuarios = comboUsuarios;
	}
    
    
	public ArrayList<Ferramenta> getItensFiltrados() {
		return itensFiltrados;
	}


	public void setItensFiltrados(ArrayList<Ferramenta> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
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
		

	
	public String getTipoambienteFilter() {
		return tipoambienteFilter;
	}


	public void setTipoambienteFilter(String tipoambienteFilter) {
		this.tipoambienteFilter = tipoambienteFilter;
	}


	public void carregarListagem() {
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
		try {
		FerramentaDAO dao = new FerramentaDAO();
		itens = dao.listar(user,computador,servidorFilter,ipFilter,databaseFilter,tipoambienteFilter,usuarioFilter,programaFilter );
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

		ferramenta = new Ferramenta();
		
		UsuarioDAO dao = new UsuarioDAO();
//		/InstalacaoConfigDAO per = new InstalacaoConfigDAO();
		TipoConfigDAO tic = new TipoConfigDAO();
		
		comboUsuarios = dao.listar(user,computador);
	
		comboTipoConfigs = tic.listar();
		
		} catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	public void prepararEditar(String comando){
		System.out.println(comando.indexOf('|'));
		
	
		if (comando.indexOf('|') > 0 ) {
			
		
		String[] particao = new String[2];
		particao[0] = comando.substring(0,comando.indexOf('|'));
		particao[1] = comando.substring(comando.indexOf('|')+1,comando.length());
	    

		System.out.println("part1: " + particao[0]);
		System.out.println("part2: " + particao[1]);
		
		String comando1 = particao[0];
		String comando2 = particao[1];
		
	    
	    
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		DBAToolsConnector connector = null;
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		
		
		HostClientDAO hostDAO = new HostClientDAO();
		HostClient hc = hostDAO.getLastLogon(user, request.getRemoteAddr());
		
		if (hc != null){
			System.out.println("host: " + hc.getHost() + " port: " + hc.getPort());
			connector = new DBAToolsConnector(hc.getHost(),hc.getPort());
		}
		else{
			connector = new DBAToolsConnector(request.getRemoteAddr(),9090);
		}
		
		
		System.out.println("comando1: " +comando1);
		System.out.println("comando2: " +comando2);
		 
		String[] commands = new String[2];
		commands[0] = comando1.substring(0,comando1.indexOf(';'));
		commands[1] = comando1.substring(comando1.indexOf(';')+1,comando1.length());
		
		System.out.println("comando[1]: " +commands[0]);
		System.out.println("comando[2]: " +commands[1]);
		
		
		connector.sendCommand(commands[0], commands[1]);
		

		String[] commands2 = new String[2];
		commands2[0] = comando2.substring(0,comando2.indexOf(';'));
		commands2[1] = comando2.substring(comando2.indexOf(';')+1,comando2.length());

		
		System.out.println("comando2[1]: " +commands2[0]);
		System.out.println("comando2[2]: " +commands2[1]);
	
		
		connector.sendCommand(commands2[0], commands2[1]);
		}
		
		
		else {
			
			
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			System.out.println(comando);
			DBAToolsConnector connector = null;
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			
			
			HostClientDAO hostDAO = new HostClientDAO();
			HostClient hc = hostDAO.getLastLogon(user, request.getRemoteAddr());
			
			if (hc != null){
				System.out.println("host: " + hc.getHost() + " port: " + hc.getPort());
				connector = new DBAToolsConnector(hc.getHost(),hc.getPort());
			}

			else{
				connector = new DBAToolsConnector(request.getRemoteAddr(),9090);
			}
			
			 
			
			String[] commands = new String[2];
			commands[0] = comando.substring(0,comando.indexOf(';'));
			commands[1] = comando.substring(comando.indexOf(';')+1,comando.length());
			System.out.println(commands[0]);
			System.out.println(commands[1]);
			
			connector.sendCommand(commands[0], commands[1]);



		
			
		}
		
	}


	public void prepararComando(String comando){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		System.out.println(comando);
		DBAToolsConnector connector = null;
		String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		
		
		HostClientDAO hostDAO = new HostClientDAO();
		HostClient hc = hostDAO.getLastLogon(user, request.getRemoteAddr());
		
		if (hc != null){
			System.out.println("host: " + hc.getHost() + " port: " + hc.getPort());
			connector = new DBAToolsConnector(hc.getHost(),hc.getPort());
		}
		else{
			connector = new DBAToolsConnector(request.getRemoteAddr(),9090);
		}
		
		 
		
		String[] commands = new String[2];
		commands[0] = comando.substring(0,comando.indexOf(';'));
		commands[1] = comando.substring(comando.indexOf(';')+1,comando.length());
		System.out.println(commands[0]);
		System.out.println(commands[1]);
		
		connector.sendCommand(commands[0], commands[1]);
		
			
		
	}
	
	public static void main(String[] args) {
		
		String comando = "aaaa|bbbb";

		
		String[] particao = new String[2];
		particao[0] = comando.substring(0,comando.indexOf('|'));
		particao[1] = comando.substring(comando.indexOf('|')+1,comando.length());
        
		System.out.println("part1: " + particao[0]);
		System.out.println("part2: " + particao[1]);
		
	}



}
