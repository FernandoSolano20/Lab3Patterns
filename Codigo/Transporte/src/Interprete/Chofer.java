package Interprete;

import interfaces.Expresion;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Chofer implements Expresion, Comparable {
    private int id;
    private String nombre;
    private boolean tieneRuta;

    public Chofer(int id, String nombre) {
        setId(id);
        setNombre(nombre);
        setTieneRuta(false);
    }

    public Chofer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean tieneRuta() {
        return tieneRuta;
    }

    public void setTieneRuta(boolean tieneRuta) {
        this.tieneRuta = tieneRuta;
    }

    @Override
    public void interprete(Contexto ctx) {
        setId(Integer.parseInt(ctx.getNodeList().item(0).getChildNodes().item(0).getNodeValue()));
        setNombre(ctx.getNodeList().item(1).getChildNodes().item(0).getNodeValue());
        setTieneRuta(false);
    }

    @Override
    public Element interpreteToXml(Contexto ctx) {
        Chofer chofer = (Chofer) ctx.getExpresionXml();
        Element choferElemento = ctx.getRoot().createElement("chofer");
        Element id = ctx.getRoot().createElement("id");
        id.appendChild(ctx.getRoot().createTextNode(""+chofer.getId()));
        Element nombre = ctx.getRoot().createElement("nombre");
        nombre.appendChild(ctx.getRoot().createTextNode(chofer.getNombre()));

        choferElemento.appendChild(id);
        choferElemento.appendChild(nombre);

        return choferElemento;
    }

    @Override
    public int compareTo(Object o) {
        Chofer chofer = (Chofer)o;
        if(!chofer.tieneRuta()){
            return 1;
        }
        else if (chofer.tieneRuta()){
            return -1;
        }
        else {
            return 0;
        }
    }
}
