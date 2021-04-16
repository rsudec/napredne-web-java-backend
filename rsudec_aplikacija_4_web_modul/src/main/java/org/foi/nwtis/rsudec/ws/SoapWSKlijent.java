/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ws;

import java.util.List;

/**
 *
 * @author Robi
 */
public class SoapWSKlijent {

    public List<org.foi.nwtis.rsudec.ws.Aerodrom> dohvatiAerodromeKorisnika(String korisnik, String lozinka) {

        List<Aerodrom> res = null;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.SoapWS_Service service = new org.foi.nwtis.rsudec.ws.SoapWS_Service();
            org.foi.nwtis.rsudec.ws.SoapWS port = service.getSoapWSPort();
            // TODO process result here
            res = port.dajAerodromeKorisnika(korisnik, lozinka);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return res;

    }

    public List<AvionLeti> dohvatiAvioneAerodroma(String korisnik, String lozinka, String icao, long vrijemeOd, long vrijemeDo) {

        List<AvionLeti> res = null;

        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.SoapWS_Service service = new org.foi.nwtis.rsudec.ws.SoapWS_Service();
            org.foi.nwtis.rsudec.ws.SoapWS port = service.getSoapWSPort();

            res = port.dajAvioneSAerodroma(korisnik, lozinka, icao, vrijemeOd, vrijemeDo);

        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return res;
    }
    
    public List<Aerodrom> dohvatiAvioneUBlizini(String korisnik, String lozinka, String icao, String min, String max){
        List<Aerodrom> res = null;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.SoapWS_Service service = new org.foi.nwtis.rsudec.ws.SoapWS_Service();
            org.foi.nwtis.rsudec.ws.SoapWS port = service.getSoapWSPort();

            // TODO process result here
            res = port.dajAerodromeUKrugu(korisnik, lozinka, icao, min ,max);

        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return res;
    }
    
    public double dohvatiUdaljenostIzmedu2(String korisnik, String lozinka, String icao1, String icao2){
        double res = -1;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.SoapWS_Service service = new org.foi.nwtis.rsudec.ws.SoapWS_Service();
            org.foi.nwtis.rsudec.ws.SoapWS port = service.getSoapWSPort();
            // TODO initialize WS operation arguments here

            res = port.dajUdaljenostAerodroma(korisnik, lozinka, icao1, icao2);
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return res;
    
    }
    public List<Aerodrom> dajSveAerodrome(String korisnik, String lozinka, String naziv){
        List<Aerodrom> res = null;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.SoapWS_Service service = new org.foi.nwtis.rsudec.ws.SoapWS_Service();
            org.foi.nwtis.rsudec.ws.SoapWS port = service.getSoapWSPort();
            // TODO initialize WS operation arguments here
            // TODO process result here
            res = port.dajAerodromeNaziv(korisnik, lozinka, naziv);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return res;
    }
}
