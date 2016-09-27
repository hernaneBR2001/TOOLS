package br.com.dbatools.domain;

public class Usuario {
	private Long cod_usuario;
	private String nom_usuario;
	private String usuario;
	private String senha;
	private String telefone;
	private String email;
	private String usuarioldap;
	private Empresa empresa = new Empresa();
	private Perfil perfil = new Perfil();
	
	
	
	
	
	public String getUsuarioldap() {
		return usuarioldap;
	}
	public void setUsuarioldap(String usuarioldap) {
		this.usuarioldap = usuarioldap;
	}
	public Long getCod_usuario() {
		return cod_usuario;
	}
	public void setCod_usuario(Long cod_usuario) {
		this.cod_usuario = cod_usuario;
	}
	public String getNom_usuario() {
		return nom_usuario;
	}
	public void setNom_usuario(String nom_usuario) {
		this.nom_usuario = nom_usuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
