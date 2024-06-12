/*
 * Class: EscText
 * Descripción: Clase encargada de generar un archivo XML con la
 *              estructura de un rating dado.
 * @author: Francisco Javier Mena Barraza
 * @version: 1.0
 * @mail: fmena_barraza@hotmail.com
 */
import java.io.*;

public class EscText {

    private String[][] dat;
    private String nombre;
    private int lt;
    private String res;
    private String parte1 = "";
    private String parte2 = "";
    private String parte3 = "";
    private String parte4 = "";
    private String parte5 = "";


    public EscText(String u1, String u2, String u3, String u4, String u5,String[][] dat, String nombre, int lt){
        this.parte1 = u1;
        this.parte2 = u2;
        this.parte3 = u3;
        this.parte4 = u4;
        this.parte5 = u5;
        this.dat = dat;
        this.nombre = nombre;
        this.lt = lt;
        this.res = "";
    }


    public void init( ){
        System.out.println("Creando archivo xml");
        res = "";
        // Encabezado
        res += parte1 + "\n";
        // Agregamos el nombre del rating
        res = res + "\t<ServiceProvider Name=\"Rating\">\n" +
                "\t\t<RatingPlan Name=\"\" Service=\"Rating\">\n" +
                "\t\t<RatingPeriod Name=\""+this.nombre+"\" RatingStructureFile=\"\" StartDate=\"20100723T014513000\">\n" +
                "\t\t<TariffStructure Name=\""+this.nombre+"\" ServiceId=\"Rating\" TariffType=\"Rating\" id=\"0\" type=\"TariffStructure\">\n";

        // Agregamos los numberlist
        res = res + parte2 + "\n";
        for(int i = 0; i<lt; i++){
            res = res + "\t\t\t\t\t\t\t<Node>"+dat[i][0]+"\n" +
                    "\t\t\t\t\t\t\t\t<Condition>NumberList\n" +
                    "\t\t\t\t\t\t\t\t\t<Type>0</Type>\n" +
                    "\t\t\t\t\t\t\t\t\t<Number>"+dat[i][0]+"</Number>\n" +
                    "\t\t\t\t\t\t\t\t</Condition>\n" +
                    "\t\t\t\t\t\t\t</Node>\n";
        }
        // Agregamos la parte de los rates
        res = res + parte3 + "\n";
        // agregamos las marcaciones y su precio
         for(int i = 0; i<lt; i++){
            res = res + "\t\t\t\t\t\t\t\t<Node>"+dat[i][0]+"\n" +
                    "\t\t\t\t\t\t\t\t\t<Tariff>Fee\n" +
                    "\t\t\t\t\t\t\t\t\t\t<Price>"+dat[i][1]+"</Price>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<UpdateType>Active</UpdateType>\n" +
                    "\t\t\t\t\t\t\t\t\t</Tariff>\n" +
                    "\t\t\t\t\t\t\t\t\t<Tariff>Rate\n" +
                    "\t\t\t\t\t\t\t\t\t\t<UnitType>Time</UnitType>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<Price>0.0\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t<Factor>60</Factor>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t</Price>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<Interval>1\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t<Factor>1</Factor>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t</Interval>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<UpdateType>Active</UpdateType>\n" +
                    "\t\t\t\t\t\t\t\t\t</Tariff>\n" +
                    "\t\t\t\t\t\t\t\t</Node>\n";
         }

        // Agregamos la parte de Activos
        res = res + parte4 + "\n";
        // agregamos los datos con los links de marcaciones especiales para home
         for(int i = 0; i<lt; i++){
             res = res + "\t\t\t\t\t\t\t\t<Node>"+dat[i][0]+"\n" +
                     "\t\t\t\t\t\t\t\t\t<Condition locked=\"true\" target=\"/defs/Number Lists/SMS Special Number List/"+dat[i][0]+"/NumberList\" type=\"link\"/>\n" +
                     "\t\t\t\t\t\t\t\t\t<Node locked=\"true\" target=\"/defs/Rates/On Region (Home)/SMS Number List Rates/"+dat[i][0]+"\" type=\"link\"/>\n" +
                     "\t\t\t\t\t\t\t\t</Node>\n";
         }
        
        // Agregamos la parte final del documento
        res = res + parte5;
         System.out.println("Archivo cargado en memoria");
    }

    /*
     * Metodo que genera el arvhivo xml
     */
    public void escribe(String val){
        System.out.println("Creando archivo");
        try{
            FileWriter fichero =new FileWriter(this.nombre);
            PrintWriter pw = new PrintWriter(fichero);
            pw.print(val);
            fichero.close();
        }catch(Exception e){
            System.out.println(e);
        }
         System.out.println("Archivo creado");
    }

    /*
     * Metodo que devuelve una vista previa del arhivo a imprimir
     */
    public String getRes(){
        return this.res;
    }
}
