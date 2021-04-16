/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ejb.sb;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import org.foi.nwtis.rsudec.ejb.eb.Myairportslog;

/**
 *
 * @author Robi
 */
@Local
public interface MyairportslogFacadeLocal {

    void create(Myairportslog myairportslog);

    void edit(Myairportslog myairportslog);

    void remove(Myairportslog myairportslog);

    Myairportslog find(Object id);

    List<Myairportslog> findAll();

    List<Myairportslog> findRange(int[] range);

    int count();
    
    Boolean aerodromJeObradenZaDatum(String ident, Date datum);
    
    int brojDanaSPodacima(String icao);
    
}
