package teszt;

import elektronika.LEDTeve;
import elektronika.SmartTeve;
import elektronika.Teve;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import piac.MediaPiac;

public class TeveTeszt {
    public static void main(String[] args) {
        // Tesztelés
        Teve próba1 = new Teve("LG", "42LC41",new LinkedList<String>(),124000);
        Teve próba2 = new Teve(
                "Samsung", 
                "321284A",
                Arrays.asList(new String[] {
                    "nagy",
                    "fekete",
                    "színess",
                    "hangos"}),
                211200);
//        System.out.println(próba1);
//        System.out.println(próba2);        
        // 6.
        try(Scanner fajl = new Scanner(new File(args [0]))){
            List<Teve> lista = new ArrayList<>();
            while (fajl.hasNextLine()) {                
                Scanner sor = new Scanner(fajl.nextLine());
                sor.useDelimiter(";");
                char kategoria = sor.next().charAt(0);
                String marka = sor.next();
                String tipus = sor.next();
                int ar = sor.nextInt();
                boolean modern = false;
                if(kategoria != 'E')
                    modern = sor.next().charAt(0) == '+';
                List<String> tulajdonsag = new ArrayList<>();
                if(sor.hasNext())
                    tulajdonsag = Arrays.asList(sor.next().split(","));
                Teve teve = null;
                switch (kategoria){
                    case 'E':
                        teve = new Teve(marka, tipus, tulajdonsag, ar);
                        break;
                    case 'L':
                        teve = new LEDTeve(modern, marka, tipus, tulajdonsag, ar);
                        break;
                    case 'S':
                        teve = new SmartTeve(modern, marka, tipus, tulajdonsag, ar);
                        break;
                }
                lista.add(teve);
            }
//            for (Teve teve : lista) {
//                System.out.println(teve);
//            }
            
            MediaPiac mp = new MediaPiac(
                args.length > 1 ? args[1] : "Debreceni Médiapiac", 
                args.length > 2 ? args[2] : "Debrecen, Piac u.", 
                lista.toArray(new Teve[0]));
            // 7.
            Scanner bill = new Scanner(System.in);
            System.out.println("---------------------------------------------");
            System.out.print("Kérek egy tulajdonságot: ");
            for (Teve teve : mp.adottTulajdonsaguTevek(bill.nextLine())) {
                System.out.println(" - " + teve);
            };
//            System.out.println("---------------------------------------------");
//            System.out.println("Médiapiac: ");
//            System.out.println(mp);

            // 8.
            System.out.println("---------------------------------------------");
            System.out.print("Átlagos tulajdonságszám: ");
            double atlag = atlagosTulajdonsagSzam(lista);
            System.out.println(atlag);
            for (Teve teve : lista) {
                if (teve.getTulajdonság().length < atlag) {
                    System.out.println(" - " + teve);
                }
            }

            // 9.
            System.out.println("---------------------------------------------");
            System.out.print("Kérek egy kezdúbetüt: ");
            if (mp.nagybetusitAdottBetuvelKezdodoMarkakat(bill.next().charAt(0))) {
                System.out.println("Volt módosítás!\n" + mp);
            } else {
                System.out.println("Nem volt módosítás!");
            }
            
            // 10.
            System.out.println("---------------------------------------------");
            if (mp.tobbSmartMintLed()) {
                System.out.println("Több Smart mint LED!");
            } else {
                System.out.println("Több LED mint Smart!");               
            }
            
            // 11.
            System.out.println("---------------------------------------------");
            List<MediaPiac> mpLista = new ArrayList<>();
            mpLista.add(mp);
            System.out.println("Létrehozott allományok száma: " + 
                    kiirFajlba(mpLista, args[0]));
            
            
            System.out.println("");
        }   catch (FileNotFoundException ex) {
            System.err.println("Hiba az allomany megnyitasakor:\n -> " + args[0]);
        }   catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Nincs megadva Parancssori argomentum!");
        }
    
    }
    
    static double atlagosTulajdonsagSzam(Collection<Teve> tevek){
        double osszTulajdonsagSzam = 0;
        for (Teve teve : tevek) {
            osszTulajdonsagSzam += teve.getTulajdonság().length;
        }
        return osszTulajdonsagSzam / tevek.size();
    }
    
    static int kiirFajlba(Collection<MediaPiac> koll, String tulajdonsag){
        int szam = 0;
        for (MediaPiac mediaPiac : koll) {
            if(mediaPiac.adottTulajdonsaguModernTevek(tulajdonsag).size() >= 3){
                try (PrintStream ps = new PrintStream("lista" + szam + ".txt")){
                    ps.print(mediaPiac);
                } catch (FileNotFoundException e) {
                    System.err.println("Nem nyitható meg: lista" + szam + ".txt");
                }
            }
        }
        
        for (MediaPiac mediaPiac : koll) {
            if(mediaPiac.adottTulajdonsaguModernTevek(tulajdonsag).size() >= 3){
                mediaPiac.kiír("lista" + ++szam + ".txt");
            }
            
        }
        return szam;
    }
    
}
