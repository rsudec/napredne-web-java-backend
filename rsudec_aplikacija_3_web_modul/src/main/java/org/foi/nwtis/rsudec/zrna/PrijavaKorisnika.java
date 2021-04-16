/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import lombok.Getter;
import lombok.Setter;
import org.foi.nwtis.rsudec.ejb.eb.Korisnici;
import org.foi.nwtis.rsudec.ejb.sb.KorisniciFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.MqttporukaFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.MyairportsFacadeLocal;
import org.foi.nwtis.rsudec.jms.JMS_Red_3;
import org.foi.nwtis.rsudec.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.rsudec.slusaci.MQTTListener;


/**
 *
 * @author Robi
 */
@Named(value = "prijavaKorisnika")
@SessionScoped
public class PrijavaKorisnika implements Serializable {

    @Inject
    JMS_Red_3 jms3;
    
    @EJB
    KorisniciFacadeLocal kfl;
     @EJB
    MqttporukaFacadeLocal mqfl;
    @EJB
    MyairportsFacadeLocal mafl;
    @Inject
    ServletContext context;
    MQTTListener mqttL;
    @Getter
    @Setter
    private String korisnik = "";
    @Getter
    @Setter
    private String lozinka = "";
    
    @Getter
    String imePrezime;

    public void provjeraUsername(FacesContext facesContext,
            UIComponent arg1, Object value) throws ValidatorException {
        String upisani = (String) value;

        String tekstPoruke = "Greška korisničko ime - min 3 znaka";
        if (upisani.length() < 3) {
            FacesMessage poruka = new FacesMessage();
            poruka.setSeverity(FacesMessage.SEVERITY_ERROR);
            poruka.setDetail(tekstPoruke);
            poruka.setSummary(tekstPoruke);
            facesContext.addMessage("korime", poruka);
            throw new ValidatorException(poruka);
        }
    }

    public void provjeraPassword(FacesContext facesContext,
            UIComponent arg1, Object value) throws ValidatorException {
        String upisani = (String) value;

        String tekstPoruke = "Upiši lozinku - min 3 znaka";
        if (upisani.length() < 3) {
            System.out.println("greška lozinka");
            FacesMessage poruka = new FacesMessage();
            poruka.setSeverity(FacesMessage.SEVERITY_ERROR);
            poruka.setDetail(tekstPoruke);
            poruka.setSummary(tekstPoruke);
            facesContext.addMessage("lozinka", poruka);
            throw new ValidatorException(poruka);
        }
    }

    public String autenticiraj() {
        System.out.println(korisnik);
        System.out.println(lozinka);

        Korisnici k = kfl.find(korisnik);
        if (k == null || !k.getLozinka().equals(lozinka)) {
            korisnik = "";
            
            lozinka = "";
            imePrezime = "";
            return "";
        }
        imePrezime =  k.getIme() + " " + k.getPrezime();
        System.out.println("PK ime prezime ->" + imePrezime);
        BP_Konfiguracija bpk = (BP_Konfiguracija) context.getAttribute("BP_Konfig");
        int brojPoruka = Integer.parseInt(bpk.getKonfig().dajPostavku("mqtt.velicinaSkupine"));
        mqttL = new MQTTListener(mqfl, mafl, imePrezime, korisnik, jms3, brojPoruka);
        mqttL.start();
        
        return "index.xhtml";

    }

    public Boolean korisnikJePrijavljen() {
        if (!korisnik.isEmpty()) {
            return true;
        }
        return false;
    }
    
    public Object odjava(){
        korisnik = "";
        lozinka = "";
        imePrezime = "";
        if(mqttL != null)
            mqttL.interrupt();
        return "index.xhtml?faces-redirect=true";
    }
    
    @PreDestroy
    public void destroy(){
        if(mqttL != null)
            mqttL.interrupt();
    }
    
    

}
