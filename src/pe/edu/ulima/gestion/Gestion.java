package pe.edu.ulima.gestion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
}
