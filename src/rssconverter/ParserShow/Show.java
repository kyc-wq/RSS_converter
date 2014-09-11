/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rssconverter.ParserShow;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import rssconverter.News;

/**
 *
 * @author user
 */
public class Show {
    private int cantidad;
    private final String fechaActual;
    
    public Show() {
        Calendar fecha = new GregorianCalendar();
        this.fechaActual = fecha.get(Calendar.DAY_OF_MONTH) + "/"
                + (fecha.get(Calendar.MONTH) + 1) + "/"
                + fecha.get(Calendar.YEAR);
     }
    
    
    public void Parse(List<News> noticias) throws ParserConfigurationException, 
            FileNotFoundException, TransformerException, UnsupportedEncodingException, IOException {
        
        try (PrintWriter writer = new PrintWriter("index.html", "UTF-8")) {
            writer.print("<html lang=\"en\">\n<head></head>\n<body>\n<meta charset=\"utf-8\">"
                    + "\n<link rel=\"stylesheet\" href=\"styles.css\">"
                    + "<h1>Cybert News<h1><h6>      ["+ this.fechaActual +"]</h6>\n<br></br>");
            
            
            for(News noticia: noticias) {
                writer.print("<header>\n<hgroup>\n<h2>"+ noticia.getTitulo()
                        + "</h2>\n</hgroup>\n</header>\n"
                        + "<nav> <ul></ul> </nav>"); //1ra barra
                
                writer.print("<article>\n" +"<header>\n<time>" + noticia.getFecha().substring(0,12) + "</time>\n"
                        + "<a href="+ noticia.getLink() + ">&nbsp&nbsp[ Ver mas...]</a></h1>\n</header>"
                        + "<p>" + noticia.getDescripcion() +"</p>\n</article>"
                        + "\n<nav> <ul></ul> </nav><br></br><br></br>");
            }
            writer.print("<footer>\n<p>© 2014 Jean .C Olives. All rights reserved.</p>\n</footer>"
                    + "\n\n\n</body></html>");
        }
        

        Desktop.getDesktop().browse((new File("index.html")).toURI());
                                          
    }
    
    
    
    
    
    
    
}
