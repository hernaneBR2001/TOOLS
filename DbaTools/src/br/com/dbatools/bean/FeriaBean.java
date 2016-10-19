package br.com.dbatools.bean;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dbatools.dao.UsuarioDAO;
import br.com.dbatools.dao.CmdbDAO;
import br.com.dbatools.dao.FeriaDAO;
import br.com.dbatools.dao.QtdDiaDAO;
import br.com.dbatools.domain.Usuario;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.domain.Cmdb;
import br.com.dbatools.domain.Feria;
import br.com.dbatools.domain.QtdDia;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name="MBFeria")
@ViewScoped
public class FeriaBean {
    private ArrayList<Feria> itens;
    private ArrayList<Feria> itensFiltrados;
    
    private Feria feria;
    private ArrayList<Usuario> comboUsuarios;
    private ArrayList<QtdDia> comboQtdDias;
    
    
    public ArrayList<QtdDia> getComboQtdDias() {
		return comboQtdDias;
	}


	public void setComboQtdDias(ArrayList<QtdDia> comboQtdDias) {
		this.comboQtdDias = comboQtdDias;
	}


	public ArrayList<Feria> getItens() {
		return itens;
	}
    
    
    public void setItens(ArrayList<Feria> itens) {
		this.itens = itens;
	}
	
    
    public ArrayList<Feria> getItensfiltrados() {
		return itensFiltrados;
	}
    
    public void setItensfiltrados(ArrayList<Feria> itensfiltrados) {
		this.itensFiltrados = itensfiltrados;
	}
    
    public Feria getFeria() {
		return feria;
	}
    
    public void setFeria(Feria feria) {
		this.feria = feria;
	}
    
    public ArrayList<Usuario> getComboUsuarios() {
		return comboUsuarios;
	}
    


    
    public void setComboUsuarios(ArrayList<Usuario> comboUsuarios) {
		this.comboUsuarios = comboUsuarios;
	}
    

	public void carregarListagem() {
		try {

			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
			
		FeriaDAO dao = new FeriaDAO();
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
			
		feria = new Feria();
		
		UsuarioDAO dao = new UsuarioDAO();
		QtdDiaDAO qtd = new QtdDiaDAO();
		
		comboUsuarios = dao.listar(user,computador);
        comboQtdDias = qtd.listar();
		
		} catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	public void novo()  {
		try{

			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");
			
	    FeriaDAO dao = new FeriaDAO();
		dao.salvar(feria,user);


		
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("Ferias Salvo com sucesso. ");
		}catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}catch (ParseException pe) {
			pe.printStackTrace();
			JSFUtil.adicionarMensagemErro(pe.getMessage());
		}
	}
	
	public void excluir() {
		try {

			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

			FeriaDAO dao = new FeriaDAO();
			
			dao.excluir(feria,user);
			
			itens = dao.listar(user,computador);
			
			JSFUtil.adicionarMensagemSucesso("Ferias Removido com Sucesso");
			
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
			QtdDiaDAO qtd = new QtdDiaDAO();
			
			comboUsuarios = dao.listar(user,computador);
			comboQtdDias = qtd.listar();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	
	public void editar() throws ParseException {
		try{
			
			String user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			String computador = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("computador");

		FeriaDAO dao = new FeriaDAO();
		
		dao.editar(feria,user);
		
		itens = dao.listar(user,computador);
		
		JSFUtil.adicionarMensagemSucesso("Ferias editado com sucesso.");
		
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}

