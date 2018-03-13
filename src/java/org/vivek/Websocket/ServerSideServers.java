/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vivek.Websocket;

/**
 *
 * @author vivek
 */
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.vivek.model.ServerCred;

@ApplicationScoped
@ServerEndpoint("/actions")
public class ServerSideServers {

    @Inject
    private MeassageHandler handler;

    @OnOpen
    public void open(Session session) {
        System.out.println("Open Method");
        handler.addSession(session);
    }

    @OnClose
    public void close(Session session) {
        System.out.println("Close Method");
        handler.removeSession(session);
    }

    @OnError
    public void error(Throwable error) {
        System.out.println("Error Method");
        Logger.getLogger(ServerSideServers.class.getName()).log(Level.SEVERE, null, error);
        ;
    }
    @OnMessage
        public void handleMessage(String message, Session clientSession){
            System.out.println("Handle Message Method" +message);
            try (JsonReader reader = Json.createReader(new StringReader(message))) {
                System.out.println("handle message 2");
                JsonObject jsonMessage = reader.readObject();
                System.out.println("handle message 3");
              if("add".equals(jsonMessage.getString("action"))){
                 System.out.println("handle message 4");
                 ServerCred server = new ServerCred();
                 server.setProcessorCore(jsonMessage.getString("pCore"));
                 server.setProcessorSpeed(jsonMessage.getString("pSpeed"));
                 server.setRamSpeed(jsonMessage.getString("rSpeed"));
                 server.setRamCore(jsonMessage.getString("rCap"));
                 server.setType(jsonMessage.getString("nType"));          
                 try{
                     System.out.println("send message back.."+server.toString());
                 clientSession.getBasicRemote().sendText(server.toString());
                 System.out.println("send messgae completed");
                 }catch(Exception e){}
              }
            }
            
}
}
