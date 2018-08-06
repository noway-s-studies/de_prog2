package jatek;
import java.util.Objects;

public class Tarsasjatek implements Comparable<Tarsasjatek>{
    /**  1 */
    protected String nev;
    protected int korhatar;
    protected int min;
    protected int max;
    
    public Tarsasjatek(String nev, int korhatar, int min, int max) throws NegativErtekException {
        if ( korhatar < 0 || min < 0 || max < 0 ){
            throw new NegativErtekException("Negatív érték!");
        }
        this.nev = nev;
        this.korhatar = korhatar;
        this.min = min;
        this.max = max;
    }
    public String getNev() {
        return nev;
    }
    public int getKorhatar() {
        return korhatar;
    }
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
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
        final Tarsasjatek other = (Tarsasjatek) obj;
        if (!Objects.equals(this.nev, other.nev)) {
            return false;
        }
        return true;
    }    
    
    @Override
    public String toString() {
        return nev 
                + (korhatar == 0 ? "" : " (" + korhatar + " éves kórtól)") 
                + ", jatekosok szama: " + (min == 0 ? "" : min) 
                + "-" + (max == 0 ?  "" : max);
    }
    
    /**  4 */
    public void rendbeRakMinMax(){
        if( min != 0 && max != 0 && min > max){
            int x = min;
            min = max;
            max = x;
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.nev);
        return hash;
    }
    @Override
    public int compareTo(Tarsasjatek o) {
        if( korhatar != o.korhatar)
            return o.korhatar - korhatar;
        return nev.compareTo(o.nev);
    } 
}
