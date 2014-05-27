/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import proj8.entities.Users;
import proj8.entities.Whiteboard;
import proj8.facades.UsersFacade;
import proj8.facades.WhiteboardFacade;
import proj8.webSocket.MyWhiteboard;

/**
 *
 * @author brunocosta
 */
@Named
@SessionScoped
public class WhiteboardController implements Serializable {

    private boolean abort;
    private List<Whiteboard> whiteList;
    private Whiteboard selectedW;
    private StreamedContent finalImage;
    private boolean haveW;

    @Inject
    private MyWhiteboard mwb;

    @Inject
    private UsersFacade userFacade;
    
    @Inject
    private WhiteboardFacade wFacade;
    
    
    @PostConstruct
    public void init(){
        whiteList = wFacade.WhiteboardFindByUser(userFacade.LoggedUser());
        selectedW = new Whiteboard();
        finalImage = new DefaultStreamedContent();
    }

    public boolean isAbort() {
        return abort;
    }

    public void setAbort(boolean abort) {
        this.abort = abort;
    }


    public List<Whiteboard> getWhiteList() {
        whiteList = wFacade.WhiteboardFindByUser(userFacade.LoggedUser());
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

    public StreamedContent getFinalImage() {
        return finalImage;
    }

    public void setFinalImage(StreamedContent finalImage) {
        this.finalImage = finalImage;
    }

    public boolean isHaveW() {
        return haveW;
    }
    
    
    
    
    

    public void changeEditAbort() {
        Users loggedUser = userFacade.LoggedUser();
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
        whiteList = wFacade.WhiteboardFindByUser(userFacade.LoggedUser());
    }
   
    
    public void makeImage() throws IOException{
        
        haveW = true;
        byte[] imageData = selectedW.getImage();
        BufferedImage img = new BufferedImage(600, 300, BufferedImage.TYPE_4BYTE_ABGR);
        img.getRaster().setDataElements(0, 0, 600, 300, imageData);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(img, "png", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        StreamedContent image = new DefaultStreamedContent(is, "image/png");
        finalImage = image;
    }
    
    
    public void deleteWhiteboard(Long wId){
        
        wFacade.remove(wFacade.find(wId));
        whiteList = wFacade.WhiteboardFindByUser(userFacade.LoggedUser());
                
        
    }

}
