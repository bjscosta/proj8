/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import proj8.entities.Users;

/**
 *
 * @author brunocosta
 */
public class Counters implements Serializable {
    
    private List<Users> abortingUsers = new ArrayList<>();
    private List<Users> editingUsers = new ArrayList<>();

    public List<Users> getAbortingUsers() {
        return abortingUsers;
    }

    public void setAbortingUsers(List<Users> abortingUsers) {
        this.abortingUsers = abortingUsers;
    }

    public List<Users> getEditingUsers() {
        return editingUsers;
    }

    public void setEditingUsers(List<Users> editingUsers) {
        this.editingUsers = editingUsers;
    }

    
    
    
    
}
