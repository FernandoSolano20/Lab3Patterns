package Interprete;

import interfaces.Expresion;
import interfaces.RutaEstrategia;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Ruta implements Expresion {
    private int id;
    private static int idGeneral = 0;
    private String nombre;
    private String descripcion;
    private int cantidadKilometros;
    private int cantidadParadas;
    private List<RutaEstrategia> rutaDelosBuses;

    public Ruta(String nombre, String descripcion, int cantidadKilometros, int cantidadParadas) {
        idGeneral++;
        setId(idGeneral);
        setNombre(nombre);
        setDescripcion(descripcion);
        setCantidadKilometros(cantidadKilometros);
        setCantidadParadas(cantidadParadas);
        rutaDelosBuses = new ArrayList<>();
    }

    public Ruta() {
        idGeneral++;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadKilometros() {
        return cantidadKilometros;
    }

    public void setCantidadKilometros(int cantidadKilometros) {
        this.cantidadKilometros = cantidadKilometros;
    }

    public int getCantidadParadas() {
        return cantidadParadas;
    }

    public void setCantidadParadas(int cantidadParadas) {
        this.cantidadParadas = cantidadParadas;
    }

    public List<RutaEstrategia> getRutaDelBus() {
        return rutaDelosBuses;
    }

    public String setRutaDelBus(RutaEstrategia rutaDelBus) {
        boolean response = rutaDelBus.establecerEstrategia(this);
        if (response){
            this.rutaDelosBuses.add(rutaDelBus);
            return "A la ruta se le asigno un bus";
        }
        return "No hay mas buses o choferes";
    }

    @Override
    public void interprete(Contexto ctx) {
        setId(Integer.parseInt(ctx.getNodeList().item(0).getChildNodes().item(0).getNodeValue()));
        setNombre(ctx.getNodeList().item(1).getChildNodes().item(0).getNodeValue());
        setDescripcion(ctx.getNodeList().item(2).getChildNodes().item(0).getNodeValue());
        setCantidadKilometros(Integer.parseInt(ctx.getNodeList().item(3).getChildNodes().item(0).getNodeValue()));
        setCantidadParadas(Integer.parseInt(ctx.getNodeList().item(4).getChildNodes().item(0).getNodeValue()));
    }

    @Override
    public Element interpreteToXml(Contexto ctx) {
        Ruta ruta = (Ruta)ctx.getExpresionXml();
        Element rutaElemento = ctx.getRoot().createElement("ruta");
        Element id = ctx.getRoot().createElement("id");
        id.appendChild(ctx.getRoot().createTextNode(""+ruta.getId()));
        Element nombre = ctx.getRoot().createElement("nombre");
        nombre.appendChild(ctx.getRoot().createTextNode(ruta.getNombre()));
        Element descripcion = ctx.getRoot().createElement("descripcion");
        descripcion.appendChild(ctx.getRoot().createTextNode(ruta.getDescripcion()));
        Element cantidadKilometros = ctx.getRoot().createElement("cantidadKilometros");
        cantidadKilometros.appendChild(ctx.getRoot().createTextNode(""+ruta.getCantidadKilometros()));
        Element cantidadParadas = ctx.getRoot().createElement("cantidadParadas");
        cantidadParadas.appendChild(ctx.getRoot().createTextNode(""+ruta.getCantidadParadas()));

        rutaElemento.appendChild(id);
        rutaElemento.appendChild(nombre);
        rutaElemento.appendChild(descripcion);
        rutaElemento.appendChild(cantidadKilometros);
        rutaElemento.appendChild(cantidadParadas);

        return rutaElemento;
    }

    public String mostrarInformacion(){
        String rutas = "";
        if (rutaDelosBuses != null){
            for (RutaEstrategia rE:rutaDelosBuses) {
                rutas += rE.imprimir();
            }
        }

        return "Nombre: " + getNombre() + "\n" +
                "Descripcion: " + getDescripcion() + "\n" +
                "Cantidad de Kilometros: " + getCantidadKilometros() + "\n" +
                "Rutas de los buses \n" + rutas;
    }
}
