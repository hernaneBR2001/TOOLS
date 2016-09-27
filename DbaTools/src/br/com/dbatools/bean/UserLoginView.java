package br.com.dbatools.bean;

//package org.primefaces.showcase.view.overlay;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import br.com.dbatools.dao.UserDAO;
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
   
    public void login(ActionEvent event) throws IOException{
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = true;
         

        boolean result = UserDAO.login(username, password);
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
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalido Login!",
                    "Por favor, loga novamente!"));
            JSFUtil.adicionarMensagemErro("Invalido Login!");
            
            ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().invalidate();
        	FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() +"/faces/pages/login.xhtml");
        }
        
        
    } 
    
    public void logout(ActionEvent event) throws IOException{
    	((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().invalidate();
    	FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() +"/faces/pages/login.xhtml");
    }
    
}