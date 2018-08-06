package teszt;
import jatek.JatekLista;
import jatek.Kartyajatek;
import jatek.NegativErtekException;
import jatek.Tarsasjatek;
import jatekos.KivansagLista;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class JatekTeszt {
    public static void main(String[] args) throws FileNotFoundException, NegativErtekException, IllegalAccessException {
/*      Tarsasjatek tj = new Tarsasjatek("Gazdalkodj okosan!", 10, 2, 6);
        Tarsasjatek tj2 = new Tarsasjatek("Puzzle 15 db-os", 0, 0, 0);
        Kartyajatek kj = new Kartyajatek("francia", 110, "Kanaszta", 10, 4, 4);
        System.out.println(tj);
        System.out.println(tj2);
        System.out.println(kj);
*/

        try ( Scanner sc = new Scanner(new File(args [0]))){
            List<Tarsasjatek> lista = new ArrayList<>();  
            while(sc.hasNextLine()){
                Scanner sor = new Scanner( sc.nextLine() );
                sor.useDelimiter(";");
                boolean kartyajatek = sor.next().equals( "K" );
                String nev = sor.next();
                int korhatar = sor.nextInt();
                int min = sor.nextInt();
                int max = sor.nextInt();
                try{
                    if (kartyajatek){
                        String paklifajta = sor.next();
                        int paklimeret = sor.nextInt();
                        lista.add(new Kartyajatek(paklifajta, paklimeret, nev, korhatar, min, max));
                    }
                    else{
                        lista.add(new Tarsasjatek(nev, korhatar, min, max));
                    }
                }
                catch(NegativErtekException ex){
                    System.err.println(ex.getMessage());
                }
            }
            for( Tarsasjatek jatek : lista)
                jatek.rendbeRakMinMax();
            
            System.out.println("---------------------------------------------");
            System.out.println("Beolvasott adatok: ");
            for(Tarsasjatek jatek : lista)
                System.out.println(jatek);
            
            System.out.println("---------------------------------------------");
            KivansagLista kl = new KivansagLista(args.length > 1 ? args[1] : "Petike", lista);
            Scanner be = new Scanner(System.in);
            System.out.print("Kérek egy paklifajtát: ");
            for (Kartyajatek k : kl.kartyajatekok(be.next()))
                System.out.println(k);
            
            System.out.println("---------------------------------------------");
            System.out.println("Bárki által játszható: ");
            kiirBarkiAltalJatszhatok(lista.toArray(new Tarsasjatek[ 0 ]));
            
            System.out.println("---------------------------------------------");
            System.out.println("32 lapos Magyar kártyajátékokkal bővítve: ");
            felvesz32LaposMagyarKartyajatokok(kl, lista);
            System.out.println(kl);
            
            System.out.println("---------------------------------------------");
            System.out.print("Kérek egy játékos darabszámot: ");
            
            try {
                int i = be.nextInt();
                System.out.println(kl.adottSzamuJatekosAltalJatszhatoKartyajatekok(i));
            } catch (InputMismatchException e) {
                System.err.println("Számot vártunk!");
            }
            
            System.out.println("---------------------------------------------");
            System.out.print("Létrehozott állományok száma: " + kiirFajlba3EvesekAltalJatszhato(new JatekLista[] {kl}) + "db\n");
        } catch (FileNotFoundException ex){
            System.err.println("Nem tudom megnyitni az allomanyt!");
        } catch(ArrayIndexOutOfBoundsException ex){
            System.err.println("Nincs megadva parancssori argumentum");
        }
    }
    static void kiirBarkiAltalJatszhatok(Tarsasjatek[] tomb){
        for (Tarsasjatek jatek : tomb){
            if( jatek.getKorhatar() == 0)
                System.out.println((jatek instanceof Kartyajatek ? "Kartyajatek " : "Tarsasjatek ") + jatek );
        }
    }
    static void felvesz32LaposMagyarKartyajatokok( JatekLista jl, Collection<Tarsasjatek> koll) throws NegativErtekException{
        for (Tarsasjatek jatek : koll)
            if (jatek instanceof Kartyajatek
                    && ((Kartyajatek)jatek).getPaklimeret() == 32
                    && ((Kartyajatek)jatek).getPaklifajta().equals("magyar"))
                jl.felvesz(new Kartyajatek("magyar",
                                            32,
                                            jatek.getNev().toUpperCase(),
                                            jatek.getKorhatar(),
                                            jatek.getMin(),
                                            jatek.getMax()));
    }
    static int kiirFajlba3EvesekAltalJatszhato(JatekLista[] tomb){
        int szam = 0;
        for (JatekLista jl : tomb)
            if(!jl.megfeleloJatekok(3).isEmpty())
                jl.kiir(("lista" + ++szam + ".txt"));
        return szam;
    }
}