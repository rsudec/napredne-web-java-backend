/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ejb.sb;

import java.math.BigInteger;
import java.util.List;
import javax.ejb.Local;
import org.foi.nwtis.rsudec.ejb.eb.Log;

/**
 *
 * @author Robi
 */
@Local
public interface LogFacadeLocal {

    void create(Log log);

    void edit(Log log);

    void remove(Log log);

    Log find(Object id);

    List<Log> findAll();

    List<Log> findRange(int[] range);

    int count();
    
    void zapisiLog(String action, String url, String ip, String kor, BigInteger trajanje);
    
    List<Log> dajLogKorisnika(String korisnik);
}
