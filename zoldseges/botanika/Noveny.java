package botanika;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class Noveny implements Comparable<Noveny>{
    private String megnevezes;
    private String szin[];
    private boolean eheto;

    public Noveny(String megnevezes,  Collection<String> szin, boolean eheto) throws NincsSzinException {
        if ( szin == null || szin.isEmpty()){
            throw new NincsSzinException("Nincs szín!");
        }
        this.megnevezes = megnevezes;
        this.szin = szin.toArray(new String[0]);
        this.eheto = eheto;
    }

    public String getMegnevezes() {
        return megnevezes;
    }

    public String[] getSzin() {
        return szin;
    }

    public boolean isEheto() {
        return eheto;
    }

    public void setMegnevezes(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.megnevezes);
        hash = 37 * hash + Arrays.deepHashCode(this.szin);
        hash = 37 * hash + (this.eheto ? 1 : 0);
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Noveny other = (Noveny) obj;
        if (!Objects.equals(this.megnevezes, other.megnevezes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String szinek = szin.length > 1 ? "többszínű" : "egyszínű";
        String ehetok = eheto ? "ehető" : "nem ehető";
        return megnevezes + " (" + szinek + "), " + ehetok;
    }

    @Override
    public int compareTo(Noveny o) {        
        if(this.eheto != o.eheto){
            if(o.eheto){
                return +1;
            } else {
                return -1;
            }
        }
        return this.megnevezes.compareTo(o.megnevezes);
    }
    
}
