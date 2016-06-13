package com.example.root.noticias_Udenar.RssParse;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "rss", strict = false)

public class Rss {

    //Clase que representa la etiqueta principal del lector Rss

    @Element
    private Channel channel;

    public Rss() {
    }

    public Rss(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }
}
