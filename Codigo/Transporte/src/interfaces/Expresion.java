package interfaces;

import Interprete.Contexto;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public interface Expresion {
    void interprete(Contexto ctx);
    Element interpreteToXml(Contexto ctx);
}
