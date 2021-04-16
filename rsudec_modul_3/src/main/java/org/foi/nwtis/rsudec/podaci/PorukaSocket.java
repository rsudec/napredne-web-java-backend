/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.podaci;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Robi
 */
public class PorukaSocket implements Serializable{
    @Getter @Setter
    String id;
    @Getter @Setter
    String korisnik;
    @Getter @Setter
    String komanda;
    @Getter @Setter
    String vrijemePrijema;
    @Getter @Setter
    String vrijemeSlanja;

    public PorukaSocket(String id, String korisnik, String komanda, String vrijemePrijema, String vrijemeSlanja) {
        this.id = id;
        this.korisnik = korisnik;
        this.komanda = komanda;
        this.vrijemePrijema = vrijemePrijema;
        this.vrijemeSlanja = vrijemeSlanja;
    }
    
    
}
