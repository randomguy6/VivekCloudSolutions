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
import java.io.IOException;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import org.vivek.model.ServerCred;

@ApplicationScoped
public class MeassageHandler {
    private int deviceId = 0;
    private final Set<Session> sessions = new HashSet<>();
    private final Set<ServerCred> servers = new HashSet<>();
    
    public void addSession(Session session){
        System.out.println("addSession Method");
        sessions.add(session);
    }
    
    public void addServer(ServerCred server){
        System.out.println("addServer Method");
        server.setId(deviceId);
        servers.add(server);
        /*for(ServerCred serv: servers){
            System.out.println(serv.toString());
        }*/
        JsonObject addMessage = createAddMessage(server);
        sendToAllConnectedSessions(addMessage);
        System.out.println("sent to all sessions");
    }
    
    public void removeServer(int id){
        System.out.println("removeServer method");
        ServerCred server = getServerId(id);
        servers.remove(server);
        JsonProvider provider = JsonProvider.provider();
        JsonObject removeMessage = provider.createObjectBuilder()
                .add("action", "remove")
                .add("id", id)
                .build();
        sendToAllConnectedSessions(removeMessage);
    }
    
    public void removeSession(Session session){
        System.out.println("removeSession Method");
        sessions.remove(session);
    }
        
    private JsonObject createAddMessage(ServerCred survey) {
        System.out.println("createAddMessage Method");
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("processor", survey.getProcessorCore())
                .add("ram", survey.getRamSpeed())
                .add("Type", survey.getType())
                .build();
        return addMessage;
    }

    private ServerCred getServerId(int id) {
        ServerCred ret = new ServerCred();
        for(ServerCred serv : servers){
            if(serv.getId()==id)
                serv = ret;
        }
        return ret;
    }
    
    private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
            System.out.println("sent back to browser");
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(MeassageHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }


