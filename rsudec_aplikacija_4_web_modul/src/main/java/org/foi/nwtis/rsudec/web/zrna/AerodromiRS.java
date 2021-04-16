/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.web.zrna;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import static javafx.scene.input.KeyCode.T;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import lombok.Getter;
import lombok.Setter;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.podaci.Odgovor;
import org.foi.nwtis.podaci.OdgovorAerodrom;
import org.foi.nwtis.rsudec.ejb.sb.MyairportsFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.MyairportslogFacadeLocal;
import org.foi.nwtis.rsudec.rs.RSKlijent;
import org.foi.nwtis.rsudec.ws.SoapWSKlijent;

/**
 *
 * @author Robi
 */
@Named(value = "aerodromiRS")
@RequestScoped
public class AerodromiRS {

    @Getter
    List<Aerodrom> aerodromiKorisnika = new ArrayList<>();
    @Getter
    List<org.foi.nwtis.rsudec.ws.Aerodrom> sviAerodromi = new ArrayList<>();
     @Getter
    List<org.foi.nwtis.rsudec.ws.Aerodrom> aerodromiRezultat = new ArrayList<>();
    @Inject
    Lokalizacija lokalizacija;
    @Getter
    @Setter
    String odabraniAerodrom;
    @Inject
    PrijavaKorisnika pk;
    @Getter
    String status;
    @Getter
    @Setter
    String minGranica;
    @Getter 
    @Setter
    String maxGranica;
    
    @Getter
    @Setter
    String udaljenostAerodromPrvi;
     @Getter
    @Setter
    String udaljenostAerodromDrugi;
     @Getter
     double rezultatUdaljenost;
    @PostConstruct
    void dohvatiAerodromeKorisnika() {
        if (pk.getKorisnik() == null) {
            return;
        }
        RSKlijent rs = new RSKlijent(pk.getKorisnik(), pk.getLozinka());
        SoapWSKlijent klijent = new SoapWSKlijent();
        
        OdgovorAerodrom korisnikovi = rs.dajAerodomeKorisnika(OdgovorAerodrom.class);
        
        sviAerodromi = klijent.dajSveAerodrome(pk.getKorisnik(), pk.getLozinka(), "");
        aerodromiKorisnika = Arrays.asList(korisnikovi.getOdgovor());
    }
    public AerodromiRS() {
    }
    public void obrisiOdabraniAerodrom(){
        
        System.out.println(odabraniAerodrom);
        RSKlijent rs = new RSKlijent(pk.getKorisnik(), pk.getLozinka());
        Response r = rs.obrisiAerodromKorisnika(odabraniAerodrom);
        dohvatiAerodromeKorisnika();
    }
    public void obrisiLetoveAerodroma(){
        System.out.println(pk.getKorisnik());
        RSKlijent rs = new RSKlijent(pk.getKorisnik(), pk.getLozinka());
        
        Response r = rs.obrisiLetoveAerodromaKorisnika(odabraniAerodrom);
        Odgovor odg = r.readEntity(Odgovor.class);
        
        ResourceBundle res = ResourceBundle.getBundle(
                "org.foi.nwtis.rsudec.web.Prijevod",
                new Locale(lokalizacija.getJezik()));
        if (odg.getStatus().equals("40")) {
            status = res.getString("pogled5.brisanjeLetovaGreška");
        }
        else {
            status = res.getString("pogled5.brisanjeLetovaUspjeh");
        }
    }
    public void  dajAerodromeUnutarGranica(){
        SoapWSKlijent klijent = new SoapWSKlijent();
        aerodromiRezultat = klijent.dohvatiAvioneUBlizini(pk.getKorisnik(), pk.getLozinka(), odabraniAerodrom, minGranica, maxGranica);
    }
    public void dajUdaljenostIzmedu2(){
        SoapWSKlijent klijent = new SoapWSKlijent();
        rezultatUdaljenost = klijent.dohvatiUdaljenostIzmedu2(pk.getKorisnik(), pk.getLozinka(), udaljenostAerodromPrvi, udaljenostAerodromDrugi);
    }
}
