package org.foi.nwtis.rsudec.ejb.eb;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-02T22:04:06")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, Date> vrijeme;
    public static volatile SingularAttribute<Log, BigInteger> trajanje;
    public static volatile SingularAttribute<Log, String> ipadresa;
    public static volatile SingularAttribute<Log, Integer> id;
    public static volatile SingularAttribute<Log, String> url;
    public static volatile SingularAttribute<Log, String> akcija;
    public static volatile SingularAttribute<Log, String> korisnik;

}