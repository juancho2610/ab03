/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.services;

import com.example.PersistenceManager;
import com.example.models.Producto;
import com.example.models.ProductoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONObject;
/**
 *
 * @author ASUS
 */
@Path("/producto")
@Produces(MediaType.APPLICATION_JSON)
public class ProductoService {
   
    @PersistenceContext (unitName = "lab03")
        EntityManager entityManager;
    
    @PostConstruct
    public void init(){
        try{
            entityManager = PersistenceManager.getIntstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        Query q = entityManager.createQuery("selct u from Competitor u order by u.surname ASC");
        List<Producto> producto = new ArrayList<Producto>();
        Producto productoTmp= new Producto("Manzana",5.00,"Fruta");
        Producto productoTmp2= new Producto("Pera",9.00,"Fruta");
        Producto productoTmp3= new Producto("Lechuga",10.00,"Vegetal");
        producto.add(productoTmp);
        producto.add(productoTmp2);
        producto.add(productoTmp3);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(producto).build();
    }
    

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCompetitor(ProductoDTO producto) {
        JSONObject rta = new JSONObject();
        Producto productoTmp= new Producto();
        productoTmp.setName(producto.getName());
        productoTmp.setPrice(producto.getPrice());
        //productoTmp.setDescription(producto.getDescription());
        
        
        try { 
            entityManager.getTransaction().begin(); 
            entityManager.persist(productoTmp); 
            entityManager.getTransaction().commit(); 
            entityManager.refresh(productoTmp); 
            rta.put("producto_id",producto); 
        } catch (Throwable t) { t.printStackTrace(); if (entityManager.getTransaction().isActive()) { entityManager.getTransaction().rollback(); } productoTmp = null; } finally { entityManager.clear(); entityManager.close(); }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(productoTmp).build();
    }
    
}
