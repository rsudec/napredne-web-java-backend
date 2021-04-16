/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ejb.sb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rest.podaci.Lokacija;
import org.foi.nwtis.rsudec.ejb.eb.Airports;

/**
 *
 * @author Robi
 */
@Stateless
public class AirportsFacade extends AbstractFacade<Airports> implements AirportsFacadeLocal {

    @Inject
    MyairportsFacadeLocal mafl;
    
    @PersistenceContext(unitName = "NWTiS_Projekt_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AirportsFacade() {
        super(Airports.class);
    }
    public List<Aerodrom> dajPoNazivu(String naziv){
        naziv = "%" + naziv + "%";
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        
        Root<Airports> ma = cq.from(Airports.class);
        cq.select(ma).where(cb.like(ma.get("name"), naziv));
        
        TypedQuery<Airports> a = em.createQuery(cq);
        List<Airports> res = a.getResultList();
        
        List<Aerodrom> resAerodrom = new ArrayList<>();
        for(Airports air : res){
            resAerodrom.add(new Aerodrom(air.getIdent(), air.getName(), air.getIsoCountry(), new Lokacija(air.getCoordinates().split(", ")[1], air.getCoordinates().split(", ")[0])));
        }
        if(resAerodrom.isEmpty())
            return null;
        return resAerodrom;
    }
    public List<Aerodrom> dajPoDrzavi(String isoCountry){
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        
        Root<Airports> ma = cq.from(Airports.class);
        cq.select(ma).where(cb.equal(ma.get("isoCountry"), isoCountry));
        
        TypedQuery<Airports> a = em.createQuery(cq);
        List<Airports> res = a.getResultList();
        
        List<Aerodrom> resAerodrom = new ArrayList<>();
        for(Airports air : res){
            resAerodrom.add(new Aerodrom(air.getIdent(), air.getName(), air.getIsoCountry(), new Lokacija(air.getCoordinates().split(", ")[1], air.getCoordinates().split(", ")[0])));
        }
        if(resAerodrom.isEmpty())
            return null;
        return resAerodrom;
    }
    
    public double dajUdaljenostAerodroma(String icao1, String icao2){
        
        Airports a1 = find(icao1);
        Airports a2 = find(icao2);
        if (a1 != null && a2 != null) {
            double lon1 = Double.parseDouble(a1.getCoordinates().split(", ")[1]);
            double lat1 = Double.parseDouble(a1.getCoordinates().split(", ")[0]);
            double lon2 = Double.parseDouble(a2.getCoordinates().split(", ")[1]);
            double lat2 = Double.parseDouble(a2.getCoordinates().split(", ")[0]);

            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;
            return dist;
        }
        
        return -1;
        
    }
    @Override
    public List<Aerodrom> dajAerodromeUKrugu(String korisnik, String icao, String min, String max){
     
        Airports a = find(icao);
        if(a == null)
            return null;
        
        List<Aerodrom> aerodromiKorisnika = mafl.dajAerodromeKorisnika(korisnik);
        
        List<Aerodrom> result = new ArrayList();
        
        for(Aerodrom aer : aerodromiKorisnika){
            double udaljenost = dajUdaljenostAerodroma(icao, aer.getIcao());
            if(udaljenost < Integer.parseInt(max) && udaljenost > Integer.parseInt(min))
                result.add(aer);
        }
        if(result.isEmpty())
            return null;
        return result;
    }
    
    
}
