package botanika;

import java.util.Collection;

public class Gyumolcs extends Noveny{
    
    public Gyumolcs(String megnevezes, Collection<String> szin) throws NincsSzinException {
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
        return super.getMegnevezes() + " gyümölcs (" + szinek + "), " + ehetok;
    }
    
}
