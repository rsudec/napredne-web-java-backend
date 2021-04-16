/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.konfiguracije;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author NWTiS_3
 */
public class KonfiguracijaTXT extends KonfiguracijaApstraktna {

    public KonfiguracijaTXT(String nazivDatoteke) {
        super(nazivDatoteke);
    }

    @Override
    public void ucitajKonfiguraciju() throws NemaKonfiguracije, NeispravnaKonfiguracija {
        ucitajKonfiguraciju(this.nazivDatoteke);
    }

    @Override
    public void ucitajKonfiguraciju(String datoteka) throws NemaKonfiguracije, NeispravnaKonfiguracija {
        this.obrisiSvePostavke();
        
        if(datoteka == null || datoteka.length() == 0){
            throw new NemaKonfiguracije("Neispravni naziv datoteke!");
        }
        File f = new File(datoteka);
        
        if(f.exists() && f.isFile()){ 
            try {
                this.postavke.load(new FileInputStream(f));
            } catch (IOException ex) {
                throw new NeispravnaKonfiguracija("Problem kod učitavanja konfiguracije");
            }
        } else {
            throw new NemaKonfiguracije("Datoteka pod nazivom " + datoteka + " ne postoji ili nije datoteka.");
        }
        
        
    }

    @Override
    public void spremiKonfiguraciju() throws NemaKonfiguracije, NeispravnaKonfiguracija {
        spremiKonfiguraciju(this.nazivDatoteke);
    }

    @Override
    public void spremiKonfiguraciju(String datoteka) throws NemaKonfiguracije, NeispravnaKonfiguracija {
        
        if(datoteka == null || datoteka.length() == 0){
            throw new NemaKonfiguracije("Neispravni naziv datoteke!");
        }
        File f = new File(datoteka);
        
        if((f.exists() && f.isFile()) || !f.exists()){ 
            try {
                this.postavke.store(new FileOutputStream(f), "rsudec");
            } catch (IOException ex) {
                throw new NeispravnaKonfiguracija("Problem kod učitavanja konfiguracije");
            }
        } else {
            throw new NemaKonfiguracije("Datoteka pod nazivom " + datoteka + " ne postoji ili nije datoteka.");
        }
    }
    
}
