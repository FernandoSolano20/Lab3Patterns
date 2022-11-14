package Estrategia;

import interfaces.RutaEstrategia;

public class ContextoEstrategia {
    private RutaEstrategia rutaEstrategia;

    public ContextoEstrategia() {
    }

    public RutaEstrategia getStrategy(int opc) {
        if (opc == 1) {
            rutaEstrategia = new Aleatoria();
        } else if (opc == 2) {
            rutaEstrategia = new BusMayorKilometraje();
        } else if (opc == 3){
            rutaEstrategia = new BusMenorKilometraje();
        }
        return rutaEstrategia;
    }
}
