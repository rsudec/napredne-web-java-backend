/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ejb.sb;

import java.util.List;
import javax.ejb.Local;
import org.foi.nwtis.rest.podaci.AvionLeti;
import org.foi.nwtis.rsudec.ejb.eb.Airplanes;

/**
 *
 * @author Robi
 */
@Local
public interface AirplanesFacadeLocal {

    void create(Airplanes airplanes);

    void edit(Airplanes airplanes);

    void remove(Airplanes airplanes);

    Airplanes find(Object id);

    List<Airplanes> findAll();

    List<Airplanes> findRange(int[] range);

    int count();
    
    void dodajAvionLeti(AvionLeti a);
    
    List<AvionLeti> dajAvioneSAerodroma(String icao, long vrijemeOd, long vrijemeDo);
    
    List<AvionLeti> dajLetoveAviona(String icao24, long vrijemeOd, long vrijemeDo);
    
    Boolean imaAvionaSAerodroma(String icao);
    
    int obrisiAvioneSAerodroma(String icao);
    int brojLetovaSAerodroma(String icao);
}
