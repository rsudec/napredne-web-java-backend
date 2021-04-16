/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.web.zrna;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import lombok.Getter;
import org.foi.nwtis.rsudec.podaci.PorukaMQTT;
import org.foi.nwtis.rsudec.podaci.PorukaSocket;
import org.foi.nwtis.rsudec.podaci.PorukaWebSocket;
import org.foi.nwtis.rsudec.sb.PorukeStateful;

/**
 *
 * @author Robi
 */
@Named(value = "jMSPoruke")
@RequestScoped
public class JMSPoruke {

    @Getter
    List<PorukaWebSocket> red1;
    @Getter
    List<PorukaSocket> red2;
    @Getter
    List<PorukaMQTT> red3;
    
    @Inject
    PorukeStateful stateful;
    @Inject
    PrijavaKorisnika pk;
    @PostConstruct
    void init(){
        red2 = stateful.dajPorukeSocketa(pk.getKorisnik());
        red1 = stateful.dajPorukeWebSocketa(pk.getKorisnik());
        red3 = stateful.dajPorukeMQTT(pk.getKorisnik());
    }
    
    public void obrisiRed1(){
        red1 = null;
        stateful.obrisiWebSocket(pk.getKorisnik());
    }
    public void obrisiRed2(){
        red2 = null;
        stateful.obrisiSocket(pk.getKorisnik());
    }
    public void obrisiRed3(){
        red3 = null;
        stateful.obrisiMQTT(pk.getKorisnik());
    }
    
    
}
