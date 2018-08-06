
package verseny;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class ProgVerseny extends TanulmanyiVerseny implements Comparable<ProgVerseny>{
    
    private Set<String> nyelvek;

    public Set<String> getNyelvek() {
        return nyelvek;
    }

    public ProgVerseny(Set<String> nyelvek, String megnevezes, LocalDate datum, LocalTime kezdesIdopont, LocalTime befejezesIdopont) throws NincsNyelvException {
        super(megnevezes, "informatika", datum, kezdesIdopont, befejezesIdopont);
        if (nyelvek.isEmpty()) {
            throw new NincsNyelvException("Nincs megadva programozási nyelv!");
        }
        this.nyelvek = nyelvek;
    }

    @Override
    public String toString() {
        String nyelvLista = "";
        boolean vesszo = false;
        for (String string : nyelvek) {
            nyelvLista += vesszo ? ", " : " ";
            nyelvLista += string;
            vesszo = true;
        }
        return super.toString() + ", támogatott nyelvek:" + nyelvLista;
    }

    @Override
    public int compareTo(ProgVerseny o) {
        if(nyelvek.size() != o.nyelvek.size())
            return nyelvek.size() - o.nyelvek.size();
        int cmp = (super.datum.getYear() - o.datum.getYear());
        if (cmp == 0) {
            cmp = (super.datum.getMonthValue() - o.datum.getMonthValue());
            if (cmp == 0) {
                cmp = (super.datum.getDayOfMonth() - o.datum.getDayOfMonth());
                if (cmp == 0) {
                    cmp = (super.kezdesIdopont.getHour() - o.kezdesIdopont.getHour());
                    if (cmp == 0) {
                        cmp = (super.kezdesIdopont.getMinute() - o.kezdesIdopont.getMinute());
                    }
                }
            }
        }
        return cmp;
    }
    
    
}
