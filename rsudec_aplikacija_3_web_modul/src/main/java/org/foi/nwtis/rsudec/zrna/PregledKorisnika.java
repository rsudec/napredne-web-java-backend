/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.zrna;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.foi.nwtis.rsudec.ejb.eb.Korisnici;
import org.foi.nwtis.rsudec.ejb.sb.KorisniciFacadeLocal;

/**
 *
 * @author Robi
 */
@Named(value = "pregledKorisnika")
@ViewScoped
public class PregledKorisnika implements Serializable {

    @EJB
    KorisniciFacadeLocal kfl;

    @Getter
    @Setter
    List<Korisnici> korisnici = new ArrayList<>();

    @Getter
    @Setter
    List<Korisnici> filtriraniKorisnici = new ArrayList<>();

    public PregledKorisnika() {
    }

    @PostConstruct
    public void loadUsers() {
        
        korisnici = kfl.findAll();
        filtriraniKorisnici = korisnici;
    }

}
