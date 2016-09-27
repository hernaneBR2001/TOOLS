package br.com.dbatools.domain;

public class CadastroDbAppServidor {
private Long cod_cadastro;
private String tipo;
private Cmdb cmdb = new Cmdb();
private String usuario_bd_app;
private String senha_bd_app;

public Long getCod_cadastro() {
	return cod_cadastro;
}
public void setCod_cadastro(Long cod_cadastro) {
	this.cod_cadastro = cod_cadastro;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}
public Cmdb getCmdb() {
	return cmdb;
}
public void setCmdb(Cmdb cmdb) {
	this.cmdb = cmdb;
}
public String getUsuario_bd_app() {
	return usuario_bd_app;
}
public void setUsuario_bd_app(String usuario_bd_app) {
	this.usuario_bd_app = usuario_bd_app;
}
public String getSenha_bd_app() {
	return senha_bd_app;
}
public void setSenha_bd_app(String senha_bd_app) {
	this.senha_bd_app = senha_bd_app;
}

}
