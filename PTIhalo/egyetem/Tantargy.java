package egyetem;

import java.util.Objects;

public class Tantargy implements Comparable<Tantargy>{
    private String kod;
    private String nev;
    private int kreditSzam;

    public Tantargy(String kod, String nev, int kreditSzam) {
        this.kod = kod;
        this.nev = nev;
        this.kreditSzam = kreditSzam;
    }

    public String getKod() {
        return kod;
    }

    public String getNev() {
        return nev;
    }

    public int getKreditSzam() {
        return kreditSzam;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.kod);
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
        final Tantargy other = (Tantargy) obj;
        if (!Objects.equals(this.kod, other.kod)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return kod + " " + nev + " (" + kreditSzam + " kredites)";
    }

    @Override
    public int compareTo(Tantargy o) {
        if (this.kreditSzam != o.kreditSzam) {
            if(this.kreditSzam > o.kreditSzam){
                return -1;
            } else {
                return +1;
            }
        }
        int kul = this.nev.compareTo(o.nev);
        if(kul != 0)
            return kul;
        return this.kod.compareTo(o.kod);
        
    }
}
