package interfaces;

public interface Sujeto {
    void addObserver(Observador o);
    void notifyObservers();
}
