/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ejb.sb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rest.podaci.Lokacija;
import org.foi.nwtis.rsudec.ejb.eb.Airports;
import org.foi.nwtis.rsudec.ejb.eb.Myairports;
import org.foi.nwtis.rsudec.ejb.eb.Myairportslog;

/**
 *
 * @author Robi
 */
@Stateless
public class MyairportslogFacade extends AbstractFacade<Myairportslog> implements MyairportslogFacadeLocal {

    @PersistenceContext(unitName = "NWTiS_Projekt_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MyairportslogFacade() {
        super(Myairportslog.class);
    }
    
    @Override
    public Boolean aerodromJeObradenZaDatum(String ident, Date datum){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Myairportslog> mal = cq.from(Myairportslog.class);
         
        cq.select(mal).where(cb.and(cb.equal(mal.get("myairportslogPK").get("ident"), ident), cb.equal(mal.get("myairportslogPK").get("flightdate"), datum) ) );
         
        TypedQuery<Myairportslog> a = em.createQuery(cq);
        List<Myairportslog> result = a.getResultList();
        if(!result.isEmpty())
            return true;
        else if(result.isEmpty())
            return false;
        return false;
    }
    @Override
    public int brojDanaSPodacima(String icao){
             CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
         Root<Myairportslog> mal = cq.from(Myairportslog.class);
         
         cq.select(mal).where(cb.equal(mal.get("myairportslogPK").get("ident"), icao));
         
         TypedQuery<Myairportslog> a = em.createQuery(cq);
         List<Myairportslog> result = a.getResultList();
         
         
        return result.size();
    }
    
}
