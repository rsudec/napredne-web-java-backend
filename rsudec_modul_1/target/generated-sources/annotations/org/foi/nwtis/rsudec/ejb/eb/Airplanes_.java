package org.foi.nwtis.rsudec.ejb.eb;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.foi.nwtis.rsudec.ejb.eb.Airports;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-02T22:04:06")
@StaticMetamodel(Airplanes.class)
public class Airplanes_ { 

    public static volatile SingularAttribute<Airplanes, Integer> lastseen;
    public static volatile SingularAttribute<Airplanes, Integer> estdepartureairporthorizdistance;
    public static volatile SingularAttribute<Airplanes, Integer> estdepartureairportvertdistance;
    public static volatile SingularAttribute<Airplanes, String> icao24;
    public static volatile SingularAttribute<Airplanes, Integer> arrivalairportcandidatescount;
    public static volatile SingularAttribute<Airplanes, Airports> estdepartureairport;
    public static volatile SingularAttribute<Airplanes, Integer> departureairportcandidatescount;
    public static volatile SingularAttribute<Airplanes, Integer> estarrivalairportvertdistance;
    public static volatile SingularAttribute<Airplanes, Integer> firstseen;
    public static volatile SingularAttribute<Airplanes, String> estarrivalairport;
    public static volatile SingularAttribute<Airplanes, Date> stored;
    public static volatile SingularAttribute<Airplanes, String> callsign;
    public static volatile SingularAttribute<Airplanes, Integer> id;
    public static volatile SingularAttribute<Airplanes, Integer> estarrivalairporthorizdistance;

}