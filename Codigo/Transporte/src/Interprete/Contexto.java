package Interprete;

import Main.Gestor;
import interfaces.Expresion;
import interfaces.RutaEstrategia;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Contexto {
    NodeList nodeList;
    Expresion expresion;
    Document root;

    public Contexto() {
    }

    public void setNodeList(NodeList nodeList) {
        this.nodeList = nodeList;
    }

    public NodeList getNodeList() {
        return nodeList;
    }

    public Expresion getExpresionXml() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    public Expresion getExpression(String name){
        if(name == "flotilla"){
            return  new Flotilla();
        }
        else if(name == "ruta"){
            return new Ruta();
        }
        else if(name == "rutaBus"){
            return  new RutaDelBus();
        }
        else if(name == "chofer"){
            return new Chofer();
        }
        return null;
    }

    public Document getRoot() {
        return root;
    }

    public void setRoot(Document root) {
        this.root = root;
    }
}
