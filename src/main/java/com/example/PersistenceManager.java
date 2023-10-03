/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

/**
 *
 * @author ASUS
 */
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
   
    public static final boolean DEBUG = true;
    
    private static final PersistenceManager singleton = new PersistenceManager();
    
    protected EntityManagerFactory emf;
    
    public static PersistenceManager getIntstance(){
        
        return singleton;
    }
    
    private PersistenceManager(){
        
    }
    
    public EntityManagerFactory getEntityManagerFactory(){
        
        if(emf == null){
            createEntityManagerFactory();
        }
        
        return emf;
    }
    
    public void closeEntityManagerFactory(){
        
        if(emf != null){
            emf.close();
            emf =null;
            if(DEBUG){
                System.out.println("Persistence finished" + new java.util.Date());
            }
        }
    }
    
    protected void createEntityManagerFactory(){
        
        this.emf= Persistence.createEntityManagerFactory("lab03",System.getProperties());
        
        if(DEBUG){
            System.out.println("Persistence started" + new java.util.Date());
        }
                
    }
    
}
