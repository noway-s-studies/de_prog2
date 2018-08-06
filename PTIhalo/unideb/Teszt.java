package unideb;

import egyetem.HibasAdatException;
import egyetem.Kurzus;
import egyetem.Tantargy;
import egyetem.TanterviHalo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import static unideb.PTIHalo.filter;

public class Teszt {

    public static void main(String[] args) throws HibasAdatException {
        try(Scanner fajl = new Scanner(new File(args [0]))){
            Set<Tantargy> lista = new HashSet<>();
            while (fajl.hasNextLine()) {                
                Scanner sor = new Scanner(fajl.nextLine());
                sor.useDelimiter(";");
                String kod = sor.next();
                String nev = sor.next();
                int kreditSzam = sor.nextInt();
                if (sor.hasNext()) {
                    boolean eaOrGy = false;
                    switch (sor.next().charAt(0)){
                        case 'E':
                            eaOrGy = true;
                            break;
                        case 'G':
                            eaOrGy = false;
                            break;
                    }
                    if(sor.hasNext()){
                        try {
                            String terem = sor.next();
                            int hossz = sor.nextInt();
                            DayOfWeek hetNapja = null;
                            switch (sor.next()){
                                case "H":
                                    hetNapja = MONDAY;
                                    break;
                                case "K":
                                    hetNapja = TUESDAY;
                                    break;
                                case "SZE":
                                    hetNapja = WEDNESDAY;
                                    break;
                                case "CS":
                                    hetNapja = THURSDAY;
                                    break;
                                case "P":
                                    hetNapja = FRIDAY;
                                    break;
                                case "SZO":
                                    hetNapja = SATURDAY;
                                    break;
                                case "V":
                                    hetNapja = SUNDAY;
                                    break;
                            }                        
                            String timer = sor.next();
                            Scanner time = new Scanner(timer);
                            time.useDelimiter(":");
                            int ora = time.nextInt();
                            int perc = time.nextInt();
                            LocalTime oraPerc = LocalTime.of(ora, perc);
                            lista.add(new Kurzus(kod, nev, kreditSzam, eaOrGy, terem, hossz, hetNapja, oraPerc));
                        
                        } catch (DateTimeException e) {
                            System.out.println("Hibás adat!");
                        }
                    } else {
                        lista.add(new Kurzus(kod, nev, kreditSzam, eaOrGy));
                    }
                } else {
                    lista.add(new Tantargy(kod, nev, kreditSzam));
                }
            }
            System.out.println("---------------------------------------------");
            System.out.println("6. Beolvasva: ");
            for (Tantargy tantargy : lista) {
                System.out.println(" - "+ tantargy);
            }
            
            
            System.out.println("---------------------------------------------");
            System.out.print("7. Kérek egy óraszámot: ");
            PTIHalo newHalo1 = new PTIHalo(args.length > 1 ? args[1] : "PTI-stantervi háló", lista );
            Scanner bill = new Scanner(System.in);
            for (Tantargy tan : newHalo1.hosszúkurzusok(bill.nextInt())) {
                System.out.println(" - " + tan);
            };
            
            System.out.println("---------------------------------------------");
            System.out.println("8. Új kurzusok: ");
            for (Kurzus kiirNemKurzu : kiirNemKurzus(lista)) {
                System.out.println(kiirNemKurzu);
            }
            
            System.out.println("---------------------------------------------");
            System.out.println("9. NULL törlés: ");
            System.out.println(newHalo1.toString());
            
            System.out.println("---------------------------------------------");
            System.out.println("10. Össz kreditszám: " + newHalo1.OsszKredit());
            System.out.println("Álományok száma: " + kiir(new TanterviHalo[] {newHalo1}, 16));
            
                
        }   catch (FileNotFoundException ex) {
            System.err.println("Hiba az allomany megnyitasakor:\n -> " + args[0]);
        }   catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Nincs megadva parancssori argumentum");
        }
    }
    
    static ArrayList<Kurzus> kiirNemKurzus(Collection<Tantargy> tantargy){
        ArrayList<Kurzus> lista = new ArrayList<Kurzus>();
        for (Tantargy tantargy1 : tantargy) {
            if (!(tantargy1 instanceof Kurzus)) {
                lista.add(new Kurzus(tantargy1.getKod(), tantargy1.getNev(), 0, true));
                lista.add(new Kurzus(tantargy1.getKod(), tantargy1.getNev(), 0, false));
            }
        }
        return lista;
    }
    
    
    static int kiir(TanterviHalo[] tomb, int kreditszam){
        int szam = 0;
        for (TanterviHalo th : tomb)
            if(th.OsszKredit() == kreditszam){
                try (PrintStream ps = new PrintStream("lista" + ++szam + ".txt")){
                    ps.println( th );
                }
                catch (FileNotFoundException ex) {
                    System.err.println("Nem sikerult megnyitni az allomanyt!");
                }
            }
        return szam;
    }
}