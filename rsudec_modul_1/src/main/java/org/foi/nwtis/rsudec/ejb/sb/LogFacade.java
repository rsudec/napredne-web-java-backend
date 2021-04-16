/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ejb.sb;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.rsudec.ejb.eb.Log;

/**
 *
 * @author Robi
 */
@Stateless
public class LogFacade extends AbstractFacade<Log> implements LogFacadeLocal {

    @PersistenceContext(unitName = "NWTiS_Projekt_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogFacade() {
        super(Log.class);
    }

    @Override
    public void zapisiLog(String action, String url, String ip, String kor, BigInteger trajanje) {
        Log l = new Log();
        l.setIpadresa(ip);
        l.setKorisnik(kor);
        l.setUrl(url);
        l.setAkcija(action);
        l.setTrajanje(trajanje);
        l.setVrijeme(new Date());
        create(l);
    }

    @Override
    public List<Log> dajLogKorisnika(String korisnik) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Log> l = cq.from(Log.class);
        cq.select(l).where(cb.equal(l.get("korisnik"), korisnik));

        TypedQuery<Log> a = em.createQuery(cq);
        return a.getResultList();

    }
}
