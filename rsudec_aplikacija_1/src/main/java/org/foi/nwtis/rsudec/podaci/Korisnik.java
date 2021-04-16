/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.podaci;

import lombok.Getter;

/**
 *
 * @author Robi
 */
public class Korisnik {
    @Getter
    String korisnickoIme;
    @Getter
    String lozinka;
    
    public Korisnik(String k, String l){
        this.korisnickoIme = k;
        this.lozinka = l;
    }
    
}
