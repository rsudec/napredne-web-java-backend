/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.mdb;

import com.google.gson.Gson;
import java.io.StringReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.foi.nwtis.rsudec.podaci.PorukaMQTT;
import org.foi.nwtis.rsudec.podaci.PorukaWebSocket;
import org.foi.nwtis.rsudec.sb.PorukeSingleton;

/**
 *
 * @author Robi
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/NWTiS_rsudec_3"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MQTTPoruke implements MessageListener {
     @Inject
    PorukeSingleton singleton;
    public MQTTPoruke() {
    }
    
    @Override
    public void onMessage(Message message) {
        System.out.println("Stigla poruka iz NWTiS_rsudec_3 -> MQTTPoruka");
        try {
            System.out.println(message.getBody(String.class));
        } catch (JMSException ex) {
            Logger.getLogger(SocketPoruke.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String poruka = message.getBody(String.class);
            Gson gson = new Gson();
            StringReader sr = new StringReader(poruka);
            Properties bodyJSON = gson.fromJson(sr, Properties.class);
            sr.close();
            
            PorukaMQTT ps = new PorukaMQTT(bodyJSON.getProperty("id"), bodyJSON.getProperty("korisnik"), bodyJSON.getProperty("poruka"), bodyJSON.getProperty("vrijeme"));
            singleton.dodajMQTTPoruku(ps);
        } catch (JMSException ex) {
            Logger.getLogger(SocketPoruke.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
