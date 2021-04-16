/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ws.klijent;

import java.util.List;

/**
 *
 * @author Robi
 */
public class SoapWSKlijent {

    
    public Boolean provjeraKorisnika(String korisnik ,String lozinka){
    
        System.out.println("KLIJENT WS KORISNIK " + korisnik);
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.klijent.SoapWS_Service service = new org.foi.nwtis.rsudec.ws.klijent.SoapWS_Service();
            org.foi.nwtis.rsudec.ws.klijent.SoapWS port = service.getSoapWSPort();
            java.lang.Boolean result = port.provjeraKorisnika(korisnik, lozinka);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return false;

    }
    public List<Aerodrom> dajAerodromeKorisnika(String korisnik, String lozinka) {
        List<Aerodrom> aerodromi = null;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.klijent.SoapWS_Service service = new org.foi.nwtis.rsudec.ws.klijent.SoapWS_Service();
            org.foi.nwtis.rsudec.ws.klijent.SoapWS port = service.getSoapWSPort();

            aerodromi = port.dajAerodromeKorisnika(korisnik, lozinka);
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return aerodromi;
    }
    
    public List<Aerodrom> dajAerodromeNaziv(String korisnik, String lozinka, String naziv){
        List<Aerodrom> aerodromi = null;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.klijent.SoapWS_Service service = new org.foi.nwtis.rsudec.ws.klijent.SoapWS_Service();
            org.foi.nwtis.rsudec.ws.klijent.SoapWS port = service.getSoapWSPort();

            aerodromi = port.dajAerodromeNaziv(korisnik, lozinka, naziv);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return aerodromi;
    
    }
    public List<Aerodrom> dajAerodromeDrzava(String korisnik, String lozinka, String drzava){
        List<Aerodrom> aerodromi = null;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.klijent.SoapWS_Service service = new org.foi.nwtis.rsudec.ws.klijent.SoapWS_Service();
            org.foi.nwtis.rsudec.ws.klijent.SoapWS port = service.getSoapWSPort();

            aerodromi = port.dajAerodromeDrzava(korisnik, lozinka, drzava);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return aerodromi;
    
    }
    
//    public Aerodrom dajAerodromKorisnika(String korisnik, String lozinka, String icao){
//       Aerodrom res = null;
//       
//       
//       
//       return res;
//    
//    }
}
