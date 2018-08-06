package jatek;

public class Kartyajatek extends Tarsasjatek{    
    /**  3 */
    private String paklifajta;
    private int paklimeret;
    
    public String getPaklifajta() {
        return paklifajta;
    }
    public int getPaklimeret() {
        return paklimeret;
    }
    public Kartyajatek(String paklifajta, int paklimeret, String nev, int korhatar, int min, int max) throws NegativErtekException {
        super(nev, korhatar, min, max);
        this.paklifajta = paklifajta;
        this.paklimeret = paklimeret;
    }
    @Override
    public String toString() {
        return super.toString() + ", " + paklimeret + " lapos " + paklifajta + " kartya";
    }
}
