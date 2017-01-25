package pe.edu.ulima.gestion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import pe.edu.ulima.beans.Animal;

public class Gestion {
    private ArrayList<Animal> animalitos = new ArrayList<Animal>();
    
    public void cargar(String ruta){
        Scanner sc = null;
        File f = new File(ruta);
        int contador = 0;
        
        try {
            sc = new Scanner(f);
            while(sc.hasNext()){
                String linea = sc.nextLine();
                if(contador != 0){
                    String[] datos = linea.split("::");
                    int id = Integer.parseInt(datos[0]); 
                    String nombre = datos[1];
                    String nombreCientifico = datos[2];
                    int aniosVida = Integer.parseInt(datos[3]);
                    float pesoKg = Float.parseFloat(datos[4]);
                    String habitad = datos[5];
                    String locomocion = datos[6];
                    String alimentacion = datos[7];

                    Animal a = new Animal(id, nombre, nombreCientifico, aniosVida, pesoKg, habitad, locomocion, alimentacion);
                    animalitos.add(a);
                }
                contador = contador + 1;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger("El archivo no existe");
        } finally {
            sc.close();
        }
    }
    
    public ArrayList<Animal> lista(){
        return animalitos;
    }
    
    public HashMap<String,Float> aniosPromedio(){
        HashMap<String,Float> aniosPromedio = new HashMap<String,Float>();
        HashMap<String,Integer> cantidad = new HashMap<String,Integer>();
        HashMap<String,Integer> aniosAcumulados = new HashMap<String,Integer>();
        
        for(Animal a : animalitos){
            String locomocion = a.getLocomocion();
            int aniosVida = a.getAniosVida();
            
            if(cantidad.containsKey(locomocion)){
                int tempCantidad = cantidad.get(locomocion);
                int tempAniosAcumulados = aniosAcumulados.get(locomocion);
                
                cantidad.put(locomocion, tempCantidad + 1);
                aniosAcumulados.put(locomocion, tempAniosAcumulados + aniosVida);
            }else{   
                cantidad.put(locomocion, 1);
                aniosAcumulados.put(locomocion, aniosVida);
            }
        }
        
        Iterator it = cantidad.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String locomocion = (String)pair.getKey();
            int aniosAcumuladosTemp = aniosAcumulados.get(locomocion);
            int cantidadTemp = cantidad.get(locomocion);
            aniosPromedio.put(locomocion, (aniosAcumuladosTemp*1f/cantidadTemp));
            
            it.remove(); // avoids a ConcurrentModificationException
        }
        
        return aniosPromedio;
    }
    
    public ArrayList<Animal> animalesNombresCientificosMasLargos(){
        int longitudNombreCientificoMasLargo = 0;
        ArrayList<Animal> animales = new ArrayList<Animal>();
        
        for(Animal a : animalitos){
            int temp = a.getNombreCientifico().length();
            if(temp > longitudNombreCientificoMasLargo){
                longitudNombreCientificoMasLargo = temp;
            }
        }
        
        for(Animal a : animalitos){
            int temp = a.getNombreCientifico().length();
            if(temp == longitudNombreCientificoMasLargo){
                animales.add(a);
            }
        }
        
        return animales;
    } 
}
