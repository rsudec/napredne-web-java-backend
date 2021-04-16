package org.foi.nwtis.rsudec.ejb.eb;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.foi.nwtis.rsudec.ejb.eb.Airplanes;
import org.foi.nwtis.rsudec.ejb.eb.Myairports;
import org.foi.nwtis.rsudec.ejb.eb.Myairportslog;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-02T22:04:06")
@StaticMetamodel(Airports.class)
public class Airports_ { 

    public static volatile SingularAttribute<Airports, String> elevationFt;
    public static volatile SingularAttribute<Airports, String> continent;
    public static volatile SingularAttribute<Airports, String> isoRegion;
    public static volatile ListAttribute<Airports, Myairports> myairportsList;
    public static volatile SingularAttribute<Airports, String> gpsCode;
    public static volatile SingularAttribute<Airports, String> ident;
    public static volatile SingularAttribute<Airports, String> municipality;
    public static volatile SingularAttribute<Airports, String> coordinates;
    public static volatile SingularAttribute<Airports, String> type;
    public static volatile SingularAttribute<Airports, String> iataCode;
    public static volatile ListAttribute<Airports, Airplanes> airplanesList;
    public static volatile SingularAttribute<Airports, String> name;
    public static volatile SingularAttribute<Airports, String> localCode;
    public static volatile ListAttribute<Airports, Myairportslog> myairportslogList;
    public static volatile SingularAttribute<Airports, String> isoCountry;

}