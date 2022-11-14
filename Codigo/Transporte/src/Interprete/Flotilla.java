package Interprete;

import interfaces.Expresion;
import interfaces.Observador;
import interfaces.Sujeto;
import Observador.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Flotilla implements Expresion, Sujeto, Comparable {
    private String placa;
    private String caracteristica;
    private LocalDate fechaCompra;
    private int kilometraje;
    private int revisionKm;
    private boolean tieneRuta;
    private List<Observador> observers = new ArrayList<Observador>();

    public Flotilla(String placa, String caracteristica, LocalDate fechaCompra, int kilometraje) {
        addObserver(new Observado());
        setPlaca(placa);
        setCaracteristica(caracteristica);
        setFechaCompra(fechaCompra);
        setRevisionKm(5000);
        setKilometraje(kilometraje);
        setTieneRuta(false);
    }

    public Flotilla() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
        notifyObservers();
    }

    public int getRevisionKm() {
        return revisionKm;
    }

    public void setRevisionKm(int revisionKm) {
        this.revisionKm = revisionKm;
    }

    public boolean tieneRuta() {
        return tieneRuta;
    }

    public void setTieneRuta(boolean tieneRuta) {
        this.tieneRuta = tieneRuta;
    }

    @Override
    public void interprete(Contexto ctx) {
        setPlaca(ctx.getNodeList().item(0).getChildNodes().item(0).getNodeValue());
        setCaracteristica(ctx.getNodeList().item(1).getChildNodes().item(0).getNodeValue());
        setFechaCompra(LocalDate.parse(ctx.getNodeList().item(2).getChildNodes().item(0).getNodeValue()));
        setKilometraje(Integer.parseInt(ctx.getNodeList().item(3).getChildNodes().item(0).getNodeValue()));
        setRevisionKm(5000);
        setTieneRuta(false);
        addObserver(new Observado());
    }

    @Override
    public Element interpreteToXml(Contexto ctx) {
        Flotilla flotilla = (Flotilla) ctx.getExpresionXml();
        Element flotillaElemento = ctx.getRoot().createElement("flotilla");
        Element placa = ctx.getRoot().createElement("placa");
        placa.appendChild(ctx.getRoot().createTextNode(""+flotilla.getPlaca()));
        Element caracteristica = ctx.getRoot().createElement("caracteristica");
        caracteristica.appendChild(ctx.getRoot().createTextNode(flotilla.getCaracteristica()));
        Element fechaCompra = ctx.getRoot().createElement("fechaCompra");
        fechaCompra.appendChild(ctx.getRoot().createTextNode(""+flotilla.getFechaCompra()));
        Element kilometraje = ctx.getRoot().createElement("kilometraje");
        kilometraje.appendChild(ctx.getRoot().createTextNode(""+flotilla.getKilometraje()));

        flotillaElemento.appendChild(placa);
        flotillaElemento.appendChild(caracteristica);
        flotillaElemento.appendChild(fechaCompra);
        flotillaElemento.appendChild(kilometraje);

        return flotillaElemento;
    }

    @Override
    public void addObserver(Observador o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for(Observador o : observers){
            revisionKm = o.update(this.getKilometraje(),getRevisionKm());
        }
    }

    @Override
    public int compareTo(Object o) {
        Flotilla f = (Flotilla) o;
        if(this.getKilometraje() < f.getKilometraje()){
            return 1;
        }
        else if (this.getKilometraje() > f.getKilometraje()){
            return -1;
        }
        else {
            return 0;
        }
    }
}
