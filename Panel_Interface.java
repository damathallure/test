/*
 * Class: Panle_Interface
 * Descripcion: Clase encargada de generar una interfaz para el programa
 * encargado de generar el archivo XML de tarifas.
 */

import java.awt.*;

public class Panel_Interface extends Frame{
    MenuBar barra_menu = new MenuBar();
    Menu acerca = new Menu("?", true);
    TextField campo_tarifa = new TextField("", 35);
    TextField campo_nombre = new TextField("", 35);
    TextField campo_xml = new TextField("", 35);
    TextField campo_modo = new TextField("", 35);
    TextField campo_src = new TextField("", 35);
    TextField campo_roam = new TextField("", 35);
    Label estado = new Label("", Label.LEFT);
    Button procesar = new Button("Generar XML");
    Button abrir = new Button ("Abrir Tarifa");
    Button guardar = new Button("Destino XML");
    Button path = new Button("Buscar");


    // METODO QUE CALCULA EL AHORRO TOTAL
    public String generaXML(String entrada, String salida, Integer modo,String src, Integer roam){
        String res = "";
        if(modo == 0){
            estado.setText("SMSValue");
            LeeText tmp = new LeeText(entrada);
            tmp.init();
            String [][] val = tmp.getArreglo();
            String parte1 = tmp.leeg(src+"01Head.xml" );
            String parte2 = tmp.leeg(src+"02NumberlistHead.xml");
            String parte3 = tmp.leeg(src+"03RatesHead.xml");
            String parte4 = tmp.leeg(src+"04ActiveSubscriber.xml");
            String parte5 = tmp.leeg(src+"05EndFile.xml");


            int lt = tmp.getL();

            EscText tmp1 = new EscText(parte1, parte2, parte3, parte4, parte5, val, salida, lt);
            tmp1.init();
            tmp1.escribe(tmp1.getRes());
            res = "Archivo Generado";
        }else{
            estado.setText("Marcaciones Especiales");
            LeeText2 tmp = new LeeText2(entrada);
            tmp.init();
            String [][] val = tmp.getArreglo();
            String parte1 = tmp.leeg(src+"PlantillaME_RatelistHead.xml" );
            String parte2 = tmp.leeg(src+"PlantillaME_TailNLHeadRates.xml");
            String parte3 = tmp.leeg(src+"PlantillaME_tailRatesOR_HeadOFR.xml");
            String parte4 = tmp.leeg(src+"PlantillaME_TailRatesOfr_HeadAcrtiive.xml");
            String parte5 = tmp.leeg(src+"PlantillaME_Active_tailOFR.xml");
            String parte6 = tmp.leeg(src+"PlantillaME_enddocumento.xml");
            int lt = tmp.getL();
            EscText2 tmp1 = new EscText2(parte1, parte2, parte3, parte4, parte5, parte6, val, salida, lt, roam);
            tmp1.init();
            tmp1.escribe(tmp1.getRes());
            res = "Tarifa Creada";
        }
        return res;
    }

    // GENERAMOS EL FRAME
    public Panel_Interface(String title){
        super(title);
        setMenuBar(barra_menu);
        barra_menu.add(acerca);
        acerca.add("Acerca de");
        acerca.add("Ayuda");
        setLayout(new GridLayout(8,3));
        add(new Label("Directorio Path: ", Label.LEFT));
        add(campo_src);
        add(path, Button.CENTER_ALIGNMENT);
        add(new Label("Tarifa a Parsear: ", Label.LEFT));
        add(campo_tarifa);
        add(abrir, Button.CENTER_ALIGNMENT);
        add(new Label("Nombre Rating: ", Label.LEFT));
        add(campo_nombre);
        add(new Label());
        add(new Label("Carpeta Destino: ", Label.LEFT));
        add(campo_xml);
        add(guardar, Button.RIGHT_ALIGNMENT);
        add(new Label("Modo: "));
        add(campo_modo);
        add(new Label());
        add(new Label("Roaming: ", Label.LEFT));
        add(campo_roam);
        add(new Label());
        add(new Label("Estatus: ", Label.LEFT));
        add(estado);
        add(new Label());
        add(new Label());
        add(procesar, Button.CENTER_ALIGNMENT);
        add(new Label());
    }


    // Metodo que inicia todo
    public static void main(String[] argv){
        Panel_Interface aplicacion  = new Panel_Interface("Generador de Tarifas");
       aplicacion.setSize(350, 200);
       aplicacion.setLocation(500, 250);
        //aplicacion.pack();
        aplicacion.setVisible(true);
    }

    // METODO QUE CAPTURA Y MANEJA LOS EVENTOS
    public boolean action(Event evt, Object arg){
        String t_entrada;
        String xml_salida;
        String nombre;
        String estatus;
        int roam;
        String src;
        int modo;

        if(evt.target instanceof MenuItem){
            if(arg.equals("Acerca de")){
                System.out.println("Autor: Francisco Javier Mena\n" +
                        "Version: 1.0\n" +
                        "Mail: fmena_barraza@hotmail.com");
            }
            if(arg.equals("Ayuda")){
                System.out.println("PATH: Archivos donde se encuentran las cabeceras del formato XML\n" +
                        "Modo 1: Genera formato de tarifa XML de SMS Value\n" +
                        "Modo 2: Genera formato de tarifa XML DE Marcaviones espeicales");
            }
        }
        if(evt.target == path){
            //OBTENEMOS LOS DATOS
            FileDialog fd= new FileDialog(this, "Ruta Archivos generadores");
            fd.setVisible(true);
            campo_src.setText(fd.getDirectory());
            
            return true;
        }
        if(evt.target == abrir){
            //OBTENEMOS LOS DATOS
            FileDialog fd= new FileDialog(this, "Abrir Tarifa");
            fd.setVisible(true);
            campo_nombre.setText(fd.getFile());
            campo_tarifa.setText(fd.getDirectory()+ fd.getFile());

            return true;
        }
        if(evt.target == guardar){
            //OBTENEMOS LOS DATOS
            FileDialog fd= new FileDialog(this, "Abrir Tarifa", FileDialog.SAVE);
            fd.setVisible(true);
            campo_xml.setText(fd.getDirectory()+ fd.getFile());
            return true;
        } 
        if(evt.target == procesar){
            //OBTENEMOS LOS DATOS
            t_entrada = campo_tarifa.getText();
            xml_salida = campo_xml.getText();
            nombre = campo_nombre.getText();
            modo = Integer.valueOf(campo_modo.getText()).intValue();
            src = campo_src.getText();
            roam = Integer.valueOf(campo_roam.getText()).intValue();

            // Procesamos los datos
            estatus = generaXML(t_entrada, xml_salida, modo,src, roam);

            //LIMPIAMOS LOS CAMPOS
            campo_tarifa.setText("");
            campo_nombre.setText("");

            // PONEMOS EL RESULTADO EN LA ETIQUETA
            //estado.setText(String.valueOf(estatus));
            return true;
        }
        return false;
    }

    public boolean handleEvent(Event evt){
        if(evt.id == Event.WINDOW_DESTROY){
            System.exit(0);
        }
        return super.handleEvent(evt);
    }
}
