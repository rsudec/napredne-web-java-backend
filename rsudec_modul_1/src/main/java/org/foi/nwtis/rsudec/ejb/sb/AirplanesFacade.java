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
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rest.podaci.AvionLeti;
import org.foi.nwtis.rest.podaci.Lokacija;
import org.foi.nwtis.rsudec.ejb.eb.Airplanes;
import org.foi.nwtis.rsudec.ejb.eb.Airports;
import org.foi.nwtis.rsudec.ejb.eb.Myairports;

/**
 *
 * @author Robi
 */
@Stateless
public class AirplanesFacade extends AbstractFacade<Airplanes> implements AirplanesFacadeLocal {

    @Inject
    AirportsFacadeLocal afl;

    @PersistenceContext(unitName = "NWTiS_Projekt_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AirplanesFacade() {
        super(Airplanes.class);
    }

    @Override
    public void dodajAvionLeti(AvionLeti a) {

        Airplanes novi = new Airplanes();
        novi.setIcao24(a.getIcao24());
        novi.setFirstseen(a.getFirstSeen());
        novi.setEstdepartureairport(afl.find(a.getEstDepartureAirport()));
        novi.setLastseen(a.getLastSeen());
        novi.setEstarrivalairport(a.getEstArrivalAirport());
        novi.setCallsign(a.getCallsign());
        novi.setEstdepartureairporthorizdistance(a.getEstDepartureAirportHorizDistance());
        novi.setEstdepartureairportvertdistance(a.getEstDepartureAirportVertDistance());
        novi.setEstarrivalairporthorizdistance(a.getEstArrivalAirportHorizDistance());
        novi.setEstarrivalairportvertdistance(a.getEstArrivalAirportVertDistance());
        novi.setDepartureairportcandidatescount(a.getDepartureAirportCandidatesCount());
        novi.setArrivalairportcandidatescount(a.getArrivalAirportCandidatesCount());
        novi.setStored(new Date());
        create(novi);
    }

    @Override
    public List<AvionLeti> dajAvioneSAerodroma(String icao, long vrijemeOd, long vrijemeDo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Airplanes> ma = cq.from(Airplanes.class);

        Predicate predikatIcao = cb.equal(ma.get("estdepartureairport").get("ident"), icao);
        Predicate predikatVrijemeOd = cb.greaterThan(ma.get("firstseen"), vrijemeOd);
        Predicate predikatVrijemeDo = cb.lessThan(ma.get("lastseen"), vrijemeDo);

        cq.select(ma).distinct(true).where(cb.and(predikatIcao, predikatVrijemeOd, predikatVrijemeDo));

        TypedQuery<Airplanes> a = em.createQuery(cq);
        List<Airplanes> result = a.getResultList();

        List<AvionLeti> resultAvionLeti = new ArrayList();
        for (Airplanes s : result) {
            resultAvionLeti.add(
                    new AvionLeti(
                            s.getIcao24(),
                            s.getFirstseen(),
                            s.getEstdepartureairport().getIdent(),
                            s.getLastseen(),
                            s.getEstarrivalairport(),
                            s.getCallsign(),
                            s.getEstdepartureairporthorizdistance(),
                            s.getEstdepartureairportvertdistance(),
                            s.getEstarrivalairporthorizdistance(),
                            s.getEstarrivalairportvertdistance(),
                            s.getDepartureairportcandidatescount(),
                            s.getArrivalairportcandidatescount()
                    ));

        }
        return resultAvionLeti;
    }

    @Override
    public List<AvionLeti> dajLetoveAviona(String icao24, long vrijemeOd, long vrijemeDo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Airplanes> ma = cq.from(Airplanes.class);

        Predicate predikatIcao = cb.equal(ma.get("icao24"), icao24);
        Predicate predikatVrijemeOd = cb.greaterThan(ma.get("firstseen"), vrijemeOd);
        Predicate predikatVrijemeDo = cb.lessThan(ma.get("lastseen"), vrijemeDo);

        cq.select(ma).distinct(true).where(cb.and(predikatIcao, predikatVrijemeOd, predikatVrijemeDo));

        TypedQuery<Airplanes> a = em.createQuery(cq);
        List<Airplanes> result = a.getResultList();

        List<AvionLeti> resultAvionLeti = new ArrayList();
        for (Airplanes s : result) {
            resultAvionLeti.add(
                    new AvionLeti(
                            s.getIcao24(),
                            s.getFirstseen(),
                            s.getEstdepartureairport().getIdent(),
                            s.getLastseen(),
                            s.getEstarrivalairport(),
                            s.getCallsign(),
                            s.getEstdepartureairporthorizdistance(),
                            s.getEstdepartureairportvertdistance(),
                            s.getEstarrivalairporthorizdistance(),
                            s.getEstarrivalairportvertdistance(),
                            s.getDepartureairportcandidatescount(),
                            s.getArrivalairportcandidatescount()
                    ));

        }
        return resultAvionLeti;
    }

    @Override
    public Boolean imaAvionaSAerodroma(String icao) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Airplanes> ma = cq.from(Airplanes.class);

//         Predicate predikatIcao = cb.equal(ma.get("icao24"), icao24);
//         Predicate predikatVrijemeOd = cb.greaterThan(ma.get("firstseen"), vrijemeOd);
//         Predicate predikatVrijemeDo = cb.lessThan(ma.get("lastseen"), vrijemeDo);
//         
        cq.select(ma).where(cb.equal(ma.get("estdepartureairport").get("ident"), icao));

        TypedQuery<Airplanes> a = em.createQuery(cq);
        List<Airplanes> result = a.getResultList();
        if (result == null || result.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public int obrisiAvioneSAerodroma(String icao) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Airplanes> cd = cb.createCriteriaDelete(Airplanes.class);

        //   CriteriaQuery cq = cb.createQuery();
        Root<Airplanes> ma = cd.from(Airplanes.class);
        cd.where(
                cb.equal(
                        ma.get("estdepartureairport").get("ident"), icao));

        int res = em.createQuery(cd).executeUpdate();
        return res;
    }
    
    public int brojLetovaSAerodroma(String icao){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Airplanes> ma = cq.from(Airplanes.class);

        cq.select(ma).where(cb.equal(ma.get("estdepartureairport").get("ident"), icao));

        TypedQuery<Airplanes> a = em.createQuery(cq);
        List<Airplanes> result = a.getResultList();

        
        return result.size();
    }
}
