/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.zrna;


import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import lombok.Getter;
import lombok.Setter;
import org.foi.nwtis.rsudec.ejb.eb.Log;
import org.foi.nwtis.rsudec.ejb.sb.LogFacadeLocal;
import org.foi.nwtis.rsudec.konfiguracije.bp.BP_Konfiguracija;

/**
 *
 * @author Robi
 */
@Named(value = "dnevnik")
@RequestScoped
public class Dnevnik implements Serializable {
    
    @Inject
    PrijavaKorisnika pk;
    @EJB
    LogFacadeLocal lfl;
    @Getter
    @Setter
    List<Log> dnevnik;
    @Getter
    String pages;
    @Inject
    ServletContext context;
    @PostConstruct
    void init() {
        dnevnik = lfl.dajLogKorisnika(pk.getKorisnik());
        BP_Konfiguracija bpk = (BP_Konfiguracija) context.getAttribute("BP_Konfig");
        pages = bpk.getKonfig().dajPostavku("dnevnik.stranicenje");
        
    }
}
