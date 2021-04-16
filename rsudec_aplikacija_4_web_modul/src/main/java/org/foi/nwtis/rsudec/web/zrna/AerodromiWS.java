/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.web.zrna;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.foi.nwtis.rsudec.ws.Aerodrom;
import org.foi.nwtis.rsudec.ws.AvionLeti;
import org.foi.nwtis.rsudec.ws.SoapWSKlijent;

/**
 *
 * @author Robi
 */
@Named(value = "aerodromiWs")
@RequestScoped
public class AerodromiWS {

    @Getter
    @Setter
    String odDatuma;
    @Getter
    @Setter
    String doDatuma;
    @Getter
    List<Aerodrom> aerodromi;
    @Getter
    List<AvionLeti> avioni;
    @Inject
    PrijavaKorisnika pk;

    public AerodromiWS() {
    }

    @PostConstruct
    public void dohvatiAerodromeKorisnika() {

        SoapWSKlijent wsklijent = new SoapWSKlijent();
        aerodromi = wsklijent.dohvatiAerodromeKorisnika(pk.getKorisnik(), pk.getLozinka());
    }

    public void prikaziAvione(String icao) {
        try {
            System.out.println(odDatuma);
            System.out.println(doDatuma);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
            long odD = sdf.parse(odDatuma).getTime() / 1000;
            long doD = sdf.parse(doDatuma).getTime() / 1000;
            SoapWSKlijent soapKlijent = new SoapWSKlijent();
            avioni = soapKlijent.dohvatiAvioneAerodroma(pk.getKorisnik(), pk.getLozinka(), icao, odD, doD);
            System.out.println(avioni.size());
        } catch (ParseException ex) {
            Logger.getLogger(AerodromiWS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String dajDatum(String time) {
        System.out.println(time);
        long ms = Long.parseLong(time) * 1000;
        Date datumObj = new Date(ms);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        System.out.println(sdf.format(datumObj));
        return sdf.format(datumObj);

    }

}
