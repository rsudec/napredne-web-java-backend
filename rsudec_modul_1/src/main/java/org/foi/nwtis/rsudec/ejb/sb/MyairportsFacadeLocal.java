/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ejb.sb;

import java.util.List;
import javax.ejb.Local;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rsudec.ejb.eb.Airports;
import org.foi.nwtis.rsudec.ejb.eb.Myairports;

/**
 *
 * @author Robi
 */
@Local
public interface MyairportsFacadeLocal {

    void create(Myairports myairports);

    void edit(Myairports myairports);

    void remove(Myairports myairports);

    Myairports find(Object id);

    List<Myairports> findAll();

    List<Myairports> findRange(int[] range);

    int count();
    
    List<Aerodrom> dajAerodromeKojiSePrate();
    
    List<Aerodrom> dajAerodromeKorisnika(String korisnik);
    
    Boolean obrisiAerodromKorisnika(String korisnik, String icao);
    
    Myairports dohvatiZapis(String korisnik, String icao);
    int brojPratiocaAerodroma(String icao);
}
