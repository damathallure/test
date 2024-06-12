/*
 * Class: Lee
 * Descripción: Clase encargada de parsear un archivo XML
 *              para capturar los datos de las marcaciones
 *              especiales y posteriormente procesar los datos.
 * @version: 1.0
 * @author: Francisco Javier Mena Barraza
 * @mail: fmena_barraza@hotmail.com
 */

import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class LeeText {
    private String archivo;
    private String[][] res;
    int longitud;
    
    public LeeText(String archivo){
        this.archivo = archivo;
        res = new String[1][2];
        longitud = 0;
    }


    /*
     * Método encargado de leer un archivo de texto y guardar la
     * estructura dentro de un vector para posteriormente
     * pasar esos datos a un archivo XML.
     */
    public void init( ){
        try{
            FileReader fr = new FileReader(this.archivo);
            BufferedReader entrada = new BufferedReader(fr);
            String s;

            while((s = entrada.readLine()) != null){
                String[] val = s.split("\t");
                res = agrega(val, longitud);
                longitud += 1;
            }
        }
        catch(Exception E){
            System.out.println(E);
        }
    }

    /*
     * Metodo que lee y devuelve una cadena de texto con la información leida
     */
    public String leeg(String nombre ){
        System.out.println("Leyendo archivo: " +nombre);
        String texto = "";
        try{
            FileReader fr = new FileReader(nombre);
            BufferedReader entrada = new BufferedReader(fr);
            String s;

            while((s = entrada.readLine()) != null){
                texto += s + "\n";
            }
            fr.close();
        }
        catch(Exception E){
            System.out.println(E);
        }
        System.out.println("Lectura finalizada");
        return texto;
    }


    /*
     * Método encargado de simular un arreglo dinamico
     * para agregar datos a un vector dado.
     * @param dat String[] : Valores a agregar
     * @param val int : Contador de posición
     */
    public String[][] agrega(String[] dat, int val){
        String[][] TmpRes = new String[val+1][2];
        
        for(int i = 0; i < val; i++){
            TmpRes[i][0] = res[i][0];
            TmpRes[i][1] = res[i][1];
        }

        TmpRes[val][0] = dat[0];
        TmpRes[val][1] = dat[1];
        return TmpRes;
    }


    /*
     * Método encargado de imprimir el contenido del arreglo
     */
    public void toString(String[][] arreglo, int val){
        for(int i = 0; i < val; i++){
            System.out.print("Dato1 " + arreglo[i][0] + " Dato2: " );
            System.out.println(arreglo[i][1]);
        } 
    }


    /*
     * Método encargado de proporcionar al usuario
     * el arreglo generado.
     */
    public String[][] getArreglo(){
        return res;
    }

    public int getL( ){
        return longitud;
    }
}
