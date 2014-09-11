/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rssconverter;

/**
 *
 * @author user
 */
public class News {
    private int id;
    private String titulo;
    private String descripcion;
    private String link;
    private String fecha;
    
    
    
    public News(int id, String titulo, String descripcion, String link, String fecha) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.link = link;
        this.fecha = fecha;
        
    }
    
    
    public News() {
        this.id = 0;
        this.titulo = "";
        this.descripcion = "";
        this.link = "";
        this.fecha = "";
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }    
    
}
