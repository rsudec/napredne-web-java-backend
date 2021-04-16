/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.web.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.foi.nwtis.rsudec.ejb.eb.Korisnici;
import org.foi.nwtis.rsudec.ejb.sb.KorisniciFacadeLocal;

/**
 *
 * @author Robi
 */
@Named(value = "prijavaKorisnika")
@SessionScoped
public class PrijavaKorisnika implements Serializable {

    @Getter
    @Setter
    private String korisnik = "";
    @Getter
    @Setter
    private String lozinka = "";
    @EJB
    KorisniciFacadeLocal kfl;
     @Inject
    transient Lokalizacija lokalizacija;

    public PrijavaKorisnika() {
    }
    public void provjeraUsername(FacesContext facesContext,
            UIComponent arg1, Object value)throws ValidatorException{
        String upisani = (String) value;
        
        ResourceBundle res = ResourceBundle.getBundle(
                "org.foi.nwtis.rsudec.web.Prijevod",
                new Locale(lokalizacija.getJezik()));
        String tekstPoruke = res.getString("pogled1.korimeGreska");
        if (upisani.length() < 1) {
            FacesMessage poruka = new FacesMessage();
            poruka.setSeverity(FacesMessage.SEVERITY_ERROR);
            poruka.setDetail(tekstPoruke);
            poruka.setSummary(tekstPoruke);
            facesContext.addMessage("korime", poruka);
            throw new ValidatorException(poruka);
        }
    }
    public void provjeraPassword(FacesContext facesContext,
            UIComponent arg1, Object value)throws ValidatorException{
        String upisani = (String) value;
        
        ResourceBundle res = ResourceBundle.getBundle(
                "org.foi.nwtis.rsudec.web.Prijevod",
                new Locale(lokalizacija.getJezik()));
        String tekstPoruke = res.getString("pogled1.lozinkaGreska");
        if (upisani.length() < 1) {
            FacesMessage poruka = new FacesMessage();
            poruka.setSeverity(FacesMessage.SEVERITY_ERROR);
            poruka.setDetail(tekstPoruke);
            poruka.setSummary(tekstPoruke);
            facesContext.addMessage("lozinka", poruka);
            throw new ValidatorException(poruka);
        }
    }
    public String autenticiraj(){
        Korisnici k = kfl.find(korisnik);
        if (k == null || !k.getLozinka().equals(lozinka)) {
            korisnik = "";
            lozinka = "";
            return "";
        }
        return "pogled1.xhtml";
        
    }
     public Boolean korisnikJePrijavljen(){
        if(!korisnik.isEmpty())
            return true;
        return false;
    }
     
     public Object odjava(){
        korisnik  = "";
        lozinka = "";
        return "pogled1.xhtml?faces-redirect=true";
    }

}
