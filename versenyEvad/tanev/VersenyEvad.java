package tanev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import verseny.ProgVerseny;
import verseny.TanulmanyiVerseny;
import verseny.VersenyLista;

public class VersenyEvad implements VersenyLista{
    
    private String tanev;
    private Set<TanulmanyiVerseny> versenyek;

    public VersenyEvad(String tanev, Collection<TanulmanyiVerseny> versenyek) {
        this.tanev = tanev;
        this.versenyek = new HashSet<TanulmanyiVerseny>(versenyek);
    }

    @Override
    public String toString() {
        String lista = "\n";
        for (TanulmanyiVerseny tanulmanyiVerseny : versenyek) {
            lista += "\n" + tanulmanyiVerseny;
        }
        return tanev + lista;
    }

    @Override
    public void felvesz(TanulmanyiVerseny verseny) {
        versenyek.add(verseny);
    }

    @Override
    public List<ProgVerseny> versenyek(String nyelv) {
        List<ProgVerseny> lista = new ArrayList<>();
        for (TanulmanyiVerseny tanulmanyiVerseny : versenyek) {
            if (tanulmanyiVerseny instanceof ProgVerseny) {
                for (String string : ((ProgVerseny) tanulmanyiVerseny).getNyelvek()) {
                    if (string.equals(nyelv)) {
                        lista.add((ProgVerseny) tanulmanyiVerseny);
                    }
                }
            }
        }
        Collections.sort(lista);
        return lista;
    }

    @Override
    public List<ProgVerseny> versenyek(String[] nyelvek) {
        List<ProgVerseny> lista = new ArrayList<>();
        for (TanulmanyiVerseny tanulmanyiVerseny : versenyek) {
            int i=0;
            if (tanulmanyiVerseny instanceof ProgVerseny) {
                for (String string : ((ProgVerseny) tanulmanyiVerseny).getNyelvek()) {
                    for (String string1 : nyelvek) {
                        if (string.equals(string1)) {
                            i++;
                        }
                    }
                }
            }
            if(nyelvek.length == i)
                lista.add((ProgVerseny)tanulmanyiVerseny);
        }
        Collections.sort(lista);
        return lista;
        
    }
    
}
