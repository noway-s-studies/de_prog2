package verseny;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class TanulmanyiVerseny {
    protected String megnevezes;
    protected String temeTantargy;
    protected LocalDate datum;
    protected LocalTime kezdesIdopont;
    protected LocalTime befejezesIdopont;

    public TanulmanyiVerseny(String megnevezes, String temeTantargy, LocalDate datum, LocalTime kezdesIdopont, LocalTime befejezesIdopont) {
        this.megnevezes = megnevezes;
        this.temeTantargy = temeTantargy;
        this.datum = datum;
        this.kezdesIdopont = kezdesIdopont;
        this.befejezesIdopont = befejezesIdopont;
    }

    public String getMegnevezes() {
        return megnevezes;
    }

    public String getTemeTantargy() {
        return temeTantargy;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public LocalTime getKezdesIdopont() {
        return kezdesIdopont;
    }
    
    public LocalTime getBefejezesIdopont() {
        return befejezesIdopont;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.megnevezes);
        hash = 59 * hash + Objects.hashCode(this.temeTantargy);
        hash = 59 * hash + Objects.hashCode(this.datum);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TanulmanyiVerseny)) {
            return false;
        }
        final TanulmanyiVerseny other = (TanulmanyiVerseny) obj;
        if (!Objects.equals(this.megnevezes, other.megnevezes)) {
            return false;
        }
        if (!Objects.equals(this.temeTantargy, other.temeTantargy)) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return megnevezes + " (" + temeTantargy + "), " + datum + " " + kezdesIdopont + "-" + befejezesIdopont;
    }
    
    public void idoCsere(){
        LocalTime idoCsere;
        if ((kezdesIdopont.getHour()*60 + kezdesIdopont.getMinute()) > (befejezesIdopont.getHour()*60 + befejezesIdopont.getMinute())){
            idoCsere = kezdesIdopont;
            kezdesIdopont = befejezesIdopont;
            befejezesIdopont = idoCsere;
        }
    }
    
    
}
