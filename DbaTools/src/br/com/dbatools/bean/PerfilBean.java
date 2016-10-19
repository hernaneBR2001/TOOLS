package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dbatools.dao.PerfilDAO;
import br.com.dbatools.domain.Perfil;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name = "MBPerfil")
@ViewScoped
public class PerfilBean {
	private Perfil perfil;
	private ArrayList<Perfil> itens;
    private ArrayList<Perfil> itensFiltrados;
    
	public Perfil getPerfil() {
		return perfil;
	}


	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

public ArrayList<Perfil> getItens() {
	return itens;
}

public void setItens(ArrayList<Perfil> itens) {
	this.itens = itens;
}


public ArrayList<Perfil> getItensFiltrados() {
	return itensFiltrados;
}

public void setItensFiltrados(ArrayList<Perfil> itensFiltrados) {
	this.itensFiltrados = itensFiltrados;
}



	@PostConstruct
	public void prepararPesquisa() {
		try {

			PerfilDAO dao = new PerfilDAO();
			itens = dao.listar();
			ConexaoFactory.fecharConexao();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void prepararNovo() {

		perfil = new Perfil();

	}

	public void novo() {
		try {

			PerfilDAO dao = new PerfilDAO();
			dao.salvar(perfil);

			itens = dao.listar();
			
			
			JSFUtil.adicionarMensagemSucesso("Perfil Salvo Com sucesso");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}


	

	public void excluir() {
		try {

			PerfilDAO dao = new PerfilDAO();
			dao.excluir(perfil);

			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Perfil Removido com Sucesso");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	

	public void editar() {
		try {

			PerfilDAO dao = new PerfilDAO();

			dao.editar(perfil);

			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Perfil editado com sucesso.");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

}
