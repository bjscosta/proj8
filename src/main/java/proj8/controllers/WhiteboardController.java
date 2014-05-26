/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import proj8.entities.Users;
import proj8.entities.Whiteboard;
import proj8.facades.UsersFacade;
import proj8.facades.WhiteboardFacade;
import proj8.tools.WhiteboardDataModel;
import proj8.webSocket.MyWhiteboard;

/**
 *
 * @author brunocosta
 */
@Named
@SessionScoped
public class WhiteboardController implements Serializable {

    private boolean abort;
    private WhiteboardDataModel whiteModel;
    private List<Whiteboard> whiteList;
    private Whiteboard selectedW;

    @Inject
    private MyWhiteboard mwb;

    @Inject
    private UsersFacade userFacade;
    
    @Inject
    private WhiteboardFacade wFacade;

    public boolean isAbort() {
        return abort;
    }

    public void setAbort(boolean abort) {
        this.abort = abort;
    }

    public WhiteboardDataModel getWhiteModel() {
        return whiteModel;
    }

    public void setWhiteModel(WhiteboardDataModel wModel) {
        this.whiteModel = wModel;
    }

    public List<Whiteboard> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<Whiteboard> whiteList) {
        this.whiteList = whiteList;
    }

    public Whiteboard getSelectedW() {
        return selectedW;
    }

    public void setSelectedW(Whiteboard selectedW) {
        this.selectedW = selectedW;
    }
    
    
    
    
    @PostConstruct
    public void init(){
        whiteList = wFacade.WhiteboardFindByUser(userFacade.getLoggedUser());
        whiteModel = new WhiteboardDataModel(whiteList);
    }

    public void changeEditAbort() {
        Users loggedUser = userFacade.getLoggedUser();
        if (abort) {
            mwb.getCounters().getAbortingUsers().add(loggedUser);
            mwb.getCounters().getEditingUsers().remove(loggedUser);

            
        } else {
            mwb.getCounters().getEditingUsers().add(loggedUser);
            mwb.getCounters().getAbortingUsers().remove(loggedUser);
        }

        mwb.recieveChanges();

    }
    
    public void updateList(){
        whiteList = wFacade.WhiteboardFindByUser(userFacade.getLoggedUser());
        System.out.println(whiteList.size());
    }

}
