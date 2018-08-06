package elektronika;

import java.util.Collection;
import java.util.Objects;

public class Teve implements Comparable<Teve>{
    // 1.
    private String márka;
    private String típus;
    private String tulajdonság[];
    private int ár;
    

    // 1.
    public Teve(String marka, String tipus, Collection<String> tulajdonsag, int ar) {
        this.márka = marka;
        this.típus = tipus;
        this.tulajdonság = tulajdonsag.toArray(new String[0]);
        this.ár = ar;
    }

    // 1.
    public Teve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // 1.
    public String getMárka() {
        return márka;
    }

    // 1.
    public String getTípus() {
        return típus;
    }

    // 1.
    public String[] getTulajdonság() {
        return tulajdonság;
    }

    // 1.
    public int getÁr() {
        return ár;
    }

    // 1.
    public void setMárka(String márka) {
        this.márka = márka;
    }

    // 1.
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.márka);
        hash = 59 * hash + Objects.hashCode(this.típus);
        return hash;
    }

    // 1.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if( !(obj instanceof Teve) ) {
            return false;
        }
        final Teve other = (Teve) obj;
        if (!Objects.equals(this.márka, other.márka)) {
            return false;
        }
        if (!Objects.equals(this.típus, other.típus)) {
            return false;
        }
        return true;
    }

    // 2.
    @Override
    public String toString() {
        String AllSt = márka + " " + típus;
        if (tulajdonság.length > 0){
            AllSt += " (";
            for (int i = 0; i < tulajdonság.length; i++) {
                if(i!=0){
                    AllSt += ", ";
                }
                AllSt += tulajdonság[i];
                
            }
            AllSt += ")";
        }
        AllSt += ", " + ár + " Ft";
        return  AllSt;
    }
    
    // 3.
    @Override
    public int compareTo(Teve o) {
        int kul = this.ár - o.ár;
        if(kul != 0)
            return -kul;
        kul = this.márka.compareTo(o.márka);
        if(kul != 0)
            return kul;
        return this.típus.compareTo(o.típus);
    }

}
