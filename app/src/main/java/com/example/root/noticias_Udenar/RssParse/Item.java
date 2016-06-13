package com.example.root.noticias_Udenar.RssParse;



import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "item", strict = false)
public class Item {

    //clase que representa cada noticia del lector Rss

    @Element(name="title")
    private String title;

    @Element(name = "description")
    private String descripcion;

    @Element(name="link")
    private String link;

    @Element(name="encoded")
    private String content;



    public Item() {
    }

    public Item(String title, String descripcion, String link, String content) {
        this.title = title;
        this.descripcion = descripcion;
        this.link = link;
        this.content = content;

    }

    public String getTitle() {
        return title;
    }

    public String getDescripcion() {
        String aux = descripcion;
        try{


            int i = aux.indexOf("</p>");
            aux = aux.substring(0,i);
            aux = aux.substring(3);
            aux = aux.replace("&#160;","");
            aux+="...";
            return aux;
        }catch (Exception e) {
            aux = aux.replace("&#160;","");
            return aux;
        }
    }

    public String getLink() {
        return link;
    }

    public String getImg() {

        String aux = "";
       try{
            aux = content;
           int i = aux.indexOf("</a>");
           aux = aux.substring(0,i);
           i = aux.indexOf("src");
           aux = aux.substring(i);
           i = aux.indexOf("http");
           aux = aux.substring(i);
           i = aux.indexOf('"');
           aux = aux.substring(0,i);
       }catch (Exception e){
            aux = "http://www.udenar.edu.co/wp-content/uploads/2010/12/color.gif";
       }

       return aux;

    }
}
