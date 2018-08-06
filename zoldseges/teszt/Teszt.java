package teszt;

import botanika.Gyumolcs;
import botanika.NincsSzinException;
import botanika.Noveny;
import botanika.Zoldseg;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import piac.Zoldseges;

public class Teszt {
    public static void main(String[] args) throws NincsSzinException {
        try(Scanner fajl = new Scanner(new File(args [0]))){
            List<Noveny> lista = new ArrayList<>();
            while (fajl.hasNextLine()) {                
                Scanner sor = new Scanner(fajl.nextLine());
                sor.useDelimiter(";");
                Noveny noveny = null;
                char tipus = sor.next().charAt(0);
                
                String megnevezes = sor.next();
                List<String> szin = new ArrayList<>();
                if(sor.hasNext()){
                    szin = Arrays.asList(sor.next().split("/"));
                    
                    switch (tipus){
                        case '0':
                            noveny = new Noveny(megnevezes, szin, false);
                            break;
                        case '1':
                            noveny = new Zoldseg(megnevezes, szin);
                            break;
                        case '2':
                            noveny = new Gyumolcs(megnevezes, szin);
                            break;
                    }
                    
                    lista.add(noveny);
                }
        
        
            }
            for (Noveny noveny : lista) {
                System.out.println(noveny);
            }
            Zoldseges zoldseges1 = new Zoldseges(lista.toArray(new Noveny[0]), args.length > 1 ? args[1] : "Debrecen Kispiac" );
            Zoldseges zoldseges2 = new Zoldseges(lista.toArray(new Noveny[0]), args.length > 1 ? args[1] : "Debrecen Kispiac2" );
            Zoldseges zoldseges3 = new Zoldseges(lista.toArray(new Noveny[0]), args.length > 1 ? args[1] : "Debrecen Kispiac3" );
            
            Scanner bill = new Scanner(System.in);
            System.out.println("---------------------------------------------");
            System.out.print("Kérek egy színt: ");
            for (Noveny noveny : zoldseges1.adottSzinuNovenyek(bill.nextLine())) {
                System.out.println(" - " + noveny);
            };
            
            System.out.println("---------------------------------------------");
            System.out.print("Átlagos színszám: ");
            double atlag = atlagosSzinSzam(lista);
            System.out.println(atlag);
            
            for (Noveny noveny : lista) {
                if (noveny.getSzin().length > atlag) {
                    System.out.println(" - " + noveny);
                }
            }
            
            System.out.println("---------------------------------------------");
            System.out.print("Kérek egy kezdőbetűt: ");
            if (zoldseges1.nagybetusitKb(bill.next().charAt(0))) {
                System.out.println("Volt módosítás!\n" + zoldseges1);
            } else {
                System.out.println("Nem volt módosítás!");
            }
            
            System.out.println("---------------------------------------------");
            System.out.print("Zöldségek száma: ");
            System.out.println(zoldseges1.zoldsegSzam(true));
            System.out.print("Gyümölcsök száma: ");
            System.out.println(zoldseges1.zoldsegSzam(false));
            
            System.out.println("---------------------------------------------");
            List<Zoldseges> zoldsegesLista = new ArrayList<>();
            zoldsegesLista.add(zoldseges1);
            zoldsegesLista.add(zoldseges2);
            zoldsegesLista.add(zoldseges3);
            System.out.println("Létrehozott allományok száma: " + 
                    kiirFajlba(zoldsegesLista)
            );
            System.out.println("---------------------------------------------");
            
                
        }   catch (FileNotFoundException ex) {
            System.err.println("Hiba az allomany megnyitasakor:\n -> " + args[0]);
        }   catch(ArrayIndexOutOfBoundsException ex){
            System.err.println("Nincs megadva parancssori argumentum");
        }
    }
    
    static double atlagosSzinSzam(List<Noveny> novenyek){
        double osszSzinSzam = 0;
        for (Noveny noveny : novenyek) {
            osszSzinSzam += noveny.getSzin().length;
        }
        return osszSzinSzam / novenyek.size();
    }    
    
    static int kiirFajlba(Collection<Zoldseges> koll){
        int szam = 0;
        for (Zoldseges zoldsegesek : koll) {
            if(zoldsegesek.zoldsegSzam(true) < zoldsegesek.zoldsegSzam(false)){
                zoldsegesek.kiir("lista" + ++szam + ".txt");
            }
            
        }
        return szam;
    }
}
