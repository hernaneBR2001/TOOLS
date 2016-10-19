package br.com.dbatools.bean;

//package org.primefaces.showcase.view.overlay;

import java.io.IOException;
import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import br.com.dbatools.util.JSFUtil;


 
@ManagedBean
@SessionScoped
public class UserLoginView {
     
    private String username;
     
    private String password;
 
    private String computador;
    
 

    public String getComputador() {
        return computador;
    }
 
    public void setComputador(String computador) {
        this.computador = computador;
    }
  
    
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    private boolean validateLogin(String user, String senha) throws Exception {

          
			//Bind inicial, usuário de aplicação, navegação na árvore ldap
			//UserLdap userLdap = null;
			Hashtable<String, String> env = new Hashtable<String, String>();
			String portLdap = "389";
			String host = "ldapctbc.network.ctbc";
			String userbind = "cn=BINDDBATOOLS,ou=ESPECIAL,cn=Users,dc=network,dc=ctbc";
			String baseDN = "dc=network,dc=ctbc";
			boolean possuiPerfil = false;
			
			env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
			env.put("java.naming.provider.url", "ldap://"+ host+ ":" + portLdap);
			env.put("java.naming.security.principal", userbind);
			env.put("java.naming.security.credentials", "B@T00LS#");
			env.put("java.naming.security.authentication", "simple");
	   			
			InitialDirContext ctx = new InitialDirContext(env);
		
			//Busca DN completo do usuário
			SearchControls sc = new SearchControls();
			sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
			//String[] returningAtts = { "cn", "mail", "uid" };
			//sc.setReturningAttributes(returningAtts);
			String filterSearch = "(&(objectClass=person)(uid=" + user + "))";
		
			NamingEnumeration<SearchResult> cursor = ctx.search(baseDN, filterSearch, sc);			
			String fullUserDN = "";
		
			SearchResult result = null;
			if (cursor.hasMoreElements()) {
				result = (SearchResult)cursor.nextElement();
				fullUserDN = result.getNameInNamespace();
			}
		


	        
	        String[] profileFilterSearch = {"(&(objectClass=groupOfUniqueNames)(CN=OPERATOR))","(&(objectClass=groupOfUniqueNames)(CN=ADMINISTRADOR))",
	        		"(&(objectClass=groupOfUniqueNames)(CN=EMPRESAS))","(&(objectClass=groupOfUniqueNames)(CN=MONITORAMENTO))"		
	        };
	        for (int i = 0; i < profileFilterSearch.length; i++){
		        cursor = ctx.search(baseDN, profileFilterSearch[i], sc);
				if (cursor.hasMoreElements()) {
					result = (SearchResult)cursor.nextElement();
				}
				
		        Attribute uniquemember = result.getAttributes().get("uniquemember");
		        
		        if (uniquemember != null) {
		            for (int j = 0; j < uniquemember.size(); j++){
		                if (uniquemember.get(j).toString().toLowerCase().equals(fullUserDN.toLowerCase())){
		                	System.out.println(uniquemember.get(j).toString());
		                	possuiPerfil = true;
		                	if (profileFilterSearch[i].contains("OPERATOR")){
		                		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("profile", "OPERATOR");
		                	}
		                	if (profileFilterSearch[i].contains("MONITORAMENTO")){
		                		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("profile", "MONITORAMENTO");
		                	}
		                	if (profileFilterSearch[i].contains("EMPRESAS")){
		                		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("profile", "EMPRESAS");
		                	}
		                	if (profileFilterSearch[i].contains("ADMINISTRADOR")){
		                		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("profile", "ADMINISTRADOR");
		                	}
		                	break;
		                }
		            }
		        }
	        }
	        
	            if (!possuiPerfil){
	            	throw new CredentialException("Usu�rio n�o possui perfil no CAL");
	            
	        }	        

		
			//Bind do username, autenticação por usuário/atendente
			//se dar falha de usuário senha, dispara exceção
	        env.put("java.naming.security.principal", fullUserDN);
	        env.put("java.naming.security.credentials", senha);


	        InitialDirContext ctx2 = new InitialDirContext(env);






	        ctx2.close();
	        ctx.close();
	                
	
			return true;
   

    }    
   
    public void login(ActionEvent event) throws IOException{
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = true;
        boolean result = false;
        
        try{
        	result = validateLogin(username, password);
        }
        catch(Exception e){
        	message = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), username);
        }
        
        if (result) {

            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bemvindo", username);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", username);
            ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().setAttribute("username", username);
     
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("computador", computador);
            ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().setAttribute("computador", computador);
     
            
            
	        FacesContext.getCurrentInstance().addMessage(null, message);
	        
	        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() +"/faces/pages/ferramenta.xhtml");
	        context.addCallbackParam("loggedIn", loggedIn);

        } else {
            FacesContext.getCurrentInstance().addMessage(null,message);
            //JSFUtil.adicionarMensagemErro("Invalido Login!");
            
            //((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().invalidate();
        	//FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() +"/faces/pages/login.xhtml");
        }
        
        
    } 
    
    public void logout(ActionEvent event) throws IOException{
    	((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().invalidate();
    	FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() +"/faces/pages/login.xhtml");
    }
    
}