/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import proj8.entities.Users;
import proj8.facades.UsersFacade;
import proj8.webSocket.MyWhiteboard;

/**
 *
 * @author brunocosta
 */
@Named
@SessionScoped
public class WhiteboardController implements Serializable {

    private boolean abort;

    @Inject
    private MyWhiteboard mwb;

    @Inject
    private UsersFacade userFacade;

    public boolean isAbort() {
        return abort;
    }

    public void setAbort(boolean abort) {
        this.abort = abort;
    }

    public void changeEditAbort() {
        Users loggedUser = userFacade.getLoggedUser();
        if (abort) {
            mwb.getCounters().getEditingUsers().remove(loggedUser);
            if (mwb.getCounters().getEditingUsers().isEmpty()) {
//                mwb.getCounters().setEditingUsers(mwb.getCounters().getAbortingUsers());
//                mwb.getCounters().setAbortingUsers(new ArrayList<Users>());
//                abort = false;
                
                
            }
            else{
            mwb.getCounters().getAbortingUsers().add(loggedUser);}
            
        } else {
            mwb.getCounters().getEditingUsers().add(loggedUser);
            mwb.getCounters().getAbortingUsers().remove(loggedUser);
        }

        mwb.recieveChanges();

    }

}
