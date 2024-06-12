/*
 * Class: EscText
 * Descripción: Clase encargada de generar un archivo XML con la
 *              estructura de un rating dado.
 * @author: Francisco Javier Mena Barraza
 * @version: 1.0
 * @mail: fmena_barraza@hotmail.com
 */
import java.io.*;

public class EscText2 {

    private String[][] dat;
    private String nombre;
    private int lt;
    private int roam;
    private String res;
    private String parte1 = "";
    private String parte2 = "";
    private String parte3 = "";
    private String parte4 = "";
    private String parte5 = "";
    private String parte6 = "";


    public EscText2(String u1, String u2, String u3, String u4, String u5, String u6,String[][] dat, String nombre, int lt, int roam){
        this.parte1 = u1;
        this.parte2 = u2;
        this.parte3 = u3;
        this.parte4 = u4;
        this.parte5 = u5;
        this.parte6 = u6;
        this.dat = dat;
        this.nombre = nombre;
        this.lt = lt;
        this.res = "";
        this.roam = roam;
    }


    public void init( ){
        System.out.println("Creando archivo xml");
        res = "";
        // Encabezado
        res += parte1;
        // Agregamos los numberlist correspondientes
        for(int i = 0; i<lt; i++){
            res = res + "\t\t\t\t\t\t\t\t<Node>"+dat[i][0]+"\n" +
                    "\t\t\t\t\t\t\t\t\t<Condition>NumberList\n" +
                    "\t\t\t\t\t\t\t\t\t<Type>0</Type>\n" +
                    "\t\t\t\t\t\t\t\t\t<Number>"+dat[i][0]+"</Number>\n" +
                    "\t\t\t\t\t\t\t\t\t</Condition>\n" +
                    "\t\t\t\t\t\t\t\t</Node>\n";
        }
        // Agregamos la parte de los rates

        // primero rate home
        res += parte2;
        // agregamos las marcaciones y su precio
         for(int i = 0; i<lt; i++){
            res = res + "\t\t\t\t\t\t\t\t\t\t<Node>"+dat[i][0]+"\n" + 
                    "\t\t\t\t\t\t\t\t\t\t\t<Tariff>Rate\n" + 
                    "\t\t\t\t\t\t\t\t\t\t\t\t<UnitType>Time</UnitType>\n" + 
                    "\t\t\t\t\t\t\t\t\t\t\t\t<Price>"+dat[i][1]+"\n" + 
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t<Factor>60</Factor>\n" + 
                    "\t\t\t\t\t\t\t\t\t\t\t\t</Price>\n" + 
                    "\t\t\t\t\t\t\t\t\t\t\t\t<Interval>1\n" + 
                    "\t\t\t\t\t\t\t\t\t\t\t\t<Factor>60</Factor>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t</Interval>\n" + 
                    "\t\t\t\t\t\t\t\t\t\t\t\t<UpdateType>Active</UpdateType>\n" + 
                    "\t\t\t\t\t\t\t\t\t\t\t</Tariff>\n" + 
                    "\t\t\t\t\t\t\t\t\t\t\t</Node>\n";
         }
        // segundo roaming
        res += parte3;
        // agregamos las marcaciones y su precio para roaming
         for(int i = 0; i<lt; i++){
                 res = res + "\t\t\t\t\t\t\t\t\t\t<Node>"+dat[i][0]+"\n" +
                         "\t\t\t\t\t\t\t\t\t\t\t<Tariff>Rate\n" +
                         "\t\t\t\t\t\t\t\t\t\t\t\t<UnitType>Time</UnitType>\n";
                double iv =Double.valueOf(dat[i][1]).doubleValue();
                double iv1 = iv + roam;
                if(iv != 0.0){
                    res = res + "\t\t\t\t\t\t\t\t\t\t\t\t<Price>"+iv1+"\n";
                }
                else{
                    res = res + "\t\t\t\t\t\t\t\t\t\t\t\t<Price>0.0\n";
                }
                    res = res + "\t\t\t\t\t\t\t\t\t\t\t\t\t<Factor>60</Factor>\n" +
                         "\t\t\t\t\t\t\t\t\t\t\t\t</Price>\n" +
                         "\t\t\t\t\t\t\t\t\t\t\t\t<Interval>1\n" +
                         "\t\t\t\t\t\t\t\t\t\t\t\t<Factor>60</Factor>\n" +
                         "\t\t\t\t\t\t\t\t\t\t\t\t</Interval>\n" +
                         "\t\t\t\t\t\t\t\t\t\t\t\t<UpdateType>Active</UpdateType>\n" +
                         "\t\t\t\t\t\t\t\t\t\t\t</Tariff>\n" +
                         "\t\t\t\t\t\t\t\t\t\t\t</Node>\n";
         }

        // Agregamos la cabecesra de los subscriptires activos home
        res += parte4;
        // agregamos los datos con los links de marcaciones especiales para home
         for(int i = 0; i<lt; i++){
             res = res + "\t\t\t\t\t\t\t\t\t<Node>"+dat[i][0]+"\n" +
                     "\t\t\t\t\t\t\t\t\t\t<Condition locked=\"true\" target=\"/defs/Number Lists/Special Numbers/"+dat[i][0]+"/NumberList\" type=\"link\"/>\n";
             double iv =Double.valueOf(dat[i][1]).doubleValue();
             if(iv != 0.0){
                 res = res + "\t\t\t\t\t\t\t\t\t\t<Tariff locked=\"true\"  target=\"/defs/Rates/On Region (Home)/MO VAS/UNEFON $30 a $50 por Minuto Unefon 09 (Home)/"+dat[i][0]+"/Rate\" type=\"link\"/>\n";
             }else{
                  res = res + "\t\t\t\t\t\t\t\t\t\t<Tariff id=\"\"  target=\"/defs/Rates/On Region (Home)/MO VAS/UNEFON $30 a $50 por Minuto Unefon 09 (Home)/"+dat[i][0]+"/Rate\" type=\"link\"/>\n";
             }
             res = res + "\t\t\t\t\t\t\t\t\t\t</Node>\n";
         }
        // Agregamos la cabecesra de los subscriptires activos roaming
        res += parte5;
        // agregamos los datos con los links de marcaciones especiales para roaming
        for(int i = 0; i<lt; i++){
            res = res + "\t\t\t\t\t\t\t\t\t<Node>"+dat[i][0]+"\n" +
                    "\t\t\t\t\t\t\t\t\t\t<Condition locked=\"true\" target=\"/defs/Number Lists/Special Numbers/"+dat[i][0]+"/NumberList\" type=\"link\"/>\n";
            double iv =Double.valueOf(dat[i][1]).doubleValue();
             if(iv != 0.0){
                res = res + "\t\t\t\t\t\t\t\t\t\t<Tariff locked=\"true\" target=\"/defs/Rates/Off Region (Roaming)/MO VAS/UNEFON $30 a $50 por Minuto Unefon 09 (Home)/"+dat[i][0]+"/Rate\" type=\"link\"/>\n";
             }else{
                 res = res + "\t\t\t\t\t\t\t\t\t\t<Tariff id=\"\" target=\"/defs/Rates/Off Region (Roaming)/MO VAS/UNEFON $30 a $50 por Minuto Unefon 09 (Home)/"+dat[i][0]+"/Rate\" type=\"link\"/>\n";
             }
            res = res + "\t\t\t\t\t\t\t\t</Node>\n";
        }
        //Agregamos la parte final del archivo
         res += parte6;

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
