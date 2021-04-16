/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.jms;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jms.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.json.Json;

/**
 *
 * @author Robi
 */
@Stateless
public class JMS_Red_3 {


    @Resource(mappedName = "jms/NWTiS_rsudec_3")
    private Queue nWTiS_rsudec_3;

    @Resource(mappedName = "jms/NWTiS_QF_rsudec_3")
    private ConnectionFactory nWTiS_QF_rsudec_3;
    
    
    static int idPoruke = 0;
    
    public void salji(String korisnik, String poruka, String vrijeme) {
        String porukaJson = Json.createObjectBuilder().add("id", idPoruke).add("korisnik", korisnik).add("poruka", poruka).add("vrijeme", vrijeme).build().toString();
        try {
            saljiJMSPoruku(porukaJson);
            idPoruke += 1;
        } catch (JMSException ex) {
            Logger.getLogger(JMS_Red_3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Message kreirajJMSPoruku(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }
    
    private void saljiJMSPoruku(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = nWTiS_QF_rsudec_3.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer((Destination) nWTiS_rsudec_3);
            messageProducer.send(kreirajJMSPoruku(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
}
