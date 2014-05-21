/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.controllers;


import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author brunocosta
 */

@Named
@RequestScoped
public class LogoutBean {
    
    
    public String logout(){
        String destination = "/index?faces-redirect=true";
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try{
            request.logout();
        }
        
        catch(ServletException e){
            destination = "/loginError?faces-redirect=true";
        }
        
        return destination;
    
    }
    
    
    
}
