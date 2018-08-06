package unideb;

import egyetem.Kurzus;
import egyetem.Tantargy;
import egyetem.TanterviHalo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PTIHalo implements TanterviHalo{

    private String haloNev;
    private Set<Tantargy> tantargy;

    public PTIHalo(String haloNev, Collection<Tantargy> tantargy) {
        this.haloNev = haloNev;
        this.tantargy = new HashSet<>(tantargy);
    }

    @Override
    public String toString() {
        List<Tantargy> lista = new ArrayList<Tantargy>(tantargy);
        Collections.sort(lista);
        String post = haloNev + "\n\n";
        for (Tantargy tantargyak : lista) {
            post += " - " + tantargyak + "\n";
        }
        return post;
    }

    @Override
    public void ujTargy(Tantargy targy) {
        tantargy.add(targy);
    }

    @Override
    public int OsszKredit() {
        int osszKreditSzam = 0;
        for (Tantargy tantargyak : tantargy) {
            osszKreditSzam += tantargyak.getKreditSzam();
            
        }
        return osszKreditSzam;
    }

    @Override
    public List<Kurzus> hosszúkurzusok(int oraszam) {
        List<Kurzus> list = new LinkedList<Kurzus>();
        for (Tantargy kurzus1 : tantargy) {
            if (kurzus1 instanceof Kurzus) {
                if (((Kurzus)kurzus1).getHossz() > oraszam){
                    list.add((Kurzus)kurzus1);
                }
            }
        }
        Collections.sort(list);
        return list;
    }
    static void filter(PTIHalo halok) {
        for (Iterator i = halok.tantargy.iterator(); i.hasNext(); ) 
            if (i.equals(null)) 
                i.remove();
    }
}
