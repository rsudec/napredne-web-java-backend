/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ejb.sb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.rsudec.ejb.eb.Airplanes;
import org.foi.nwtis.rsudec.ejb.eb.Log;
import org.foi.nwtis.rsudec.ejb.eb.Mqttporuka;

/**
 *
 * @author Robi
 */
@Stateless
public class MqttporukaFacade extends AbstractFacade<Mqttporuka> implements MqttporukaFacadeLocal {

    @PersistenceContext(unitName = "NWTiS_Projekt_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MqttporukaFacade() {
        super(Mqttporuka.class);
    }
    @Override
    public List<Mqttporuka> dajMqttKorisnika(String korisnik){
    
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Mqttporuka> m = cq.from(Mqttporuka.class);
        cq.select(m).where(cb.equal(m.get("korisnik"), korisnik));

        TypedQuery<Mqttporuka> a = em.createQuery(cq);
        return a.getResultList();
    }

    @Override
    public int obrisiPorukeKorisnika(String korisnik) {
       CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Mqttporuka> cd = cb.createCriteriaDelete(Mqttporuka.class);

        //   CriteriaQuery cq = cb.createQuery();
        Root<Mqttporuka> mqtt = cd.from(Mqttporuka.class);
        cd.where(
                cb.equal(
                        mqtt.get("korisnik"), korisnik));

        int res = em.createQuery(cd).executeUpdate();
        return res;
    }
}
