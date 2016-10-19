package br.com.dbatools.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dbatools.dao.EmpresaDAO;
import br.com.dbatools.domain.Empresa;
import br.com.dbatools.factory.ConexaoFactory;
import br.com.dbatools.util.JSFUtil;

@ManagedBean(name = "MBEmpresa")
@ViewScoped
public class EmpresaBean {
	private Empresa empresa;
	private ArrayList<Empresa> itens;
    private ArrayList<Empresa> itensFiltrados;
    
	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

public ArrayList<Empresa> getItens() {
	return itens;
}

public void setItens(ArrayList<Empresa> itens) {
	this.itens = itens;
}


public ArrayList<Empresa> getItensFiltrados() {
	return itensFiltrados;
}

public void setItensFiltrados(ArrayList<Empresa> itensFiltrados) {
	this.itensFiltrados = itensFiltrados;
}



	@PostConstruct
	public void prepararPesquisa() {
		try {

			EmpresaDAO dao = new EmpresaDAO();
			itens = dao.listar();
			ConexaoFactory.fecharConexao();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void prepararNovo() {

		empresa = new Empresa();

	}

	public void novo() {
		try {

			EmpresaDAO dao = new EmpresaDAO();
			dao.salvar(empresa);

			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Empresa Salvo Com sucesso");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}


	

	public void excluir() {
		try {

			EmpresaDAO dao = new EmpresaDAO();
			dao.excluir(empresa);

			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Empresa Removido com Sucesso");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	

	public void editar() {
		try {

			EmpresaDAO dao = new EmpresaDAO();

			dao.editar(empresa);

			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Empresa editado com sucesso.");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

}
