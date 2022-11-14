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

public class BusMenorKilometraje extends RutaDelBus implements RutaEstrategia {
    public BusMenorKilometraje() {
        super();
    }

    @Override
    public boolean establecerEstrategia(Ruta ruta) {
        List<Flotilla> flotillas = Gestor.getFlotillas();
        List<Chofer> choferes = Gestor.getChoferes();
        setIdRuta(ruta.getId());
        setFecha(LocalDate.now());
        Flotilla flotilla = null;
        Chofer chofer = null;
        boolean hayBuses = Gestor.hayBusesDisponibles();
        boolean hayChoferes = Gestor.hayBusesDisponibles();
        if(hayBuses && hayChoferes){
            Collections.sort(flotillas);
            Collections.sort(choferes);
            for(int i = 0; i < flotillas.size(); i++){
                flotilla = flotillas.get(i);
                chofer = choferes.get(0);
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
