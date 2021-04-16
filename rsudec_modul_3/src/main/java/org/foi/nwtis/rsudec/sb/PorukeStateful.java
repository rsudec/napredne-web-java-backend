/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.sb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import lombok.Getter;
import org.foi.nwtis.rsudec.podaci.PorukaMQTT;
import org.foi.nwtis.rsudec.podaci.PorukaSocket;
import org.foi.nwtis.rsudec.podaci.PorukaWebSocket;

/**
 *
 * @author Robi
 */
@Stateful
@LocalBean
public class PorukeStateful {

    @Inject
    PorukeSingleton singleton;

    public List<PorukaWebSocket> dajPorukeWebSocketa(String korisnik){
    
        return singleton.dajPorukeWebSocketaKorisnika(korisnik);
        
    }
    public List<PorukaSocket> dajPorukeSocketa(String korisnik){
    
        return singleton.dajPorukeSocketaKorisnika(korisnik);
        
    }
    public List<PorukaMQTT> dajPorukeMQTT(String korisnik){
    
        return singleton.dajPorukeMQTTKorisnika(korisnik);
        
    }
    public void obrisiWebSocket(String korisnik){
        singleton.obrisiOdKorisnikaWebSocket(korisnik);
    }
    public void obrisiSocket(String korisnik){
        singleton.obrisiOdKorisnikaSocket(korisnik);
    }
    public void obrisiMQTT(String korisnik){
        singleton.obrisiOdKorisnikaMQTT(korisnik);
    }
    
}
