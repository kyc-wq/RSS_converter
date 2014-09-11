//main

/*

Dificultades: 
    - "jdbc para Sqlite" no soporta UTF8, por lo que pase mucho tiempo
      trabajando en poder insertar letras con acento en la base de datos
      , pero aunque la base de datos lo soportaba y la lib de XML tambien JDBC
        no lo hacia. por lo tanto decidi usar un rss en idioma ingles.

    - problema en la obtension de noticias desde RSS en la web, ya que muestra
      el programa algunas repeticiones de las noticias.



Elementos no Solucionados: 

    - Generador de HTML
        [Motivo]: ya que al desarrollar software se lo hace contruyendo modulos
                  que son dependientes o consecutivos. 
                  
                  Mi desarrollo ha sido de manera consecutiva, por lo tanto 
                  al haber terminado los  modulos de Downloader y DbManager 
                  me percate del error en Downloader lo que concluyo en una 
                  no implementacion de Shower (mostrador en formato html) ya 
                  que era dependiente de los otros.

*/






package rssconverter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.jespxml.excepciones.TagHijoNotFoundException;
import org.xml.sax.SAXException;
import rssconverter.dbManager.DbManager;
import rssconverter.Downloader.Downlaoder;
import rssconverter.ParserShow.Show;

public class RSSconverter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, SAXException, TagHijoNotFoundException, ParserConfigurationException, FileNotFoundException, TransformerException {
        Downlaoder doc = new Downlaoder("http://bits.blogs.nytimes.com/feed/");
        DbManager db = new DbManager("noticias.sqlite");
                
        List<News> noticias = doc.update();
        db.update(noticias);
             
//        for(News noticia: noticias) {
//            System.out.println("\n\n\n\nid: " + noticia.getId() +
//                    "\n\ntitulo: " + noticia.getTitulo() + 
//                    "\n\ndescripcion: " + noticia.getDescripcion() + 
//                    "\n\nlink: " + noticia.getLink() );
//        }
        
        Show Parser = new Show();
        Parser.Parse(noticias);
        
        
    }
    
}
