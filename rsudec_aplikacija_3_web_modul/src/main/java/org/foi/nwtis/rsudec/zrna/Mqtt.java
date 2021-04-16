/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.zrna;


import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import lombok.Getter;
import lombok.Setter;
import org.foi.nwtis.rsudec.ejb.eb.Log;
import org.foi.nwtis.rsudec.ejb.eb.Mqttporuka;
import org.foi.nwtis.rsudec.ejb.sb.LogFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.MqttporukaFacadeLocal;
import org.foi.nwtis.rsudec.konfiguracije.bp.BP_Konfiguracija;

/**
 *
 * @author Robi
 */
@Named(value = "mqtt")
@RequestScoped
public class Mqtt implements Serializable {
    
    @Inject
    PrijavaKorisnika pk;
    @EJB
    MqttporukaFacadeLocal mqfl;
    @Getter
    @Setter
    List<Mqttporuka> mqtt;
    @Getter
    String pages;
    @Inject
    ServletContext context;
    
    @PostConstruct
    void init() {
        mqtt = mqfl.dajMqttKorisnika(pk.getImePrezime());
        BP_Konfiguracija bpk = (BP_Konfiguracija) context.getAttribute("BP_Konfig");
        pages = bpk.getKonfig().dajPostavku("mqtt.stranicenje");
        
    }
    public void obrisiPoruke(){
        int res = mqfl.obrisiPorukeKorisnika(pk.getImePrezime());
        System.out.println("Obrisano " + res + " MQTT poruka");
        mqtt = mqfl.dajMqttKorisnika(pk.getImePrezime());
    }
//    public List<Mqttporuka> dajZapiseMQTT(){
//        System.out.println("MQTT imeprezim e" + pk.imePrezime);
//        return mqfl.dajMqttKorisnika(pk.imePrezime);
//    }
}
