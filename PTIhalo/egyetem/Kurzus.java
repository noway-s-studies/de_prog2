package egyetem;

import java.time.DayOfWeek;
import static java.time.DayOfWeek.MONDAY;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

public class Kurzus extends Tantargy {
    
    private boolean eaOrGy;
    private String terem;
    private int hossz;
    private DayOfWeek hetNapja;
    private LocalTime oraPerc;

    public boolean isEaOrGy() {
        return eaOrGy;
    }

    public String getTerem() {
        return terem;
    }

    public int getHossz() {
        return hossz;
    }

    public DayOfWeek getHetNapja() {
        return hetNapja;
    }

    public LocalTime getOraPerc() {
        return oraPerc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.hetNapja);
        hash = 19 * hash + Objects.hashCode(this.oraPerc);
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
        final Kurzus other = (Kurzus) obj;
        if (!Objects.equals(this.getKod(), other.getKod())) {
            return false;
        }
        if (this.hetNapja != other.hetNapja) {
            return false;
        }
        if (!Objects.equals(this.oraPerc, other.oraPerc)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() 
                + ", " + hossz + " órás" + (eaOrGy ? " előadás" : " gyakorlat")
                + ", " + terem 
                + " teremben, kezdete: " + hetNapja.getDisplayName(TextStyle.FULL,  new Locale("HU")) 
                + " " + oraPerc;
    }

    public Kurzus(String kod, String nev, int kreditSzam, boolean eaOrGy, String terem, int hossz, DayOfWeek hetNapja, LocalTime oraPerc) throws HibasAdatException {
        super(kod, nev, kreditSzam);
        if (oraPerc.getHour()<0 || oraPerc.getHour()>23 || oraPerc.getMinute()<0 || oraPerc.getMinute()>59) {
            throw new HibasAdatException("Hibás érték!");
        }
        this.eaOrGy = eaOrGy;
        this.terem = terem;
        this.hossz = hossz;
        this.hetNapja = hetNapja;
        this.oraPerc = oraPerc;
    }

    public Kurzus(String kod, String nev, int kreditSzam, boolean eaOrGy) {
        super(kod, nev, kreditSzam);
        this.eaOrGy = eaOrGy;
        this.terem = null;
        this.hossz = 2;
        this.hetNapja = MONDAY;
        this.oraPerc = LocalTime.of(8, 0);
    }
    
    
}
