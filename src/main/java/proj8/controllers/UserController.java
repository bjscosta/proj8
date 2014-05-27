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
import proj8.webSocket.MyWhiteboard;

/**
 *
 * @author brunocosta
 */
@Named
@RequestScoped
public class UserController {

    @Inject
    private UsersFacade uf;

    @Inject
    private MyWhiteboard mwb;
    
    


    public String userName() {
        if (uf.LoggedUser() != null) {

            
            return uf.LoggedUser().getUsername();

        } else {
            return "";
        }
    }
    
    

    public boolean seeIfLogged() {
        if (uf.LoggedUser() == null) {
            return false;
        } else {
            return true;
        }
    }
    
    
    

}
