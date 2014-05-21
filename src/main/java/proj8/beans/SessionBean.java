/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.beans;

import java.io.Serializable;
import java.nio.ByteBuffer;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author brunocosta
 */
@SessionScoped
public class SessionBean implements Serializable{
    
    
    
    private ByteBuffer activeImage;
    
    public SessionBean(ByteBuffer data){
        activeImage = data;
    }

    public ByteBuffer getActiveImage() {
        return activeImage;
    }

    public void setActiveImage(ByteBuffer activeImage) {
        this.activeImage = activeImage;
    }

    
    
    
    
    
    
}
