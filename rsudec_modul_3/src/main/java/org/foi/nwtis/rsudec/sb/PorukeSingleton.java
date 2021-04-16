/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.sb;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import lombok.Getter;
import org.foi.nwtis.rsudec.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.rsudec.podaci.PorukaMQTT;
import org.foi.nwtis.rsudec.podaci.PorukaSocket;
import org.foi.nwtis.rsudec.podaci.PorukaWebSocket;

/**
 *
 * @author Robi
 */
@Singleton
@Startup
public class PorukeSingleton {

    @Getter
    List<PorukaSocket> porukaSocket = new ArrayList<>();
    @Getter
    List<PorukaWebSocket> porukaWebSocket = new ArrayList<>();
    @Getter
    List<PorukaMQTT> porukaMQTT = new ArrayList<>();
    BP_Konfiguracija bpk;

    @PostConstruct
    private void init() {
    }

    public void dodajSocketPoruku(PorukaSocket nov) {
        porukaSocket.add(nov);
    }

    public void dodajWebSocketPoruku(PorukaWebSocket nov) {
        porukaWebSocket.add(nov);
    }

    public void dodajMQTTPoruku(PorukaMQTT nov) {
        porukaMQTT.add(nov);
    }
    
    public List<PorukaSocket> dajPorukeSocketaKorisnika(String korisnik){
        
        List<PorukaSocket> pomoc = new ArrayList<>();
        for (PorukaSocket ps : porukaSocket) {
            if (ps.getKorisnik().equals(korisnik)) {
                pomoc.add(ps);
            }
        }
        return pomoc;
    }
    public List<PorukaWebSocket> dajPorukeWebSocketaKorisnika(String korisnik){
        List<PorukaWebSocket> pomoc = new ArrayList<>();
        for (PorukaWebSocket pws : porukaWebSocket) {
            if (pws.getKorisnik().equals(korisnik)) {
                pomoc.add(pws);
            }
        }
        return pomoc;
    }
    public List<PorukaMQTT> dajPorukeMQTTKorisnika(String korisnik){
        List<PorukaMQTT> pomoc = new ArrayList<>();
        for (PorukaMQTT pm : porukaMQTT) {
            if (pm.getKorisnik().equals(korisnik)) {
                pomoc.add(pm);
            }
        }
        return pomoc;
    }

    public void setKonf(BP_Konfiguracija bpk) {
        this.bpk = bpk;

        String file = bpk.getKonfig().dajPostavku("serijalizacija.datoteka");
        File f = new File(file);
        if (!f.exists()) {
            System.out.println("ne postoji datoteka serijaliziranih poruka");
        } else {
            deserijalizacijaPodataka();
        }

//        deserijalizacijaPorukeSocket(f);
//        deserijalizacijaPorukeWebSocket(f);
////        deserijalizacijaPorukeMQTT(f);
        System.out.println("ServerSocket");
        porukaSocket.forEach((x) -> System.out.println(x.getId() + ", " + x.getKorisnik() + ", " + x.getKomanda()));
        System.out.println("WebSocket");
        porukaWebSocket.forEach((x) -> System.out.println(x.getId() + ", " + x.getKorisnik() + ", " + x.getAerodrom()));
        System.out.println("MQTT");
        porukaMQTT.forEach((x) -> System.out.println(x.getId() + ", " + x.getKorisnik() + ", " + x.getPoruka()));

    }

    public void obrisiOdKorisnikaSocket(String korisnik) {
        List<PorukaSocket> pomoc = new ArrayList<>();
        for (PorukaSocket ps : porukaSocket) {
            if (!ps.getKorisnik().equals(korisnik)) {
                pomoc.add(ps);
            }
        }
        porukaSocket = pomoc;
    }

    public void obrisiOdKorisnikaWebSocket(String korisnik) {
        List<PorukaWebSocket> pomoc = new ArrayList<>();
        for (PorukaWebSocket pws : porukaWebSocket) {
            if (!pws.getKorisnik().equals(korisnik)) {
                pomoc.add(pws);
            }
        }
        porukaWebSocket = pomoc;
    }

    public void obrisiOdKorisnikaMQTT(String korisnik){
    List<PorukaMQTT> pomoc = new ArrayList<>();
        for(PorukaMQTT pm : porukaMQTT){
            if(!pm.getKorisnik().equals(korisnik))
                pomoc.add(pm);
        }
        porukaMQTT = pomoc;}

    @PreDestroy
    private void destroy() {
        HashMap<String, List> sve = new HashMap<>();
        sve.put("PorukaSocket", porukaSocket);
        sve.put("PorukaWebSocket", porukaWebSocket);
        sve.put("PorukaMQTT", porukaMQTT);

        try {
            FileOutputStream out = null;
            out = new FileOutputStream(bpk.getKonfig().dajPostavku("serijalizacija.datoteka"));
            ObjectOutputStream s = new ObjectOutputStream(out);
            s.writeObject(sve);
            s.close();
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PorukeSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PorukeSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deserijalizacijaPodataka() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(bpk.getKonfig().dajPostavku("serijalizacija.datoteka"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            HashMap<String, List> retrieved = (HashMap<String, List>) ois.readObject();
            porukaSocket = retrieved.get("PorukaSocket");
            porukaWebSocket = retrieved.get("PorukaWebSocket");
            porukaMQTT = retrieved.get("PorukaMQTT");

            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PorukeSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PorukeSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PorukeSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(PorukeSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void serijalizacijaPodataka() {
    }

}
