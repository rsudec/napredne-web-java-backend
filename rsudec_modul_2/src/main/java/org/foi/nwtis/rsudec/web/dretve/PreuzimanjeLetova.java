/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.web.dretve;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rest.klijenti.OSKlijent;
import org.foi.nwtis.rest.podaci.AvionLeti;
import org.foi.nwtis.rsudec.ejb.eb.Myairportslog;
import org.foi.nwtis.rsudec.ejb.sb.AirplanesFacadeLocal;

import org.foi.nwtis.rsudec.ejb.sb.MyairportsFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.MyairportslogFacadeLocal;
import org.foi.nwtis.rsudec.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.rsudec.ss.klijent.ServerSocketKlijent;

/**
 *
 * @author Robi
 */
public class PreuzimanjeLetova extends Thread {

    private BP_Konfiguracija bpk;
    private Date avioniPocetak;
    private Date avioniKraj;
    private String OSUser;
    private String OSPass;
    private int portServerSocket;
    private boolean kraj = false;
    int intervalCiklusa;
    int pauza;
    private OSKlijent oSKlijent;
    MyairportsFacadeLocal mafl;
    MyairportslogFacadeLocal malfl;
    AirplanesFacadeLocal afl;

    public PreuzimanjeLetova(BP_Konfiguracija bpk, MyairportsFacadeLocal mafl, MyairportslogFacadeLocal malfl, AirplanesFacadeLocal afl) {
        this.mafl = mafl;
        this.malfl = malfl;
        this.afl = afl;
        this.bpk = bpk;
    }

    @Override
    public void run() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date trenutni = avioniPocetak;
        while (!kraj) {
            long startTime = System.currentTimeMillis();

            long vrijemeOd = trenutni.getTime() / 1000;
            long vrijemeDo = vrijemeOd + 24 * 60 * 60;

            if (provjeriStanjeServerSocketa()) {
//                System.out.println("PREUZIMANJE TRUE");
                List<Aerodrom> aerodromiKojiSePrate = mafl.dajAerodromeKojiSePrate();

                for (Aerodrom a : aerodromiKojiSePrate) {
                    try {
                        System.out.println("Obrađujem aerodrom : " + a.getIcao());
                        
                        if (malfl.aerodromJeObradenZaDatum(a.getIcao(), trenutni)) {
                            System.out.println(a.getIcao() + " je obrađen zif (malfl.aerodromJeObradenZaDatum(a.getIcao(), trenutni)) {a datum: " + sdf.format(trenutni));
                            continue;
                        }
                        
                        List<AvionLeti> podaciOPolascimaAviona = oSKlijent.getDepartures(a.getIcao(), vrijemeOd, vrijemeDo);
                        for (AvionLeti al : podaciOPolascimaAviona) {
                            if (al.getEstArrivalAirport() != null) {
                                afl.dodajAvionLeti(al);
                            }
                        }
                        Myairportslog zapis = new Myairportslog(a.getIcao(), trenutni);
                        zapis.setStored(new Date());
                        malfl.create(zapis);
                        Thread.sleep(pauza);
                    }
                    //System.out.println(mafl.count());
                    catch (InterruptedException ex) {
                        Logger.getLogger(PreuzimanjeLetova.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {
                System.out.println("PREUZIMANJE FALSE");

            }

            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            if (elapsedTime < intervalCiklusa * 1000) {
                try {
                    Thread.sleep(intervalCiklusa * 1000 - elapsedTime);
                } catch (InterruptedException ex) {
                    interrupt();
                    Logger.getLogger(PreuzimanjeLetova.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(trenutni);
            cal.add(Calendar.DATE, 1);
            trenutni = cal.getTime();

            if (sdf.format(trenutni).equals(sdf.format(new Date())) || trenutni.equals(avioniKraj)) {
                System.out.println("Došao do krajnjeg (današnjeg) datuma");
                try {
                    Thread.sleep(24 * 60 * 60 * 1000);
                } catch (InterruptedException ex) {
                    interrupt();
                    Logger.getLogger(PreuzimanjeLetova.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        super.run(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void start() {
        System.out.println("Počela dretva preuzimanje letova");

        OSUser = bpk.getKonfig().dajPostavku("OpenSkyNetwork.korisnik");
        OSPass = bpk.getKonfig().dajPostavku("OpenSkyNetwork.lozinka");
        intervalCiklusa = Integer.parseInt(bpk.getKonfig().dajPostavku("preuzimanje.ciklus"));
        pauza = Integer.parseInt(bpk.getKonfig().dajPostavku("preuzimanje.pauza"));
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            avioniPocetak = sdf.parse(bpk.getKonfig().dajPostavku("preuzimanje.pocetak"));
            avioniKraj = sdf.parse(bpk.getKonfig().dajPostavku("preuzimanje.kraj"));
        } catch (ParseException ex) {
            Logger.getLogger(PreuzimanjeLetova.class.getName()).log(Level.SEVERE, null, ex);
        }
        portServerSocket = Integer.parseInt(bpk.getKonfig().dajPostavku("posluzitelj.socket.port"));
        oSKlijent = new OSKlijent(OSUser, OSPass);
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    private Boolean provjeriStanjeServerSocketa() {
        String res = ServerSocketKlijent.posalji("KORISNIK rsudec; LOZINKA KelEqb; STANJE;", portServerSocket);
        if (res.contains("11")) {
            return true;
        }
        return false;
    }

    @Override
    public void interrupt() {
        kraj = true;
        System.out.println("Interrupt dretva preuzimanje letova");
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

}
