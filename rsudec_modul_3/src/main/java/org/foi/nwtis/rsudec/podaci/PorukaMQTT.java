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
public class PorukaMQTT  implements Serializable{
    @Getter @Setter
    String id;
    @Getter @Setter
    String korisnik;
    @Getter @Setter
    String poruka;
    @Getter @Setter
    String vrijeme;

    public PorukaMQTT(String id, String korisnik, String poruka, String vrijeme) {
        this.id = id;
        this.korisnik = korisnik;
        this.poruka = poruka;
        this.vrijeme = vrijeme;
    }

}
