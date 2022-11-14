package Estrategia;

import Interprete.Chofer;
import Interprete.Flotilla;
import Interprete.Ruta;
import Interprete.RutaDelBus;
import Main.Gestor;
import XmlRead.XmlFiles;
import interfaces.RutaEstrategia;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Aleatoria extends RutaDelBus implements RutaEstrategia {
    public Aleatoria() {
        super();
    }

    @Override
    public boolean establecerEstrategia(Ruta ruta) {
        List<Flotilla> flotillas = Gestor.getFlotillas();
        List<Chofer> choferes = Gestor.getChoferes();
        Random random = new Random();
        setIdRuta(ruta.getId());
        setFecha(LocalDate.now());
        Flotilla flotilla = null;
        Chofer chofer = null;
        boolean hayBuses = Gestor.hayBusesDisponibles();
        boolean hayChoferes = Gestor.hayBusesDisponibles();
        if(hayBuses && hayChoferes){
            do {
                int rand = random.nextInt(flotillas.size()) + 0;
                flotilla = flotillas.get(rand);
                rand = random.nextInt(choferes.size()) + 0;
                chofer = choferes.get(rand);
            } while (flotilla.tieneRuta() || chofer.tieneRuta());

            setPlaca(flotilla.getPlaca());
            setIdChofer(chofer.getId());
            flotilla.setTieneRuta(true);
            chofer.setTieneRuta(true);
            XmlFiles xmlFiles = new XmlFiles();
            xmlFiles.guardar(this);
            return true;
        }
        return false;
    }

    @Override
    public String imprimir() {
        return "    Estrategia aleatoria \n" + super.imprimir();
    }
}
