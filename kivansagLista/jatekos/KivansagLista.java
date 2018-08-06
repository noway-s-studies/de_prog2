package jatekos;

import jatek.JatekLista;
import jatek.Kartyajatek;
import jatek.Tarsasjatek;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KivansagLista implements JatekLista{
    
    private String tulajdonos;
    private Set<Tarsasjatek> jatekok;
    
    public KivansagLista(String tulajdonos, Collection<Tarsasjatek> jatekok) {
        this.tulajdonos = tulajdonos;
        this.jatekok = new HashSet<>(jatekok);
    }
    
    @Override
    public void felvesz(Tarsasjatek jatek) {
        jatekok.add(jatek);
    }
    
    @Override
    public List<Kartyajatek> kartyajatekok(String paklifajta) {
        List<Kartyajatek> lista = new ArrayList<>();
        for (Tarsasjatek jatek : jatekok)
            if( jatek instanceof Kartyajatek && ((Kartyajatek)jatek).getPaklifajta().equals(paklifajta))
                lista.add((Kartyajatek)jatek);
        Collections.sort(lista);
        return lista;
    }
    
    @Override
    public List<Tarsasjatek> megfeleloJatekok(int eletkor) {
        List<Tarsasjatek> lista = new ArrayList<>();
        for (Tarsasjatek jatek : jatekok)
            if(jatek.getKorhatar() <= eletkor)
                lista.add(jatek);
        Collections.sort(lista);
        return lista;
    }
    
    @Override
    public void kiir(String fajlnev) {
        try (PrintStream ps = new PrintStream(fajlnev)){
            for(Tarsasjatek jatek : jatekok)
                ps.println( jatek );
        }
        catch (FileNotFoundException ex) {
            System.err.println("Nem sikerult megnyitni az allomanyt!");
        }
    }
    
    public List<String> adottSzamuJatekosAltalJatszhatoKartyajatekok(int darabszam){
                List<String> nevlista = new ArrayList<>();
                for(Tarsasjatek jatek : jatekok)
                    if( jatek instanceof Kartyajatek
                        && jatek.getMin() <= darabszam 
                        && jatek.getMax() >= darabszam)
                        nevlista.add(jatek.getNev());
                return nevlista;
            }
    
    /**  5 */
    @Override
    public String toString() {
        String vissza = tulajdonos + "\n\n";
        for (Tarsasjatek jatek : jatekok)
            vissza += jatek + "\n";
        return vissza;
    }
}