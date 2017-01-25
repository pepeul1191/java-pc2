package pe.edu.ulima.test;

import java.util.ArrayList;
import pe.edu.ulima.beans.Animal;
import pe.edu.ulima.gestion.Gestion;

public class Test {
    public static void main(String[] args) {
        Gestion g = new Gestion();
        
        //Pregunta 1
        
        g.cargar("C:\\Documentos\\PP\\animalitos.txt");
        
        ArrayList<Animal>animalitos = g.lista();
        
        for(Animal a : animalitos){
            if(a != null){
                System.out.println(a);
            }
        }
    }
    
}
