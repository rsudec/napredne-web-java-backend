/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ejb.sb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.foi.nwtis.rsudec.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.rsudec.web.dretve.PreuzimanjeLetova;

/**
 *
 * @author Robi
 */
@Singleton @Startup
public class Start {

    @EJB
    public MyairportsFacadeLocal mafl;
    
    @EJB
    MyairportslogFacadeLocal malfl;
    
    @EJB
    AirplanesFacadeLocal afl;
    
    PreuzimanjeLetova pl;
    
    @PostConstruct
    void construct(){
        System.out.println("MODUL 2 POKRENUT");
    }
    
    public void start(BP_Konfiguracija bpk){
        pl = new PreuzimanjeLetova(bpk, mafl, malfl, afl);
        pl.start();
    }
    
    @PreDestroy
    void destroy(){
        if(pl!=null)
            pl.interrupt();
        System.out.println("MODUL 2 UGAŠEN");
    }
    
    
    
    public void test() {
        System.out.println("teest");
        
    }
}
