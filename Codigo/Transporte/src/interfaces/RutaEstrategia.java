package interfaces;

import Interprete.Ruta;
import Interprete.RutaDelBus;

public interface RutaEstrategia {
    boolean establecerEstrategia(Ruta ruta);
    String imprimir();
}
