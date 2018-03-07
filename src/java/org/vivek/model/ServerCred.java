/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vivek.model;

/**
 *
 * @author vivek
 */
public class ServerCred {

    private String processor;
    private String ram;
    private String Type;

    public ServerCred(String processor, String ram) {
        this.processor = processor;
        this.ram = ram;
    }
    public ServerCred(){
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    
    public String toString(){
        return "Added new Server configuration :- Ram:"+ram+" processor: "+processor +" networktype:"+Type ;
    }
}
