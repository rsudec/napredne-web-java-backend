/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.konfiguracije.bp;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.rsudec.konfiguracije.Konfiguracija;
import org.foi.nwtis.rsudec.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.rsudec.konfiguracije.NeispravnaKonfiguracija;
import org.foi.nwtis.rsudec.konfiguracije.NemaKonfiguracije;

/**
 *
 * @author roby0
 */
public class BP_Konfiguracija implements BP_Sucelje {
    public Konfiguracija konf;
    public BP_Konfiguracija(String datoteka) throws NemaKonfiguracije, NeispravnaKonfiguracija{
        System.out.println(datoteka);
            konf = KonfiguracijaApstraktna.preuzmiKonfiguraciju(datoteka);
    }
    
    @Override
    public String getAdminDatabase() {
        return konf.dajPostavku("admin.database");
    }

    @Override
    public String getAdminPassword() {
        return konf.dajPostavku("admin.password");
    }

    @Override
    public String getAdminUsername() {
        return konf.dajPostavku("admin.username");
    }

    @Override
    public String getDriverDatabase() {
        String serverDatabase = getServerDatabase();
        return getDriverDatabase(serverDatabase);
        
    }

    @Override
    public String getDriverDatabase(String bp_url) {
        String[] p = bp_url.split(":");
        return konf.dajPostavku("jdbc."+p[1]);
    }

    @Override
    public Properties getDriversDatabase() {
        Properties result = new Properties();
        konf.dajSvePostavke().forEach((key, val) -> {
            String kljuc = (String) key;
            String vrijednost = (String) val;
            if(kljuc.startsWith("jdbc."))
                result.setProperty(kljuc, vrijednost);
        });
        /*for(Object o : konf.dajSvePostavke().keySet()){
            String k = (String) o;
            if(k.startsWith("jdbc")){
                String v = konf.dajPostavku(k);
                result.setProperty(k, v);
            }
        }*/
        return result;
    }

    @Override
    public String getServerDatabase() {
        
        return konf.dajPostavku("server.database");
    }

    @Override
    public String getUserDatabase() {
        return konf.dajPostavku("user.database");
    }

    @Override
    public String getUserPassword() {
        return konf.dajPostavku("user.password");
    }

    @Override
    public String getUserUsername() {
        return konf.dajPostavku("user.username");
    }
    @Override
    public Konfiguracija getKonfig() {
        return konf;
    }
}
