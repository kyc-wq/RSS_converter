
package rssconverter.Downloader;

import rssconverter.News;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.jespxml.JespXML;
import org.jespxml.excepciones.TagHijoNotFoundException;
import org.jespxml.modelo.Encoding;
import org.jespxml.modelo.Tag;
import org.xml.sax.SAXException;


/**
 *
 * @author user
 */
public class Downlaoder {
    private String url;
    private JespXML rssDoc;
    private Tag tagPrincipal;
    private final List<News> info;
    
    public Downlaoder(String url) throws IOException, 
            ParserConfigurationException, SAXException, TagHijoNotFoundException {            
            this.url = url;
            this.info = new ArrayList<>();
            this.rssDoc = new JespXML(new URL(url));
            this.rssDoc.setEncoding(Encoding.UTF_8);
            this.tagPrincipal = this.rssDoc.leerXML().getTagHijoByName("channel");
           
    }
    
    
    public  String getUrl() {
        return url;
    }
    
    public void setURl(String url) {
        this.url = url;
    }

    
    
    
    public List<News> update() throws TagHijoNotFoundException, ParserConfigurationException, IOException, SAXException {
        this.info.clear();
        this.rssDoc = new JespXML(new URL(url));
        this.tagPrincipal = this.rssDoc.leerXML().getTagHijoByName("channel");
        return getInfo();
    }      
    
    
    private List<News> getInfo() throws TagHijoNotFoundException, ParserConfigurationException, SAXException, IOException {
        this.info.clear(); 
        List<Tag> listaDeTags = this.tagPrincipal.getTagsHijos();
        int id = 1;
        
        for(Tag tag: listaDeTags) {
                  
         if (tag.getNombre().equals("item")) { //ahorra ejecucion de recursos.
                News noticia = new News();
                noticia.setId(id);
                noticia.setTitulo(getInfoTag(tag, "title"));
                noticia.setDescripcion(getInfoTag(tag, "description"));
                noticia.setLink(getInfoTag(tag, "link"));
                noticia.setFecha(getInfoTag(tag, "pubDate").substring(0, 12));
                
                info.add(noticia);
                id++;
           }
        }
        
        return info;
    }
    
    
    
    
    private String getInfoTag(Tag tag, String nombre) throws TagHijoNotFoundException {      
        return tag.getTagHijoByName(nombre).getContenido();
    }
    
    
    
}
