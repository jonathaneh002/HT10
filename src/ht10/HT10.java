/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht10;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonathan Espinoza
 * @version 31-5-2021
 */
public class HT10 {
    private static JFileChooser file = new JFileChooser();
    private static File archivo;
    private static BufferedReader bf;
    private static FileReader fReader;
    private static Grafo graph;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
       boolean hasFile = false; 
       boolean keep = true;
       graph = new Grafo();
       
        while(hasFile == false){
            System.out.println("Escoja el grafo representado en .txt:");
                file.setApproveButtonText("Guardar");
                int r = file.showSaveDialog(null);
                String path = file.getSelectedFile().getAbsolutePath();

                if (r == JFileChooser.APPROVE_OPTION) {
                    try{
                        String nextLine;
                        archivo = new File(path);
                        fReader = new FileReader(archivo);
                        bf = new BufferedReader(fReader);
                        
                        while((nextLine = bf.readLine())!= null){
                            String[] line = nextLine.split(" ");
                            graph.add(line[0], line[1], line[2]);
                        }
                        graph.floydAlgorithm();
                        hasFile = true;
                    }
                    catch(Exception e){
                       System.err.println("Ha ocurrido un error, intenta de nuevo ");
                    }
                         
                     }
                else if(r == JFileChooser.CANCEL_OPTION){
                    JOptionPane.showMessageDialog(null, "Debes escoger un archivo!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Debes escoger un archivo!");
                }
       }
       
        while (keep == true){
            graph.floydAlgorithm();
            String c1;
            String c2;
            String res = " ";
            System.out.print("-----------------Calculador de Rutas-----------------"
                        +"\n 1. Ruta mas corta entre dos ciudades."+
                        "\n 2. Ciudad en el centro del grafo."+
                        "\n 3. Alertar actualizacion en rutas."+
                        "\n 4. Salir "+
                        "\n----------------------------------------\n");
            String ingreso = JOptionPane.showInputDialog("Ingrese la opcion del menu que desee vizualizar: "); 
            
            switch(ingreso){
                case "1":
                    c1 = JOptionPane.showInputDialog("Ingrese el nombre de la ciudad de salida: ");
                    c2 = JOptionPane.showInputDialog("Ingrese el nombre del destino: ");    
                    
                    if(graph.cityIn(c1, c2) == true && c1 != c2){
                        System.out.println("1No es posible calcular esta ruta!");
                        int res1 = graph.getDistance(c1, c2);
                       
                        res += "La ruta más corta entre " + c1 + " y " + c2 + " es de: " + String.valueOf(res1) + "\n";

                        res += "Ruta: " + graph.findRouteString(c1, c2);

                        System.out.println(res);
                    }
                    else{
                        System.out.println("No es posible calcular esta ruta!");
                    }
                    
                    break;
                case "2":
                    try{
                        res = graph.centerAlgorithm();
                        System.out.println(res);
                    }
                    catch(Exception e){
                        System.out.println("Hubo un error, intenta de nuevo!");
                    }
                   
                    break;
                case "3":
                    System.out.print("-----------------Calculador de Rutas-----------------"
                        +"\n 1. Interrupcion en ruta"+
                        "\n 2. Nueva ruta"+
                        "\n 3. Salir "+
                        "\n----------------------------------------\n");
                    String ingreso2 = JOptionPane.showInputDialog("Ingrese la opcion del menu que desee vizualizar: "); 
                    c1 = JOptionPane.showInputDialog("Ingrese el nombre de la ciudad de salida: ");
                    c2 = JOptionPane.showInputDialog("Ingrese el nombre del destino: ");
                    
                    switch(ingreso2){
                        case("1"):
                            if(graph.cityIn(c1,c2)){
                                graph.add(c1, c2, "100000");
                                System.out.println("Ruta actualizada!"); 
                            }else{
                               System.out.println("No es posible actualizar esta ruta, intenta de nuevo!"); 
                            }
                            break;
                        case "2":
                            String dis = JOptionPane.showInputDialog("Ingrese la distancia de la nueva ruta: ");
                            graph.add(c1, c2, dis);
                            System.out.println("Ruta agregada exitosamente!");
                            break;
                        case "3":
                            System.exit(0);
                    }
                    break;
                case "4":
                    System.exit(0);
            }
       }
        
    }
    
}
