/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import proj8.entities.Users;
import proj8.facades.UsersFacade;
import proj8.pojos.Counters;
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

    private Counters counters;
    
    

    public Counters getCounters() {
        return counters;
    }

    public void setCounters(Counters counters) {
        this.counters = counters;

    }

    @PostConstruct
    public void init() {
        counters = mwb.getCounters();
        
    }

    public String userName() {
        if (uf.getLoggedUser() != null) {

            if (counters.getEditingUsers() == null) {
                List<Users> us = new ArrayList<>();
                counters.setEditingUsers(us);
                mwb.setCounters(counters);
            }

            if (counters.getEditingUsers().size() > 0) {
                for (Users u : counters.getEditingUsers()) {
                    if (!u.getUsername().equals(uf.getLoggedUser().getUsername())) {
                        counters.getEditingUsers().add(uf.getLoggedUser());
                        mwb.setCounters(counters);
                    }
                }
            } else {
                
                counters.getEditingUsers().add(uf.getLoggedUser());
                mwb.setCounters(counters);
            }
            return uf.getLoggedUser().getUsername();

        } else {
            return "";
        }
    }

    public boolean seeIfLogged() {
        if (uf.getLoggedUser() == null) {
            return false;
        } else {
            return true;
        }
    }

}
