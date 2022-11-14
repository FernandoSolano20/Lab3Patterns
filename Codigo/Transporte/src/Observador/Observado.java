package Observador;

import interfaces.Observador;

import java.io.Serializable;

public class Observado implements Observador {
    @Override
    public int update(int value, int km) {
        return mostrarRevision(value, km);
    }

    private int mostrarRevision(int value, int km) {
        int kmRecorridos = (value / 5000) * 5000;

        if(km <= value){
            if(kmRecorridos == 25000 || kmRecorridos == 50000){
                System.out.println("Revision urgente de kilometraje, ya que ha llegado a: " + kmRecorridos + " km");
            }
            else {
                System.out.println("Se debe hacer una revision al bus ya que llego a: " + value + " km");
            }
        }
        return kmRecorridos + 5000;
    }
}
