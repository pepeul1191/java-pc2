package pe.edu.ulima.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import pe.edu.ulima.beans.Animal;
import pe.edu.ulima.gestion.Gestion;

public class Test {
    public static void main(String[] args) {
        Gestion g = new Gestion();
        
        System.out.println("\nPREGUNTA 1\n");
        
        g.cargar("C:\\Documentos\\PP\\java-pc2\\src\\pe\\edu\\ulima\\gestion\\animalitos.txt");
        ArrayList<Animal>animalitos = g.lista();
        
        for(Animal a : animalitos){
            System.out.println(a);
        }
        
        System.out.println("\nPREGUNTA 2\n");
        
        HashMap<String,Float> aniosPromedio = g.aniosPromedio();
        Iterator it = aniosPromedio.entrySet().iterator();
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
        
        System.out.println("\nPREGUNTA 3\n");
        
        ArrayList<Animal>animalitosNombreCientificoMasLargo = g.animalesNombresCientificosMasLargos();
        
        for(Animal a : animalitosNombreCientificoMasLargo){
            System.out.println(a);
        }
        
        System.out.println("\nPREGUNTA 4\n");
        
        Map<Integer, String> treeMap = g.cantidadAlimentacion();
        
        it = treeMap.keySet().iterator();
        
        while(it.hasNext()){
            int key = (Integer)it.next();
            System.out.println("Cantidad : " + key + " -> Alimentacion : " + treeMap.get(key));
        }
        
        System.out.println("\nPREGUNTA 3 - Nueva\n");
        ArrayList<Animal> animalitosCaracteres = g.listaPorParametro('u');
        
        for(Animal a : animalitosCaracteres){
            System.out.println(a);
        }
    }
}
