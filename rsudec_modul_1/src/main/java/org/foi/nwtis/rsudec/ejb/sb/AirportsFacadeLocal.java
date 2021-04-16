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

/**
 *
 * @author Robi
 */
@Local
public interface AirportsFacadeLocal {

    void create(Airports airports);

    void edit(Airports airports);

    void remove(Airports airports);

    Airports find(Object id);

    List<Airports> findAll();

    List<Airports> findRange(int[] range);

    int count();
    
    List<Aerodrom> dajPoNazivu(String naziv);
    
    List<Aerodrom> dajPoDrzavi(String isoCountry);

    double dajUdaljenostAerodroma(String icao1, String icao2);
    
    List<Aerodrom> dajAerodromeUKrugu(String korisnik, String icao, String min, String max);
}
