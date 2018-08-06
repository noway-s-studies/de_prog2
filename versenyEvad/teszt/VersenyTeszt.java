package teszt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import tanev.VersenyEvad;
import verseny.NincsNyelvException;
import verseny.ProgVerseny;
import verseny.TanulmanyiVerseny;
import verseny.VersenyLista;

public class VersenyTeszt {
    public static void main(String[] args) {
        try(Scanner fajl = new Scanner(new File(args [0]))){
            Set<TanulmanyiVerseny> lista = new HashSet<>();
            while (fajl.hasNextLine()) {                
                Scanner sor = new Scanner(fajl.nextLine());
                sor.useDelimiter(";");
                char tipus = sor.next().charAt(0);
                String nev = sor.next();
                String tantargy = "";
                if (tipus == 'T') {
                    tantargy = sor.next();
                }
                String datumS = sor.next();
                String kezdesIdopontS = sor.next();
                String befejezesIdopontS = sor.next();   
                
                Scanner data1 = new Scanner(datumS);
                data1.useDelimiter("\\.");
                LocalDate datum = LocalDate.of(data1.nextInt(), data1.nextInt(), data1.nextInt());
                
                Scanner data2 = new Scanner(kezdesIdopontS);
                data2.useDelimiter(":");
                LocalTime kezdesIdopont = LocalTime.of(data2.nextInt(), data2.nextInt());
                                
                Scanner data3 = new Scanner(befejezesIdopontS);
                data3.useDelimiter(":");
                LocalTime befejezesIdopont = LocalTime.of(data3.nextInt(), data3.nextInt());
                                

                try {
                    
                    String nyelvek = "";
                    Set<String> nyelv = new HashSet<>();
                    if (tipus == 'P') {
                        nyelvek = sor.next();
                        Scanner data4 = new Scanner(nyelvek);
                        data4.useDelimiter(",");
                        while (data4.hasNext()) {
                            nyelv.add(data4.next());
                        }

                    }
                    switch (tipus){
                            case 'T':
                                lista.add(new TanulmanyiVerseny(nev, tantargy, datum, kezdesIdopont, befejezesIdopont));
                                break;
                            case 'P':
                                lista.add(new ProgVerseny(nyelv, nev, datum, kezdesIdopont, befejezesIdopont));
                                break;
                    }
                } catch (NincsNyelvException e) {
                    System.out.println("Nincs megadva programozási nyelv!");
                } catch (NoSuchElementException e) {
                    System.out.println("Nincs megadva programozási nyelv!");
                }
                
            }
            
            System.out.println("---------------------------------------------");
            System.out.println("6. Beolvasva: ");
            for (TanulmanyiVerseny tantargy : lista) {
                System.out.println(" - "+ tantargy);
            }
            
            for( TanulmanyiVerseny tantargy : lista)
                tantargy.idoCsere();
            
            
            System.out.println("---------------------------------------------");
            System.out.print("7. Kérek egy programozási nyelvet: ");
            VersenyEvad ve01 = new VersenyEvad(args.length > 1 ? args[1] : "2016/2017", lista );
            Scanner bill = new Scanner(System.in);
            for (TanulmanyiVerseny tan : ve01.versenyek(bill.next())) {
                System.out.println(" - " + tan);
            };
            
            System.out.println("---------------------------------------------");
            System.out.println("8. Rövidebb mint 3 óra: ");
            kiirKevesebb3Ora(lista.toArray(new TanulmanyiVerseny[ 0 ]));
            
            
            
            System.out.println("---------------------------------------------");
            System.out.println("Létrehozott álományok száma: " + kiirFajlba(new VersenyLista[] {ve01}));
                
        }   catch (FileNotFoundException ex) {
            System.err.println("Hiba az allomany megnyitasakor:\n -> " + args[0]);
        }   catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Nincs megadva parancssori argumentum");
        }
    }
    
    static void kiirKevesebb3Ora(TanulmanyiVerseny[] tomb){
        for (TanulmanyiVerseny verseny : tomb){
            float ora = ((verseny.getBefejezesIdopont().getHour() *60 + verseny.getBefejezesIdopont().getMinute()) - (verseny.getKezdesIdopont().getHour() *60 + verseny.getKezdesIdopont().getMinute()))/60;
            if( ora < 3 )
                System.out.println((verseny instanceof ProgVerseny ? "Progverseny: " : "Tanulmányi verseny:  ") + verseny );
        }
    }
    static int kiirFajlba(VersenyLista[] tomb){
        int szam = 0;
        for (VersenyLista vl : tomb)
            if(vl.versenyek(new String[]{"C","C++"}).size() > 0){
                try (PrintStream ps = new PrintStream("lista" + ++szam + ".txt")){
                    ps.println( vl );
                }
                catch (FileNotFoundException ex) {
                    System.err.println("Nem sikerult megnyitni az allomanyt!");
                }
            }
        return szam;
    }
}
