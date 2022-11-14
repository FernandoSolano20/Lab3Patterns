package Main;

import Interprete.Chofer;
import Interprete.Flotilla;
import Interprete.Ruta;
import Interprete.RutaDelBus;
import XmlRead.XmlFiles;

import java.util.ArrayList;
import java.util.List;

public class Gestor {
    private  static XmlFiles xmlFiles = new XmlFiles();
    private static List<Chofer> choferes = new ArrayList<>();
    private static List<Ruta> rutas = new ArrayList<>();
    private static List<Flotilla> flotillas= new ArrayList<>();
    private static List<RutaDelBus> rutasDeLosBuses = new ArrayList<>();

    public static List<Chofer> getChoferes() {
        return choferes;
    }

    public static void crearChofer(Chofer chofer){
        if(buscarChofer(chofer.getId()) == null){
            choferes.add(chofer);
            xmlFiles.guardar(chofer);
        }
    }

    public static Chofer buscarChofer(int id){
        Chofer chofer = null;
        for (Chofer c:choferes) {
            if (c.getId() == id){
                chofer = c;
                break;
            }
        }
        return chofer;
    }

    public static List<Ruta> getRutas() {
        return rutas;
    }

    public static void crearRuta(Ruta ruta){
        if(buscarRuta(ruta.getId()) == null){
            rutas.add(ruta);
            xmlFiles.guardar(ruta);
        }
    }

    private static Ruta buscarRuta(int id){
        Ruta ruta = null;
        for (Ruta r:rutas) {
            if (r.getId() == id){
                ruta = r;
                break;
            }
        }
        return ruta;
    }

    public static Ruta buscarRutaNombre(String nombre){
        Ruta ruta = null;
        for (Ruta r:rutas) {
            if (r.getNombre().equals(nombre)){
                ruta = r;
                break;
            }
        }
        return ruta;
    }

    public static List<Flotilla> getFlotillas() {
        return flotillas;
    }

    public static void crearFlotilla(Flotilla flotilla){
        if(buscarFlotilla(flotilla.getPlaca()) == null){
            flotillas.add(flotilla);
            xmlFiles.guardar(flotilla);
        }
    }

    public static Flotilla buscarFlotilla(String placa){
        Flotilla flotilla = null;
        for (Flotilla f:flotillas) {
            if (f.getPlaca().equals(placa)){
                flotilla = f;
                break;
            }
        }
        return flotilla;
    }

    public static List<RutaDelBus> getRutasDeLosBuses() {
        return rutasDeLosBuses;
    }

    public static boolean hayBusesDisponibles(){
        for (Flotilla bus:flotillas) {
            if(!bus.tieneRuta()){
                return true;
            }
        }
        return false;
    }

    public static boolean hayChoferesDisponibles(){
        for (Chofer chofer:choferes) {
            if(!chofer.tieneRuta()){
                return true;
            }
        }
        return false;
    }

    public static void setChoferes(Chofer chofer) {
        Gestor.choferes.add(chofer);
    }

    public static void setRutas(Ruta ruta) {
        Gestor.rutas.add(ruta);
    }

    public static void setFlotillas(Flotilla flotilla) {
        Gestor.flotillas.add(flotilla);
    }

    public static void setRutasDeLosBuses(RutaDelBus rutasDeLosBus) {
        Gestor.rutasDeLosBuses.add(rutasDeLosBus);
    }
}
