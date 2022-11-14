package XmlRead;

import Interprete.*;
import Main.Gestor;
import interfaces.Expresion;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class XmlFiles {
    public void read(){
        try {
            File stocks = new File("Informacion.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stocks);
            doc.getDocumentElement().normalize();
            Contexto ctx = new Contexto();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                ctx.setNodeList(node.getChildNodes());
                Expresion expresion = ctx.getExpression(node.getNodeName());
                expresion.interprete(ctx);
                guardarEnList(expresion);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void guardarEnList(Expresion expresion){
        if(expresion instanceof Flotilla){
            Gestor.setFlotillas((Flotilla) expresion);
        }
        else if(expresion instanceof Ruta){
            Gestor.setRutas((Ruta) expresion);
        }
        else if(expresion instanceof RutaDelBus){
            Gestor.setRutasDeLosBuses((RutaDelBus) expresion);
        }
        else if(expresion instanceof Chofer){
            Gestor.setChoferes((Chofer) expresion);
        }
    }

    public void guardar(Expresion expresion){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            FileInputStream inputXml = new FileInputStream(new File("Informacion.xml"));
            Document doc = db.parse(inputXml);

// add the comment node
            Contexto ctx = new Contexto();
            ctx.setExpresion(expresion);
            ctx.setRoot(doc);
            Element element = expresion.interpreteToXml(ctx);
            doc.getDocumentElement().appendChild(element);

            StringWriter outputXmlStringWriter = new StringWriter();
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
// "xml" + "UTF-8" "include XML declaration" is the default anyway, but let's be explicit
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(outputXmlStringWriter));

// now insert our newline into the string & write an UTF-8 file
            String outputXmlString = outputXmlStringWriter.toString()
                    .replaceFirst("<!--", "\n<!--").replaceFirst("-->", "-->\n");

            FileOutputStream outputXml = new FileOutputStream(new File("Informacion.xml"));
            outputXml.write(outputXmlString.getBytes("UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
