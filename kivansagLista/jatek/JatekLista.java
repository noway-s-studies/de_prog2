package jatek;
import java.util.List;

public interface JatekLista{
        /**  5 */
 // új játékot vesz fel a listára
   void felvesz(Tarsasjatek jatek);
 // visszaadja azoknak a kártyajátékoknak a rendezett listáját (a természetes
 // rendezettség sorrendjében), amelyeket a paraméterként megkapott fajtájú
 // paklival kell játszani
   List<Kartyajatek> kartyajatekok(String paklifajta);
 // visszaadja azoknak a játékoknak a rendezett listáját (a természetes
 // rendezettség sorrendjében), amelyeket játszhat egy paraméterként megkapott
// életkorú személy
   List<Tarsasjatek> megfeleloJatekok(int eletkor);
 // kiírja a megadott állományba a játékok listáját (mindegyiket külön sorba)
   void kiir(String fajlnev);
}
