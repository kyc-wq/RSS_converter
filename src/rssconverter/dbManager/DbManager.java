/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rssconverter.dbManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.jespxml.excepciones.TagHijoNotFoundException;
import org.xml.sax.SAXException;
import rssconverter.News;


/**
 *
 * @author user
 */
public class DbManager {
    
    private String dbPath;
    private Connection dbConnection;
    private final Statement consultor;
    
    
    
    
    
    public DbManager(String dbPath) throws ClassNotFoundException, SQLException, IOException, 
            SAXException, TagHijoNotFoundException, ParserConfigurationException {
        
        Class.forName("org.sqlite.JDBC");
        this.dbPath = dbPath;
        this.dbConnection = DriverManager.getConnection("jdbc:sqlite:noticias.sqlite");
        this.consultor = this.dbConnection.createStatement();
        
        
        //List<News> ListInfo = rssDoc.getInfo();
    }
    
    
    public void update(List<News> noticias) throws SQLException, TagHijoNotFoundException, 
            SAXException, IOException, ParserConfigurationException {
        
        
        //System.out.println(noticias.size());
        //Statement consult = this.dbConnection.createStatement();
        eliminarTodo();
        
        for(News noticia: noticias) {
            
            consultor.executeUpdate("INSERT INTO Noticias(id, titulo, noticia, link, fecha) VALUES (" + noticia.getId() + ", \"" 
                    + noticia.getTitulo() + "\", \"" 
                    + noticia.getDescripcion() + "\", \"" 
                    + noticia.getLink() +  "\", \"" 
                    + noticia.getFecha() +"\")");
            
        }
        
        this.consultor.close();
    }
    
    
    public List<News> getDb_info() throws SQLException {
        List<News> noticias = new ArrayList<>();
        ResultSet rs = this.consultor.executeQuery("Select * from Noticias");
        
        
        while(rs.next()) {
            News noticia = new News(rs.getInt("id"), 
                    rs.getString("titulo"),rs.getString("noticia") ,
                    rs.getString("link"), rs.getString("fecha"));
            noticias.add(noticia);
            
        }
        return noticias;
    }
    
    
    
    
    
    public void eliminarTodo() throws SQLException {
        PreparedStatement largeUpdate = dbConnection.prepareStatement("DELETE from Noticias Where 1;");
        largeUpdate.execute();
    }

    
    
    
    public String getDbPath() {
        return dbPath;
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }

    public Connection getDbConnection() {
        return dbConnection;
    }

    public void setDbConnection(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }
   
    
    
    
    
    
    
}
