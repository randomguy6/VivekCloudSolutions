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
    public void handleMessage(String message, Session clientSession) {
        System.out.println("Handle Message Method" + message);
       
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            
            System.out.println("handle message 2");
            JsonObject jsonMessage = reader.readObject();
            System.out.println("handle message 3");
            String action = jsonMessage.getString("action");
            if (action.equals("add")) {
                System.out.println("handle message ADD");
                ServerCred server = new ServerCred();
                server.setProcessorCore(jsonMessage.getInt("pCore"));
                server.setProcessorSpeed(jsonMessage.getInt("pSpeed"));
                server.setRamSpeed(jsonMessage.getInt("rSpeed"));
                server.setRamCore(jsonMessage.getInt("rCap"));
                server.setType(jsonMessage.getString("nType"));
                handler.addServer(server);
                /*try {
                    System.out.println("send message back.." + server.toString());
                    clientSession.getBasicRemote().sendText(server.toString());
                    System.out.println("send messgae completed");
                } 
                catch (Exception e) {
                }*/
            }
                else if(action.equals("remove")) {  
                System.out.print("handle message REMOVE");
                int id = jsonMessage.getInt("id");
                handler.removeServer(id);
            }
            else{
                System.out.println("COuldnt find if statement");
            }
            }
        }

    }
