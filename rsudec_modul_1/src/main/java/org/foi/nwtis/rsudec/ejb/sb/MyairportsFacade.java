/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ejb.sb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.podaci.Airport;
import org.foi.nwtis.rest.podaci.Lokacija;
import org.foi.nwtis.rsudec.ejb.eb.Airports;
import org.foi.nwtis.rsudec.ejb.eb.Myairports;

/**
 *
 * @author Robi
 */
@Stateless
public class MyairportsFacade extends AbstractFacade<Myairports> implements MyairportsFacadeLocal {

    @PersistenceContext(unitName = "NWTiS_Projekt_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MyairportsFacade() {
        super(Myairports.class);
    }
    
    @Override
    public List<Aerodrom> dajAerodromeKojiSePrate(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
         Root<Myairports> ma = cq.from(Myairports.class);
         
         cq.select(ma.get("ident")).distinct(true);
         
         TypedQuery<Airports> a = em.createQuery(cq);
         List<Airports> result = a.getResultList();
         
         List<Aerodrom> resultAerodrom = new ArrayList();
        for(Airports s : result){
             //System.out.println(s.getIdent());
            resultAerodrom.add(new Aerodrom(s.getIdent(), s.getName(), s.getIsoCountry(), new Lokacija(s.getCoordinates().split(", ")[1],s.getCoordinates().split(", ")[0])));
                 
        }
        return resultAerodrom;
    }
    
    @Override
    public List<Aerodrom> dajAerodromeKorisnika(String korisnik){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
         Root<Myairports> ma = cq.from(Myairports.class);
         
         cq.select(ma.get("ident")).distinct(true).where(cb.equal(ma.get("username").get("korIme"), korisnik));
         
         TypedQuery<Airports> a = em.createQuery(cq);
         List<Airports> result = a.getResultList();
         
         List<Aerodrom> resultAerodrom = new ArrayList();
        for(Airports s : result){
             //System.out.println(s.getIdent());
            resultAerodrom.add(new Aerodrom(s.getIdent(), s.getName(), s.getIsoCountry(), new Lokacija(s.getCoordinates().split(", ")[1],s.getCoordinates().split(", ")[0])));
                 
        }
        return resultAerodrom;
    }
    
    @Override
    public Boolean obrisiAerodromKorisnika(String korisnik, String icao){
     CriteriaBuilder cb = em.getCriteriaBuilder();
     CriteriaDelete<Myairports> cd = cb.createCriteriaDelete(Myairports.class);
     
     
     //   CriteriaQuery cq = cb.createQuery();
         Root<Myairports> ma = cd.from(Myairports.class);
         cd.where(
                 cb.and(
                         cb.equal(
                                 ma.get("username").get("korIme"), korisnik), 
                         cb.equal(
                                 ma.get("ident").get("ident"), icao)));
         
        int res = em.createQuery(cd).executeUpdate();
        return res == 1;
    }
    
    @Override
    public Myairports dohvatiZapis(String korisnik, String icao){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
         Root<Myairports> ma = cq.from(Myairports.class);
         
         cq.select(ma).where(
                 cb.and(
                         cb.equal(
                                 ma.get("username").get("korIme"), korisnik), 
                         cb.equal(
                                 ma.get("ident").get("ident"), icao)));;
         
         TypedQuery<Myairports> a = em.createQuery(cq);
         List<Myairports> result = a.getResultList();
         if(result.isEmpty())
             return null;
         return result.get(0);
    }
    @Override
    public int brojPratiocaAerodroma(String icao){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
         Root<Myairports> ma = cq.from(Myairports.class);
         
         cq.select(ma.get("ident")).where(cb.equal(ma.get("ident").get("ident"), icao));
         
         TypedQuery<Airports> a = em.createQuery(cq);
         List<Airports> result = a.getResultList();
         
         
        return result.size();
    }
    
}
