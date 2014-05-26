/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.facades;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import proj8.entities.Users;
import proj8.entities.Whiteboard;

/**
 *
 * @author brunocosta
 */
@Stateless
public class WhiteboardFacade extends AbstractFacade<Whiteboard> {
    @PersistenceContext(unitName = "jdbcPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WhiteboardFacade() {
        super(Whiteboard.class);
    }
    
    public List<Whiteboard> WhiteboardFindByUser(Users user) {

        TypedQuery<Whiteboard> query = em.createNamedQuery(Whiteboard.WHITEBOARD_FIND_BY_USERNAME, Whiteboard.class);

        query.setParameter("username", user.getUsername());

        List<Whiteboard> quotation = new ArrayList<>();

        
            quotation.addAll(query.getResultList());
        

        return quotation;
    }
    
}
