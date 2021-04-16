
package org.foi.nwtis.rsudec.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aerodromStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="aerodromStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PASIVAN"/>
 *     &lt;enumeration value="AKTIVAN"/>
 *     &lt;enumeration value="BLOKIRAN"/>
 *     &lt;enumeration value="NEPOSTOJI"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "aerodromStatus")
@XmlEnum
public enum AerodromStatus {

    PASIVAN,
    AKTIVAN,
    BLOKIRAN,
    NEPOSTOJI;

    public String value() {
        return name();
    }

    public static AerodromStatus fromValue(String v) {
        return valueOf(v);
    }

}
