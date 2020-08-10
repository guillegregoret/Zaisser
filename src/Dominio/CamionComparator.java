package Dominio;

import java.util.Comparator;

public class CamionComparator implements Comparator<Camion>{
	 public int compare(Camion c1, Camion c2) { 
         if (c1.getKm_recorridos() < c2.getKm_recorridos()) 
             return 1; 
         else if (c1.getKm_recorridos() > c2.getKm_recorridos()) 
             return -1; 
                         return 0; 
         } 
}
