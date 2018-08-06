package botanika;

public interface Novenytar
{
  // visszaadja a növénytárban szereplő olyan növények listáját a természetes
  // rendezettségük sorrendjében, amelyek színei között szerepel a paraméterként
  // megkapott szín
  public java.util.List<Noveny> adottSzinuNovenyek(String szin);

  // visszaadja a növénytárban szereplő olyan növények listáját a természetes
  // rendezettségük sorrendjében, amelyek színei között szerepel az első
  // paraméterként megkapott szín, és ha a második paraméter igaz, akkor zöldségek,
  // különben gyümölcsök
  public java.util.List<Noveny> adottSzinuNovenyek(String szin, boolean zoldseg);

  // kiírja a megadott nevű állományba a növények listáját (mindegyiket külön
  // sorba), először a nem ehetőket, aztán az ehetőket, név szerint növekvő
  // sorrendben
  public void kiir(String fajlnev);
}