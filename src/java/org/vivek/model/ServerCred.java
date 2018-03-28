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

    private int processorCore;
    private int processorSpeed;
    private int ramSpeed;
    private int ramCore;
    private String Type;
    private int id;
    
    public ServerCred(int processor, int ram) {
        this.processorCore = processor;
        this.ramSpeed = ram;
    }
    public ServerCred(){
    }

    public int getProcessorCore() {
        return processorCore;
    }

    public void setProcessorCore(int processorCore) {
        this.processorCore = processorCore;
    }

    public int getProcessorSpeed() {
        return processorSpeed;
    }

    public void setProcessorSpeed(int processorSpeed) {
        this.processorSpeed = processorSpeed;
    }

    public int getRamSpeed() {
        return ramSpeed;
    }

    public void setRamSpeed(int ramSpeed) {
        this.ramSpeed = ramSpeed;
    }

    public int getRamCore() {
        return ramCore;
    }

    public void setRamCore(int ramCore) {
        this.ramCore = ramCore;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String toString(){
        return "Ram Speed: ("+ramSpeed+" MHz) Ram Capacity: ("+ramCore+ "GB), processor core count: ("+processorCore +"-core) processor speed: ("+processorSpeed+ " GHz) networktype: ("+Type +").";
    }
}
