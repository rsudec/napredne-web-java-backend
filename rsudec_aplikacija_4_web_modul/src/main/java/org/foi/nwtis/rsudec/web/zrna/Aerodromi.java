/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.web.zrna;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import lombok.Getter;
import lombok.Setter;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rest.klijenti.LIQKlijent;
import org.foi.nwtis.rest.klijenti.OWMKlijent;
import org.foi.nwtis.rest.podaci.Lokacija;
import org.foi.nwtis.rest.podaci.MeteoPodaci;
import org.foi.nwtis.rsudec.ejb.eb.Airports;
import org.foi.nwtis.rsudec.ejb.eb.Korisnici;
import org.foi.nwtis.rsudec.ejb.eb.Myairports;
import org.foi.nwtis.rsudec.ejb.sb.AirplanesFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.AirportsFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.MyairportsFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.MyairportslogFacadeLocal;
import org.foi.nwtis.rsudec.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.rsudec.websocket.WebSocketClient;

/**
 *
 * @author Robi
 */
@Named(value = "aerodromi")
@RequestScoped
public class Aerodromi {

    @Getter
    List<Aerodrom> aerodromiKorisnika = new ArrayList<>();
    
    @Getter
    List<Aerodrom> aerodromiPretraga = new ArrayList<>();
    @Inject
    PrijavaKorisnika pk;
    @Getter
    String geoLIQLongitude;
    @Getter
    String geoLIQLatitude;
    @Getter
    String meteoTemp;
    @Getter
    String meteoTempUnit;
    @Getter
    String meteoVlaga;
    @Getter
    String meteoVlagaUnit;
    
    @Getter
    @Setter
    private String odabraniAerodrom = "";
    @Getter
    @Setter
    private String naziv= "";
    
    @EJB
    private MyairportsFacadeLocal mafl;
    @EJB
    private MyairportslogFacadeLocal malfl;
    @EJB
    private AirplanesFacadeLocal afl;
    @EJB
    private AirportsFacadeLocal airpfl;
    
    @Inject
    ServletContext context;
    @Inject
    Lokalizacija lokalizacija;
    public Aerodromi() {
    }

    @PostConstruct
    private void dohvatiAerodromeKorisnika() {
        if (pk.getKorisnik() == null) {
            return;
        }
        aerodromiKorisnika = mafl.dajAerodromeKorisnika(pk.getKorisnik());
        aerodromiPretraga = airpfl.dajPoNazivu("%");
        odabraniAerodrom = aerodromiPretraga.get(0).getIcao();
    }

    public int brojPratioca(String icao) {
        return mafl.brojPratiocaAerodroma(icao);
    }
    public int brojDanaSPodacima(String icao){
        return malfl.brojDanaSPodacima(icao);
        }
    public int brojPreuzetihLetova(String icao){
        return afl.brojLetovaSAerodroma(icao);
    }
    
    public void preuzmiGeoMeteoPodatke(String icao){
        
        Aerodrom a = null;
        for(Aerodrom air : aerodromiKorisnika){
            if(air.getIcao().equals(icao))
                a = air; 
        }
        BP_Konfiguracija bpk = (BP_Konfiguracija) context.getAttribute("BP_Konfig");
//        LIQKlijent klijent = new LIQKlijent(bpk.getKonfig().dajPostavku("LocationIQ.token"));
//        Lokacija lok = klijent.getGeoLocation(icao);
//        if (lok == null) {
//            System.out.println("nije odhavitio LIQ");
//            return;
//        }
        if(a == null){
             System.out.println("nije odhavitio aerodrom");
            return;}
        geoLIQLatitude = a.getLokacija().getLatitude();
        geoLIQLongitude =a.getLokacija().getLongitude();
        
        OWMKlijent oWMklijent = new OWMKlijent(bpk.getKonfig().dajPostavku("OpenWeatherMap.apikey"));
        MeteoPodaci podaci = oWMklijent.getRealTimeWeather(geoLIQLatitude, geoLIQLongitude);
        if (podaci == null) {
            System.out.println("nije odhavitio OWM");
            return;
        }
        meteoTemp = podaci.getTemperatureValue().toString();
        meteoTempUnit = podaci.getTemperatureUnit();
        meteoVlaga = podaci.getHumidityValue().toString();
        meteoVlagaUnit = podaci.getHumidityUnit();
        
        System.out.println(geoLIQLatitude + ", " + geoLIQLongitude);
        System.out.println(meteoTemp + ", " + meteoVlaga);
    }
    
    public void dajAerodromeNaziv(){
        aerodromiPretraga =  airpfl.dajPoNazivu(naziv);
    }
    public void osvjeziAerodromeKorisnika(){
        System.out.println(odabraniAerodrom);
        WebSocketClient wsc = new WebSocketClient();
        wsc.sendMessage(pk.getKorisnik() + "," + odabraniAerodrom);
        aerodromiKorisnika = mafl.dajAerodromeKorisnika(pk.getKorisnik());
    }
    public String dajKorisnika(){
        return pk.getKorisnik();
    }
    
}
