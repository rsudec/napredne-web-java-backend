/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.websocket.client;

import java.io.IOException;
import java.net.URI;
import javax.ejb.Stateless;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author Robi
 */
@Stateless
@ClientEndpoint
public class WebSocketClient {

    
    private final String uri="ws://localhost:8084/rsudec_aplikacija_2_pomoc/websocket";
    private Session session;
   

   public WebSocketClient(){
      try{
         WebSocketContainer container= ContainerProvider.getWebSocketContainer();
         container.connectToServer(this, new URI(uri));
      }catch(Exception ex){

      }
   }

   @OnOpen
   public void onOpen(Session session){
      this.session=session;
   }

   @OnMessage
   public void onMessage(String message, Session session){

   }

   public void sendMessage(String message){
      try {
         session.getBasicRemote().sendText(message);
      } catch (IOException ex) {

      }
   }
}
