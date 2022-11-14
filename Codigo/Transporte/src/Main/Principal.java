package Main;


import Estrategia.ContextoEstrategia;
import Interprete.Chofer;
import Interprete.Flotilla;
import Interprete.Ruta;
import XmlRead.XmlFiles;
import com.sun.deploy.security.ruleset.RuleAction;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class Principal {

    private static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        XmlFiles xmlFiles = new XmlFiles();
        xmlFiles.read();
        mostrarMenu();
    }

    private static void mostrarMenu() {
        int opc = 0;
        do {
            System.out.println("Digite la opcion que desea");
            System.out.println("1- Registrar flotilla");
            System.out.println("2- Registrar Ruta");
            System.out.println("3- Registrar Chofer");
            System.out.println("4- Asignar de rutas");
            System.out.println("5- Modificar kilometraje de bus");
            System.out.println("6- Mostrar relacion ruta bus");
            System.out.println("7- Salir");
            opc = read.nextInt();
            procesarOpcion(opc);
        }while (opc != 7);
    }

    private static void procesarOpcion(int opc) {
        switch (opc){
            case 1:
                crearFlotilla();
                break;

            case 2:
                crearRuta();
                break;

            case 3:
                crearChofer();
                break;

            case 4:
                asignarRuta();
                break;

            case 5:
                editarKmBus();
                break;

            case 6:
                mostarRutas();
                break;

            case 7:
                System.out.println("Saliendo");
                break;

            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    private static void mostarRutas() {
        List<Ruta> rutas = Gestor.getRutas();
        for (Ruta ruta:rutas) {
            System.out.println(ruta.mostrarInformacion());
        }
    }

    private static void editarKmBus() {
        System.out.println("Digite la placa");
        String placa = read.next();
        Flotilla flotilla = Gestor.buscarFlotilla(placa);
        if(flotilla != null){
            System.out.println("Cantidad de kilometros");
            int km = read.nextInt();
            flotilla.setKilometraje(km);
        }
    }

    private static void asignarRuta() {
        System.out.println("Nombre ruta");
        String nombre = read.next();
        Ruta ruta = Gestor.buscarRutaNombre(nombre);
        if(ruta != null){
            System.out.println("Estrategia");
            System.out.println("1- Aleatoria");
            System.out.println("2- 1 Bus con mucho kilometraje");
            System.out.println("3- 1 Bus con poco kilometraje");
            int opc = read.nextInt();
            ContextoEstrategia ctx = new ContextoEstrategia();
            if(ctx != null){
                System.out.println(ruta.setRutaDelBus(ctx.getStrategy(opc)));
            }
        }
    }

    private static void crearChofer() {
        System.out.println("Id");
        int id = read.nextInt();

        System.out.println("Nombre");
        String nombre = read.next();

        Chofer chofer = new Chofer(id,nombre);
        Gestor.crearChofer(chofer);
    }

    private static void crearRuta() {

        System.out.println("Nombre");
        String nombre = read.next();

        System.out.println("Descripcion");
        String descripcion = read.next();

        System.out.println("Cantidad de kilometros");
        int km = read.nextInt();

        System.out.println("Cantidad de paradas");
        int paradas = read.nextInt();

        Ruta ruta = new Ruta(nombre,descripcion,km,paradas);
        Gestor.crearRuta(ruta);
    }

    private static void crearFlotilla() {
        System.out.println("Digite la placa");
        String placa = read.next();

        System.out.println("Caracteristica");
        String caracteristica = read.next();

        System.out.println("Dia compra");
        int dia = read.nextInt();

        System.out.println("Mes compra");
        int month = read.nextInt();

        System.out.println("Año compra");
        int year = read.nextInt();

        System.out.println("Kilometraje");
        int kilometraje = read.nextInt();

        Flotilla flotilla = new Flotilla(placa,caracteristica, LocalDate.of(year,month,dia),kilometraje);
        Gestor.crearFlotilla(flotilla);
    }


}

/*interface Expression
{
    boolean interpreter(String con);
}

// TerminalExpression class implementing
// the above interface. This interpreter
// just check if the data is same as the
// interpreter data.
class TerminalExpression implements Expression
{
    String data;

    public TerminalExpression(String data)
    {
        this.data = data;
    }

    public boolean interpreter(String con)
    {
        if(con.contains(data))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
// OrExpression class implementing
// the above interface. This interpreter
// just returns the or condition of the
// data is same as the interpreter data.
class OrExpression implements Expression
{
    Expression expr1;
    Expression expr2;

    public OrExpression(Expression expr1, Expression expr2)
    {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
    public boolean interpreter(String con)
    {
        return expr1.interpreter(con) || expr2.interpreter(con);
    }
}

// AndExpression class implementing
// the above interface. This interpreter
// just returns the And condition of the
// data is same as the interpreter data.
class AndExpression implements Expression
{
    Expression expr1;
    Expression expr2;

    public AndExpression(Expression expr1, Expression expr2)
    {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
    public boolean interpreter(String con)
    {
        return expr1.interpreter(con) && expr2.interpreter(con);
    }
}

// Driver class
class Principal
{

    public static void main(String[] args)
    {
        Expression person1 = new TerminalExpression("Kushagra");
        Expression person2 = new TerminalExpression("Lokesh");
        Expression isSingle = new OrExpression(person1, person2);

        Expression vikram = new TerminalExpression("Vikram");
        Expression committed = new TerminalExpression("Committed");
        Expression isCommitted = new AndExpression(vikram, committed);

        System.out.println(isSingle.interpreter("Kushagra"));
        System.out.println(isSingle.interpreter("Lokesh"));
        System.out.println(isSingle.interpreter("Achint"));

        System.out.println(isCommitted.interpreter("Committed, Vikram"));
        System.out.println(isCommitted.interpreter("Single, Vikram"));

    }
}
*/