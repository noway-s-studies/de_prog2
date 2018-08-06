package botanika;

import java.util.Collection;

public class Zoldseg extends Noveny{
    
    public Zoldseg(String megnevezes, Collection<String> szin) throws NincsSzinException {
        super(megnevezes, szin, true);
    }

    @Override
    public String toString() {
        String szinek = super.getSzin().length > 1 ? "többszínű" : "egyszínű";
        String ehetok;
        if (super.isEheto()) {
            ehetok = "ehető";
        }
        else { 
            ehetok = "nem ehető";
        }
        return super.getMegnevezes() + " zöldség (" + szinek + "), " + ehetok;
    }
    
}
