/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ejb.sb;

import java.util.List;
import javax.ejb.Local;
import org.foi.nwtis.rsudec.ejb.eb.Mqttporuka;

/**
 *
 * @author Robi
 */
@Local
public interface MqttporukaFacadeLocal {

    void create(Mqttporuka mqttporuka);

    void edit(Mqttporuka mqttporuka);

    void remove(Mqttporuka mqttporuka);

    Mqttporuka find(Object id);

    List<Mqttporuka> findAll();

    List<Mqttporuka> findRange(int[] range);

    int count();
    
    List<Mqttporuka> dajMqttKorisnika(String korisnik);
    
    int obrisiPorukeKorisnika(String korisnik);
}
