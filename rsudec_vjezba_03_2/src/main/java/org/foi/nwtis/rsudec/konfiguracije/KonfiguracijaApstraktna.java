/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.konfiguracije;

import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author NWTiS_3
 */
public abstract class KonfiguracijaApstraktna implements Konfiguracija{
    
    protected String nazivDatoteke;
    protected Properties postavke;
    
    public KonfiguracijaApstraktna(String nazivDatoteke) {
        this.nazivDatoteke = nazivDatoteke;
        this.postavke = new Properties();
    }    
    
    @Override
    public void dodajKonfiguraciju(Properties postavke){
        for(Enumeration e = postavke.keys(); e.hasMoreElements(); ){
            String kljuc = (String) e.nextElement();
            String vrijednost = postavke.getProperty(kljuc);
            this.postavke.setProperty(kljuc, vrijednost);
        }
    }

    @Override
    public void kopirajKonfiguraciju(Properties postavke) {
        this.postavke = postavke;
    }

    @Override
    public Properties dajSvePostavke() {
        return this.postavke;
    }

    @Override
    public boolean obrisiSvePostavke() {
        if(this.postavke.isEmpty()){
            return false;
        } else {
            this.postavke.clear();
            return true;
        }
    }

    @Override
    public String dajPostavku(String postavka) {
        return this.postavke.getProperty(postavka);
    }

    @Override
    public boolean spremiPostavku(String postavka, String vrijednost) {
        if(this.postavke.containsKey(postavka)){
            return false;
        } else {
            this.postavke.setProperty(postavka, vrijednost);
            return true;
        }
    }

    @Override
    public boolean azurirajPostavku(String postavka, String vrijednost) {
        if(!this.postavke.containsKey(postavka)){
            return false;
        } else {
            this.postavke.setProperty(postavka, vrijednost);
            return true;
        }
    }

    @Override
    public boolean postojiPostavka(String postavka) {
        return this.postavke.containsKey(postavka);
    }

    @Override
    public boolean obrisiPostavku(String postavka) {
        if(!this.postavke.containsKey(postavka)){
            return false;
        } else {
            this.postavke.remove(postavka);
            return true;
        }
    }
    
    public static Konfiguracija kreirajKonfiguraciju(String datoteka) throws NemaKonfiguracije, NeispravnaKonfiguracija {
        Konfiguracija konfiguracija = dajIspravnuKonfiguraciju(datoteka);
        konfiguracija.spremiKonfiguraciju();
        return konfiguracija;
        
    }
    public static Konfiguracija preuzmiKonfiguraciju(String datoteka) throws NemaKonfiguracije, NeispravnaKonfiguracija { 
        Konfiguracija konfiguracija = dajIspravnuKonfiguraciju(datoteka);
        konfiguracija.ucitajKonfiguraciju();
        return konfiguracija;
    }

    private static Konfiguracija dajIspravnuKonfiguraciju(String datoteka) throws NeispravnaKonfiguracija {
        int p = datoteka.lastIndexOf(".");
        if(p == -1)
            throw new NeispravnaKonfiguracija("Datoteka nema ekstenziju");
        
        String ekstenzija = datoteka.substring(p + 1).toLowerCase();
        
        switch(ekstenzija) {
            case "txt": return new KonfiguracijaTXT(datoteka);
            case "xml": return new KonfiguracijaXML(datoteka);
            case "bin": return new KonfiguracijaBIN(datoteka);
            case "json": return new KonfiguracijaJSON(datoteka);
            default:
                throw new NeispravnaKonfiguracija("Datoteka ima krivu ekstenziju");
                
        } 
    }
}
