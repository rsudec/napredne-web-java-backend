/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.zrna;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import org.foi.nwtis.rsudec.socket.SocketClient;

/**
 *
 * @author Robi
 */
@Named(value = "status")
@RequestScoped
public class Status implements Serializable {

    @Inject
    PrijavaKorisnika pk;

    @Getter
    @Setter
    String stanjeSocket;
    @Getter
    @Setter
    String stanjeGrupa;
    @Getter
    String poruka;
    @Getter
    String porukaGrupa;
    @Getter
    @Setter
    String aerodromiTxt = "";

    public Status() {
    }

    @PostConstruct
    void init() {
        dajStanje();
        dajStanjeGrupe();
    }

    public void dajStanje() {
        String res = SocketClient.saljiKomandu(pk.getKorisnik(), pk.getLozinka(), "STANJE;");
        if (res.equals("OK 11;")) {
            stanjeSocket = "PREUZIMANJE RADI";
        } else if (res.equals("OK 12;")) {
            stanjeSocket = "PREUZIMANJE PAUZIRANO/STOPIRANO";
        } else {
            poruka = "Greška na serveru";
        }
    }

    public void pauziraj() {
        String res = SocketClient.saljiKomandu(pk.getKorisnik(), pk.getLozinka(), "PAUZA;");
        if (res.equals("OK 10;")) {
            stanjeSocket = "PREUZIMANJE PAUZIRANO/STOPIRANO";
        } else if (res.equals("ERR 13;")) {
            poruka = "SS trenutno ne radi, ne vrijedi PAUZA";
        } else {
            poruka = "Greška na serveru";
        }
    }

    public void radi() {
        String res = SocketClient.saljiKomandu(pk.getKorisnik(), pk.getLozinka(), "RADI;");
        if (res.equals("OK 10;")) {
            stanjeSocket = "PREUZIMANJE RADI";
        } else if (res.equals("ERR 14;")) {
            poruka = "SS trenutno radi, ne vrijedi RADI";
        } else {
            poruka = "Greška na serveru";
        }
    }

    public void kraj() {
        String res = SocketClient.saljiKomandu(pk.getKorisnik(), pk.getLozinka(), "KRAJ;");
        if (res.equals("OK 10;")) {
            stanjeSocket = "PREUZIMANJE PAUZIRANO/STOPIRANO";
        } else {
            poruka = "Greška na serveru";
        }

    }

    public void dajStanjeGrupe() {
        String res = SocketClient.saljiKomandu(pk.getKorisnik(), pk.getLozinka(), "GRUPA STANJE;");
        if (res.equals("OK 21;")) {
            stanjeGrupa = "GRUPA AKTIVNA";
        } else if (res.equals("OK 22;")) {
            stanjeGrupa = "GRUPA BLOKIRANA";
        } else if (res.equals("ERR 21;")) {
            stanjeGrupa = "GRUPA NE POSTOJI";
        } else {
            porukaGrupa = "Greška na serveru";
        }
    }

    public void grupaPrijavi() {
        String res = SocketClient.saljiKomandu(pk.getKorisnik(), pk.getLozinka(), "GRUPA PRIJAVI;");
        if (res.equals("OK 20;")) {
            stanjeGrupa = "GRUPA BLOKIRANA";
            porukaGrupa = "Registrirali ste groupu";
        } else if (res.equals("ERR 20;")) {
            porukaGrupa = "Grupa je već registrirana, ne možete PRIJAVI";
        } else {
            porukaGrupa = "Greška na serveru";
        }
    }

    public void grupaOdjavi() {
        String res = SocketClient.saljiKomandu(pk.getKorisnik(), pk.getLozinka(), "GRUPA ODJAVI;");
        if (res.equals("OK 20;")) {
            stanjeGrupa = "GRUPA NE POSTOJI";
            porukaGrupa = "Deregistrirali ste groupu";
        } else if (res.equals("ERR 21;")) {
            porukaGrupa = "Grupa ne postoji, ne možete ODJAVI";
        } else {
            porukaGrupa = "Greška na serveru";
        }
    }

    public void grupaAktiviraj() {
        String res = SocketClient.saljiKomandu(pk.getKorisnik(), pk.getLozinka(), "GRUPA AKTIVIRAJ;");
        if (res.equals("OK 20;")) {
            stanjeGrupa = "GRUPA AKTIVIRANA";
        } else if (res.equals("ERR 22;")) {
            porukaGrupa = "Grupa je aktivna, ne možete AKTIVIRAJ";
        } else if (res.equals("ERR 21;")) {
            porukaGrupa = "Grupa ne postoji, ne možete AKTIVIRAJ";
        } else {
            porukaGrupa = "Greška na serveru";
        }
    }

    public void grupaBlokiraj() {
        String res = SocketClient.saljiKomandu(pk.getKorisnik(), pk.getLozinka(), "GRUPA BLOKIRAJ;");
        if (res.equals("OK 20;")) {
            stanjeGrupa = "GRUPA BLOKIRANA";
        } else if (res.equals("ERR 23;")) {
            porukaGrupa = "Grupa je blokana, ne možete BLOKIRAJ";
        } else if (res.equals("ERR 21;")) {
            porukaGrupa = "Grupa ne postoji, ne možete BLOKIRAJ";
        } else {
            porukaGrupa = "Greška na serveru";
        }

    }

    public void grupaAerodromi() {
        if (aerodromiTxt.isEmpty()) {
            porukaGrupa = "Unesi ICAO aerodroma, odvojene zarezom i razmakom";
            return;
        }
        String komanda = "GRUPA AERODROMI " + aerodromiTxt + ";";

        String res = SocketClient.saljiKomandu(pk.getKorisnik(), pk.getLozinka(), komanda);
        if (res.equals("OK 20;")) {
            porukaGrupa = "Dodani aerodromi.";
        } else if (res.equals("ERR 31;")) {
            porukaGrupa = "Grupa nije blokirana.";
        } else {
            porukaGrupa = "Greška na serveru";
        }

    }

    
   

}
