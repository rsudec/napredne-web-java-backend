/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.slusaci;

import com.google.gson.Gson;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.validation.ConstraintViolationException;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rsudec.ejb.eb.Mqttporuka;
import org.foi.nwtis.rsudec.ejb.sb.MqttporukaFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.MyairportsFacadeLocal;
import org.foi.nwtis.rsudec.jms.JMS_Red_3;

/**
 *
 * @author Robi
 */
public class MqttObrada extends Thread {
    
    JMS_Red_3 jms3;
    int velicinaSkupine;
    MqttporukaFacadeLocal mqfl;
    MyairportsFacadeLocal mafl;
    String korisnik;
    String poruka;
    String imePrezime;
    static int brojPoruka = 0;
    SimpleDateFormat sdf;
    
    public MqttObrada(String poruka, MqttporukaFacadeLocal mqfl, MyairportsFacadeLocal mafl, String imePrezime, String korisnik, JMS_Red_3 jms3, int velicinaSkupine) {
        this.velicinaSkupine = velicinaSkupine;
        this.mqfl = mqfl;
        this.imePrezime = imePrezime;
        this.poruka = poruka;
        this.mafl = mafl;
        this.korisnik = korisnik;
        this.jms3 = jms3;
    }
    
    @Override
    public void run() {
        Gson gson = new Gson();
        StringReader sr = new StringReader(poruka);
        Properties bodyJSON = gson.fromJson(sr, Properties.class);
        sr.close();
        Mqttporuka p = new Mqttporuka();
        sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss.SSS");
        
        p.setKorisnik(bodyJSON.getProperty("korisnik"));
        p.setAerodrom(bodyJSON.getProperty("aerodrom"));
        try {
            p.setVrijeme(sdf.parse(bodyJSON.getProperty("vrijeme")));
        } catch (ParseException ex) {
            Logger.getLogger(MQTTListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setPoruka(bodyJSON.getProperty("poruka"));
        try {
            mqfl.create(p);
        } catch (ConstraintViolationException e) {
            Logger l = Logger.getLogger(MqttObrada.class.getName());
            e.getConstraintViolations().forEach(err -> l.log(Level.SEVERE, err.toString()));
        }
        
        List<Aerodrom> aero = mafl.dajAerodromeKorisnika(korisnik);
        Boolean prati = false;
        if (imePrezime.equals(p.getKorisnik())) {
            for (Aerodrom a : aero) {
                if (a.getIcao().equals(p.getAerodrom())) {
                    prati = true;
                }
            }
        }
        
        if (prati) {
            brojPoruka++;
        }
        
        if (brojPoruka == velicinaSkupine) {
            System.out.println("skupio dosta poruka za jms");
            jms3.salji(korisnik, p.getPoruka(), sdf.format(p.getVrijeme()));
            brojPoruka = 0;
        }
        
        super.run(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
