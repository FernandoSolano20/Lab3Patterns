package Interprete;

import Main.Gestor;
import interfaces.Expresion;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.time.LocalDate;

public class RutaDelBus implements Expresion {
    public static int idGeneral = 0;
    private int id;
    private LocalDate fecha;
    private int idRuta;
    private String placa;
    private int idChofer;

    public RutaDelBus() {
        idGeneral++;
        id = idGeneral;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(int idChofer) {
        this.idChofer = idChofer;
    }

    @Override
    public void interprete(Contexto ctx) {
        setId(Integer.parseInt(ctx.getNodeList().item(0).getChildNodes().item(0).getNodeValue()));
        setFecha(LocalDate.parse(ctx.getNodeList().item(1).getChildNodes().item(0).getNodeValue()));
        setIdRuta(Integer.parseInt(ctx.getNodeList().item(2).getChildNodes().item(0).getNodeValue()));
        setPlaca(ctx.getNodeList().item(3).getChildNodes().item(0).getNodeValue());
        setIdChofer(Integer.parseInt(ctx.getNodeList().item(4).getChildNodes().item(0).getNodeValue()));
    }

    @Override
    public Element interpreteToXml(Contexto ctx) {
        RutaDelBus rutaDelBus = (RutaDelBus)ctx.getExpresionXml();
        Element rutaBus = ctx.getRoot().createElement("rutaBus");
        Element id = ctx.getRoot().createElement("id");
        id.appendChild(ctx.getRoot().createTextNode(""+rutaDelBus.getId()));
        Element fecha = ctx.getRoot().createElement("fecha");
        fecha.appendChild(ctx.getRoot().createTextNode(""+rutaDelBus.getFecha()));
        Element idRuta = ctx.getRoot().createElement("idRuta");
        idRuta.appendChild(ctx.getRoot().createTextNode(""+rutaDelBus.getIdRuta()));
        Element placa = ctx.getRoot().createElement("placa");
        placa.appendChild(ctx.getRoot().createTextNode(""+rutaDelBus.getPlaca()));
        Element idChofer = ctx.getRoot().createElement("idChofer");
        idChofer.appendChild(ctx.getRoot().createTextNode(""+rutaDelBus.getIdChofer()));

        rutaBus.appendChild(id);
        rutaBus.appendChild(fecha);
        rutaBus.appendChild(idRuta);
        rutaBus.appendChild(placa);
        rutaBus.appendChild(idChofer);

        return rutaBus;
    }

    public String imprimir(){
        return  "    Chofer: " + Gestor.buscarChofer(getIdChofer()).getNombre() + "\n" +
                "    Bus: " + Gestor.buscarFlotilla(getPlaca()).getPlaca() + "\n";
    }
}
