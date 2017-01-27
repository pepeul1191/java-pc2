package pe.edu.ulima.gestion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
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
    
    public Map<Integer, String> cantidadAlimentacion(){
        Map<Integer, String> cantidadAlimentacion = new TreeMap<Integer, String>();
        HashSet<String> alimentaciones = new HashSet<String>();
        
        for(Animal a : animalitos){
            alimentaciones.add(a.getAlimentacion());
        }
        
        Iterator iterator = alimentaciones.iterator(); 
      
        // check values
        while (iterator.hasNext()){
            String alimentacion = (String)iterator.next();
            int cantidad = cantidadSegunAlimentacion(alimentacion);
            
            cantidadAlimentacion.put(cantidad, alimentacion);
        }
        
        return cantidadAlimentacion;
    }
    
    private int cantidadSegunAlimentacion(String alimentacion){
        int cantidad = 0;
        
        for(Animal a : animalitos){
            if(a.getAlimentacion().equalsIgnoreCase(alimentacion)){
                cantidad = cantidad + 1;
            }
        }
    
        return cantidad;
    }
    
    public ArrayList<Animal> listaPorParametro(char caracter){
        HashMap<String,Integer> coincidenciasgetNombreCientifico = new HashMap<String,Integer>();
        caracter = Character.toUpperCase(caracter);
        int mayor = 0;
        ArrayList<Animal> rpta = new ArrayList<Animal>();
        
        for(Animal a : animalitos){
            String nombreCientifico = a.getNombreCientifico();
            int coincidencias = 0;
            
            for(int i = 0; i < nombreCientifico.length(); i++){
                if(nombreCientifico.charAt(i) == caracter){
                    coincidencias = coincidencias + 1;
                }
            }
            
            if (coincidencias > mayor){
                mayor = coincidencias;
            }
            
            coincidenciasgetNombreCientifico.put(nombreCientifico, coincidencias);
        }
        
        System.out.println("MAYOR : " + mayor);
        
        Iterator it = coincidenciasgetNombreCientifico.entrySet().iterator();
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String nombreCientifico = (String)pair.getKey();
            int coindicencias = (Integer)pair.getValue();
            
            if(coindicencias == mayor){
                rpta.add(animalPorNombreCientifico(nombreCientifico));
            }
            
            it.remove(); // avoids a ConcurrentModificationException
        }
        
        return rpta;
    }
    
    private Animal animalPorNombreCientifico(String nombreCientifico){
        Animal rpta = null;
        
        for(Animal a : animalitos){
            if(a.getNombreCientifico().equalsIgnoreCase(nombreCientifico)){
                rpta = a;
            }
        }
        
        return rpta;
    }
}
