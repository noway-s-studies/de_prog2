package egyetem;

public interface TanterviHalo {
    // Új tantárgyat/kurzust ad a hálóhoz
    public void ujTargy(Tantargy targy);
    //visszaadja a hálóban lévő tárgyak/kurzusok összkreditszámát
    public int OsszKredit();
    // visszaadja azoknak a kurzusoknak a rendezett listáját (a természetes
    // rendezettségük sorrendjében , amelyek időtartama meghaladja a paraméterként
    // megkapott óraszámot
    public java.util.List<Kurzus> hosszúkurzusok(int oraszam);
}
