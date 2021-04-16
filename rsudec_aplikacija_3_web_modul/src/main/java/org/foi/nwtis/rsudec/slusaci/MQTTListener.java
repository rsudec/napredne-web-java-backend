/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.slusaci;

import com.google.gson.Gson;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.foi.nwtis.rsudec.ejb.eb.Mqttporuka;
import org.foi.nwtis.rsudec.ejb.sb.MqttporukaFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.MyairportsFacadeLocal;
import org.foi.nwtis.rsudec.jms.JMS_Red_3;
import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

/**
 *
 * @author Robi
 */
public class MQTTListener extends Thread {
    MqttporukaFacadeLocal mqttfl;
    MyairportsFacadeLocal mafl;
    JMS_Red_3 jms3;
    String korisnik;
    String imePrezime;
    int velicinaSkupine;
    public MQTTListener(MqttporukaFacadeLocal mqttfl, MyairportsFacadeLocal mafl, String imePrezime, String korisnik, JMS_Red_3 jms3, int velicinaSkupine){
    this.mqttfl = mqttfl;
    this.mafl = mafl;
    this.velicinaSkupine = velicinaSkupine;
    this.imePrezime = imePrezime;
    this.korisnik = korisnik;
    this.jms3 = jms3;
    }
    

    Boolean kraj = false;
    String user = "rsudec";
    String password = "KelEqb";
    String host = "nwtis.foi.hr";
    int port = 61613;
    final String destination = "/NWTiS/rsudec";
    static int msgCount = 0;
     CallbackConnection connection;

    @Override
    public synchronized void start() {
        try {
            System.out.println("palim MQTT");
            MQTT mqtt = new MQTT();
            mqtt.setHost(host, port);
            mqtt.setUserName(user);
            mqtt.setPassword(password);

            connection = mqtt.callbackConnection();
            connection.listener(new org.fusesource.mqtt.client.Listener() {
                long count = 0;

                @Override
                public void onConnected() {
                    System.out.println("Otvorena veza na MQTT");
                }

                @Override
                public void onDisconnected() {
                    System.out.println("Prekinuta veza na MQTT");
                    interrupt();
                }

                @Override
                public void onFailure(Throwable value) {
                    System.out.println(value.getMessage());
                    System.out.println("Problem u vezi na MQTT");
                    interrupt();
                }

                @Override
                public void onPublish(UTF8Buffer topic, Buffer msg, Runnable ack) {
                    try {
                        String body = msg.utf8().toString();
                        MqttObrada mo = new MqttObrada(body, mqttfl, mafl, imePrezime, korisnik, jms3, velicinaSkupine);
                        mo.start();
                        mo.join();
                        //System.out.println("Stigla poruka br: " + count);
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MQTTListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            connection.connect(new Callback<Void>() {
                @Override
                public void onSuccess(Void t) {
                    Topic[] topics = {new Topic(destination, QoS.AT_LEAST_ONCE)};
                    connection.subscribe(topics, new Callback<byte[]>() {
                        @Override
                        public void onSuccess(byte[] qoses) {
                            System.out.println("Pretplata na: " + destination);
                        }

                        @Override
                        public void onFailure(Throwable value) {
                            System.out.println("Problem kod pretplate na: " + destination);
                            interrupt();
                        }
                    });
                }

                @Override
                public void onFailure(Throwable thrwbl) {
                    System.out.println("Neuspjela pretplata na: " + destination);
                    interrupt();
                }
            });
            
            super.run(); //To change body of generated methods, choose Tools | Templates.
        } catch (URISyntaxException ex) {
            interrupt();
            Logger.getLogger(MQTTListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void run() {
        while (!kraj) {
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                interrupt();
                Logger.getLogger(MQTTListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        try {
//            MQTTListener.class.wait();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(MQTTListener.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    @Override
    public void interrupt() {
        kraj = true;
        connection.disconnect(null);
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

}
