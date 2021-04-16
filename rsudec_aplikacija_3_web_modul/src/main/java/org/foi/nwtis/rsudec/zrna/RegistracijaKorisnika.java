/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import lombok.Getter;
import lombok.Setter;
import org.foi.nwtis.podaci.Korisnik;
import org.foi.nwtis.rsudec.ejb.eb.Korisnici;
import org.foi.nwtis.rsudec.ejb.sb.KorisniciFacadeLocal;
import org.foi.nwtis.rsudec.socket.SocketClient;

/**
 *
 * @author Robi
 */
@Named(value = "registracijaKorisnika")
@SessionScoped
public class RegistracijaKorisnika implements Serializable {

    @EJB
    KorisniciFacadeLocal kfl;
    @Setter
    @Getter
    String korisnik;
    @Setter
    @Getter
    String ime;
    @Setter
    @Getter
    String prezime;
    @Setter
    @Getter
    String email;
    @Setter
    @Getter
    String lozinka;

    public void provjeraKorisnickoIme(FacesContext facesContext,
            UIComponent arg1, Object value) throws ValidatorException {
        String upisani = (String) value;

        String tekstPoruke = "Unesi korisničko ime - min 3 znaka";
        if (upisani.length() < 3) {
            FacesMessage poruka = new FacesMessage();
            poruka.setSeverity(FacesMessage.SEVERITY_ERROR);
            poruka.setDetail(tekstPoruke);
            poruka.setSummary(tekstPoruke);
            facesContext.addMessage("korime", poruka);
            throw new ValidatorException(poruka);
        }
    }

    public void provjeraIme(FacesContext facesContext,
            UIComponent arg1, Object value) throws ValidatorException {
        String upisani = (String) value;

        String tekstPoruke = "Unesi ime - min 2 znaka";
        if (upisani.length() < 2) {
            FacesMessage poruka = new FacesMessage();
            poruka.setSeverity(FacesMessage.SEVERITY_ERROR);
            poruka.setDetail(tekstPoruke);
            poruka.setSummary(tekstPoruke);
            facesContext.addMessage("ime", poruka);
            throw new ValidatorException(poruka);
        }
    }

    public void provjeraPrezime(FacesContext facesContext,
            UIComponent arg1, Object value) throws ValidatorException {
        String upisani = (String) value;

        String tekstPoruke = "Unesi prezime - min 2 znaka ";
        if (upisani.length() < 2) {
            FacesMessage poruka = new FacesMessage();
            poruka.setSeverity(FacesMessage.SEVERITY_ERROR);
            poruka.setDetail(tekstPoruke);
            poruka.setSummary(tekstPoruke);
            facesContext.addMessage("prezime", poruka);
            throw new ValidatorException(poruka);
        }
    }

    public void provjeraEmail(FacesContext facesContext,
            UIComponent arg1, Object value) throws ValidatorException {
        String upisani = (String) value;

        String tekstPoruke = "Unesi email - min 6 znakova i obavezan znak '@'.";
        if (upisani.length() < 6 || !upisani.contains("@")) {
            FacesMessage poruka = new FacesMessage();
            poruka.setSeverity(FacesMessage.SEVERITY_ERROR);
            poruka.setDetail(tekstPoruke);
            poruka.setSummary(tekstPoruke);
            facesContext.addMessage("email", poruka);
            throw new ValidatorException(poruka);
        }
    }

    public void provjeraLozinka(FacesContext facesContext,
            UIComponent arg1, Object value) throws ValidatorException {
        String upisani = (String) value;

        String tekstPoruke = "Unesi lozinku - min 3 znaka.";
        if (upisani.length() < 3) {
            FacesMessage poruka = new FacesMessage();
            poruka.setSeverity(FacesMessage.SEVERITY_ERROR);
            poruka.setDetail(tekstPoruke);
            poruka.setSummary(tekstPoruke);
            facesContext.addMessage("lozinka", poruka);
            throw new ValidatorException(poruka);
        }
    }

    public String registriraj() {

        Korisnici k = new Korisnici(korisnik, ime, prezime, lozinka, email, new Date(), new Date());
        if (kfl.find(korisnik) == null) {
            kfl.create(k);
            SocketClient.saljiKomandu(korisnik, lozinka, "DODAJ;");
            return "prijavaKorisnika.xhtml";
        }
        return "";
    }

}
