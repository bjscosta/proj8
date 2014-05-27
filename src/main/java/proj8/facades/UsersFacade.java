/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.facades;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import proj8.entities.Users;

/**
 *
 * @author brunocosta
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "jdbcPU")
    private EntityManager em;
    
    @Resource
    private SessionContext ctx;
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

    public UsersFacade() {
        super(Users.class);
    }
    
    public Users LoggedUser(){
        
        Users u = null;
        
        if(ctx.getCallerPrincipal()!=null){
            String name = ctx.getCallerPrincipal().getName();
            u = find(name);
            
        }    
       
        return u;
                
        
    }
    
}
