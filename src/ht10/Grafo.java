/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ht10;

import java.util.Vector;
import java.util.Collections;
/**
 *
 * @author Jonathan Espinoza
 */
public class Grafo {
    private int[][] distance;
    private int[][] routes;
    private Vector<String> city;
   
    /**
     * 
     */
    public Grafo(){
        city = new Vector<String>();
        distance = new int [30][30];
        routes = new int [30][30];

        for(int i=0;i<30;i++){
            for(int j=0;j<30;j++){
                if(i==j){
                    distance[i][j]=0;
                }
                else{
                    distance[i][j]=100000;
                }

                routes[i][j] = 100000;
                
            }
        }
    }
    /**
     * 
     * @param city1
     * @return
     */
    public boolean cityIn(String city1){
        return city.contains(city1);
    }
    /**
     * 
     * @param city1
     * @param city2
     * @return
     */
    public boolean cityIn(String city1, String city2){
        boolean exist = false;
        if(city.contains(city1) && city.contains(city2)){
            exist = true;
        }
        return exist;
    }
    /**
     * 
     * @return
     */
    public String centerAlgorithm(){
        Vector<Integer> center = new Vector<Integer>(); 
        int maxCost;
        for (int i = 0; i < city.size(); i++){
            maxCost = 0;
            for (int j = 0; j < city.size(); j++)
            {
                if(distance[i][j] > maxCost){
                    maxCost = distance[i][j];
                }
            }
            center.add(maxCost);
        }
        
        int minEccs = Collections.min(center);
        int index = center.indexOf(minEccs);
        
        return ("El centro del grafo es: "+city.elementAt(index)); 
    }
    /**
     * 
     */
    public void floydAlgorithm(){
        for (int k = 0; k<city.size(); k++)
        {
            for (int i = 0; i<city.size(); i++)
            {
                for (int j = 0; j<city.size(); j++)
                {
                    if (distance[i][k] == 100000 || distance[k][j] == 100000)
                        continue;
                    
                    if (distance[i][j] > (distance[i][k] + distance[k][j])){
                            distance[i][j] = distance[k][j] + distance[i][k];
                            routes[i][j] = routes[i][k];
                        }
                }
            }
        }
    }
    /**
     * 
     * @param c1
     * @param c2
     * @return
     */
    public String findRouteString(String c1, String c2) {
        int i = city.indexOf(c1);
        int j = city.indexOf(c2);
        int k = 0;
        
        System.out.println(i);
        System.out.println(j);
        
        Vector<String> finalRoute = new Vector<String>();
        String ret = " ";
        finalRoute.add("De "+city.get(i));

        
        if (distance[i][j] == 100000 || distance[i][j] == 0){
            ret = ("No es posible calcular la ruta, intenta de nuevo."); 
        }else{
            while (i != j)
            {
                if(k > 5){
                    i = j;
                    ret = ("No es posible calcular la ruta, intenta de nuevo."); 
                }
                else{
                    i = routes[i][j];
                    k += 1;
                    finalRoute.add(city.get(i));
                }
                
            }
        }
       
        int n = finalRoute.size();
        if(k >= 5){
            ret = "No es posible calcular la ruta, espera a que se despeje la ruta";
        }
        else{
            for(int l = 0; l < n - 1; l++){
            
                ret += (finalRoute.get(l) + " a ");
                ret += (finalRoute.get(n - 1) + "\n");
            }
        }

        return ret;
    } 
    /**
     * 
     * @param city1
     * @param city2
     * @param cant
     */
    public void add(String city1, String city2, String cant) {

        if(!city.contains(city1)){
            city.add(city1); 
        }
        if(!city.contains(city2)){
            city.add(city2);
        }
        int i = city.indexOf(city1);
        int j = city.indexOf(city2);        
        distance[i][j]= Integer.parseInt(cant);
     
        if(cant == "100000"){
            routes[i][j] = 100000;
        }else{
            routes[i][j] = j;
        }   
    }
    /**
     * 
     * @param city1
     * @param city2
     * @return
     */
    public int getDistance(String city1, String city2){
        return distance[city.indexOf(city1)][city.indexOf(city2)];
    }

    

    
}
