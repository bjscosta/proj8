/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proj8.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
}
