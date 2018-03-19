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

    private String processorCore;
    private String processorSpeed;
    private String ramSpeed;
    private String ramCore;
    private String Type;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public ServerCred(String processor, String ram) {
        this.id = id++;
        this.processorCore = processor;
        this.ramSpeed = ram;
    }
    public ServerCred(){
    }

    public String getProcessorCore() {
        return processorCore;
    }

    public void setProcessorCore(String processorCore) {
        this.processorCore = processorCore;
    }

    public String getRamSpeed() {
        return ramSpeed;
    }

    public void setRamSpeed(String ramSpeed) {
        this.ramSpeed = ramSpeed;
    }

    public String getProcessorSpeed() {
        return processorSpeed;
    }

    public void setProcessorSpeed(String processorSpeed) {
        this.processorSpeed = processorSpeed;
    }

    public String getRamCore() {
        return ramCore;
    }

    public void setRamCore(String ramCore) {
        this.ramCore = ramCore;
    }
    
    

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    
    public String toString(){
        return "Added new Server configuration; Ram Speed: ("+ramSpeed+" MHz) Ram Capacity: ("+ramCore+ "GB), processor core count: ("+processorCore +"-core) processor speed: ("+processorSpeed+ " GHz) networktype: ("+Type +").";
    }
}
