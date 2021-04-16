/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.sb;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.foi.nwtis.rsudec.ejb.eb.Myairports;
import org.foi.nwtis.rsudec.ejb.sb.AirportsFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.KorisniciFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.LogFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.MyairportsFacadeLocal;
import org.foi.nwtis.rsudec.konfiguracije.bp.BP_Konfiguracija;

/**
 *
 * @author Robi
 */
@ServerEndpoint("/websocket")
@Stateless
public class WebSocketEndpoint {

    @EJB
    AirportsFacadeLocal afl;
    @EJB
    MyairportsFacadeLocal mafl;
    @EJB
    KorisniciFacadeLocal kfl;
    @EJB
    LogFacadeLocal lfl;
    @Inject
    ServletContext sc;
    @Inject
    JMS_Red_1 jms1;

    @Resource
    TimerService tservice;

    static Queue<Session> queue = new ConcurrentLinkedQueue<>();
    static Boolean timerRadi = false;
    static BP_Konfiguracija bpk;

    public static void send(String msg) {

        try {
            for (Session session : queue) {
                session.getBasicRemote().sendText(msg);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("---Primljena poruka---");
       // System.out.println("Session id:  " + session.getId());
        System.out.println("Poruka: " + message);

        long start = System.currentTimeMillis();

        String korisnik = "";
        String aerodrom = "";
        try {
            korisnik = message.split(",")[0];
            aerodrom = message.split(",")[1];
        } catch (Exception e) {
            System.out.println("Ne valja format poruka --> 'korisnik,aerodrom'");
            return;
        }
        System.out.println(korisnik);
        System.out.println(aerodrom);
        Myairports novi = new Myairports();
        novi.setIdent(afl.find(aerodrom));
        novi.setUsername(kfl.find(korisnik));
        novi.setStored(new Date());
        Object pokusaj = mafl.dohvatiZapis(korisnik, aerodrom);
        if (pokusaj == null) {
            mafl.create(novi);
            jms1.salji(korisnik, aerodrom);
            long stop = System.currentTimeMillis();
            
            String url = session == null ? "no-info" : session.getRequestURI().toString();
            String ip = session == null ? "no-info" : session.getQueryString();
            lfl.zapisiLog("WebSocket dodajAerodromKorisniku", url, ip, korisnik, BigInteger.valueOf(stop - start));
        }

    }

    @OnOpen
    public void onOpen(Session session) {
        queue.add(session);
        if (!timerRadi) {
            bpk = (BP_Konfiguracija) sc.getAttribute("BP_Konfig");
            long interval = Long.parseLong(bpk.getKonfig().dajPostavku("websocket.interval.brojAerodroma"));
            timerRadi = true;
            tservice.createIntervalTimer(0, interval, new TimerConfig());
        }

        System.out.printf("Session spojen, id: %s%n", session.getId());
    }

    @OnError
    public void onError(Throwable e) {
        System.out.printf("Session greška");
    }

    @OnClose
    public void onClose(Session session) {
        queue.remove(session);
        System.out.printf("Session zatvoren, id: %s%n", session.getId());
    }

    @Timeout
    public void posaljiPodatke() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        send(mafl.dajAerodromeKojiSePrate().size() + "," + sdf.format(new Date()));
    }
}
