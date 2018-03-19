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
        servers.add(server);
    }
    
    public void removeServer(int id){
        System.out.println("removeServer method");
        ServerCred server = getServerId(id);
        servers.remove(server);
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "remove")
                .add("id", id)
                .build();
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
    }


