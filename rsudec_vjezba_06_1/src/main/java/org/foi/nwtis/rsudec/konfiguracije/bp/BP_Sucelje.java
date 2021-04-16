package org.foi.nwtis.rsudec.konfiguracije.bp;

import java.util.Properties;
import org.foi.nwtis.rsudec.konfiguracije.Konfiguracija;

/**
 *
 * @author rsudec
 */
public interface BP_Sucelje {
    String getAdminDatabase();
    String getAdminPassword();
    String getAdminUsername();
    String getDriverDatabase();
    String getDriverDatabase(String bp_url);
    Properties getDriversDatabase();
    String getServerDatabase();
    String getUserDatabase();
    String getUserPassword();
    String getUserUsername();  
    Konfiguracija getKonfig();
}
