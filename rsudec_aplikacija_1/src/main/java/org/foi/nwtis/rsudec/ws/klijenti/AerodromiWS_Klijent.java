/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package org.foi.nwtis.rsudec.ws.klijenti;

import java.util.List;
import org.foi.nwtis.rsudec.ws.StatusKorisnika;

/**
 *
 * @author Robi
 */
public class AerodromiWS_Klijent {
    
    
    public Boolean prijaviGrupu(String username, String password){
    

        Boolean result = null;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.AerodromiWS_Service service = new org.foi.nwtis.rsudec.ws.AerodromiWS_Service();
            org.foi.nwtis.rsudec.ws.AerodromiWS port = service.getAerodromiWSPort();
            // TODO initialize WS operation arguments here
            java.lang.String korisnickoIme = username;
            java.lang.String korisnickaLozinka = password;
            // TODO process result here
            result = port.registrirajGrupu(korisnickoIme, korisnickaLozinka);
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        return result;

    }
    public Boolean odjaviGrupu(String username, String password){
    
        Boolean result = null;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.AerodromiWS_Service service = new org.foi.nwtis.rsudec.ws.AerodromiWS_Service();
            org.foi.nwtis.rsudec.ws.AerodromiWS port = service.getAerodromiWSPort();
            // TODO initialize WS operation arguments here
            java.lang.String korisnickoIme = username;
            java.lang.String korisnickaLozinka = password;
            // TODO process result here
            result = port.deregistrirajGrupu(korisnickoIme, korisnickaLozinka);
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        return result;

    }
    
    public StatusKorisnika stanjeGrupe(String username, String password){
    
        StatusKorisnika res = null;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.AerodromiWS_Service service = new org.foi.nwtis.rsudec.ws.AerodromiWS_Service();
            org.foi.nwtis.rsudec.ws.AerodromiWS port = service.getAerodromiWSPort();
            // TODO initialize WS operation arguments here
            java.lang.String korisnickoIme = username;
            java.lang.String korisnickaLozinka = password;
            // TODO process result here
            res = port.dajStatusGrupe(korisnickoIme, korisnickaLozinka);
            System.out.println("Result = "+res);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return res;
    }
    
    public Boolean aktivirajGrupu(String username, String password){
    
        Boolean res = null;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.AerodromiWS_Service service = new org.foi.nwtis.rsudec.ws.AerodromiWS_Service();
            org.foi.nwtis.rsudec.ws.AerodromiWS port = service.getAerodromiWSPort();
            // TODO initialize WS operation arguments here
            java.lang.String korisnickoIme = username;
            java.lang.String korisnickaLozinka = password;
            // TODO process result here
            res = port.aktivirajGrupu(korisnickoIme, korisnickaLozinka);
            System.out.println("Result = "+res);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());// TODO handle custom exceptions here
        }
        return res;

    }
    public Boolean blokirajGrupu(String username, String password){
    
        Boolean res = null;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.AerodromiWS_Service service = new org.foi.nwtis.rsudec.ws.AerodromiWS_Service();
            org.foi.nwtis.rsudec.ws.AerodromiWS port = service.getAerodromiWSPort();
            // TODO initialize WS operation arguments here
            java.lang.String korisnickoIme = username;
            java.lang.String korisnickaLozinka = password;
            // TODO process result here
            res = port.blokirajGrupu(korisnickoIme, korisnickaLozinka);
            System.out.println("Result = "+res);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());// TODO handle custom exceptions here
        }
        return res;

    }
    
    public Boolean brisiAerodrome(String username, String password){
        Boolean res = null;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.AerodromiWS_Service service = new org.foi.nwtis.rsudec.ws.AerodromiWS_Service();
            org.foi.nwtis.rsudec.ws.AerodromiWS port = service.getAerodromiWSPort();
            java.lang.String korisnickoIme = username;
            java.lang.String korisnickaLozinka = password;
            // TODO process result here
            res = port.obrisiSveAerodromeGrupe(korisnickoIme, korisnickaLozinka);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return res;
    }
    
    public Boolean dodajAerodromGrupi(String username, String password, String icaoNovi){
        
        Boolean res = null;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.AerodromiWS_Service service = new org.foi.nwtis.rsudec.ws.AerodromiWS_Service();
            org.foi.nwtis.rsudec.ws.AerodromiWS port = service.getAerodromiWSPort();
            // TODO initialize WS operation arguments here
            java.lang.String korisnickoIme = username;
            java.lang.String korisnickaLozinka = password;
            java.lang.String icao = icaoNovi;
            // TODO process result here
            res = port.dodajAerodromIcaoGrupi(korisnickoIme, korisnickaLozinka, icao);
        } catch (Exception ex) {
            System.out.println("GREŠKA DODAJ AERODROM GRUPI");
            System.out.println(ex.getMessage());
        }
        return res;

    }
    public List<org.foi.nwtis.rsudec.ws.Aerodrom> dajAerodromeGrupe(String username, String password){
        
        List<org.foi.nwtis.rsudec.ws.Aerodrom> result = null;
        try { // Call Web Service Operation
            org.foi.nwtis.rsudec.ws.AerodromiWS_Service service = new org.foi.nwtis.rsudec.ws.AerodromiWS_Service();
            org.foi.nwtis.rsudec.ws.AerodromiWS port = service.getAerodromiWSPort();
            // TODO initialize WS operation arguments here
            java.lang.String korisnickoIme = "";
            java.lang.String korisnickaLozinka = "";
            // TODO process result here
            result = port.dajSveAerodromeGrupe(korisnickoIme, korisnickaLozinka);
        } catch (Exception ex) {
            System.out.println("GREŠKA DAJAERODROME GRUPE");
            System.out.println(ex.getMessage());
        }
        return result;
    }

}
