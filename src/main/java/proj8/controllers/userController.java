/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import proj8.facades.UsersFacade;

/**
 *
 * @author brunocosta
 */

@Named
@RequestScoped
public class userController {
    
    @Inject
    private UsersFacade uf;
    
    
    public String userName(){
        if(uf.getLoggedUser()!=null){
            return uf.getLoggedUser().getUsername();
        }
        else{
            return "";
        }
    }
    
    public boolean isLogged(){
        if(uf.getLoggedUser()==null){
            return false;
        }
        
        else{
            return true;
        }
    }

    
}
