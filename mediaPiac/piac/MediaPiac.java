package piac;

import elektronika.LEDTeve;
import elektronika.SmartTeve;
import elektronika.Teve;
import elektronika.TeveUzlet;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MediaPiac implements TeveUzlet {
    // 5.
    private String nev, cim;
    private List<Teve> tevek;

    // 5.
    public MediaPiac(String nev, String cim, Teve[] tevek) {
        this.nev = nev;
        this.cim = cim;
        this.tevek = Arrays.asList(tevek);
    }

    // 5.
    @Override
    public String toString() {
        return nev + ": " + cim + "\n" + teveList();
    }
    
    // 5.
    private String teveList() {
        List<Teve> ledTevek = new ArrayList<>();
        List<Teve> smartTevek = new ArrayList<>();
        List<Teve> egyebTevek = new ArrayList<>();
        
        for (Teve teve : tevek) {
            if (teve instanceof LEDTeve) {
                ledTevek.add(teve);
            }
            if (teve instanceof SmartTeve) {
                smartTevek.add(teve);
            }
            if (!(teve instanceof LEDTeve) && !(teve instanceof SmartTeve)) {
                egyebTevek.add(teve);
            }
        }
        
        Collections.sort(ledTevek);
        Collections.sort(smartTevek);
        Collections.sort(egyebTevek);
        
        StringBuilder sb = new StringBuilder("LED Tevek\n");
        for (Teve teve : ledTevek){
            sb.append(" - " + teve + "\n");
        }
        sb.append("Smart teve\n");
        for (Teve teve : smartTevek){
            sb.append(" - " + teve + "\n");
        }
        sb.append("Egyeb teve\n");
        for (Teve teve : egyebTevek){
            sb.append(" - " + teve + "\n");
        }
        return sb.toString();
    }

    // 5.
    @Override
    public void kiír(String fajlnev) {
        try (PrintStream ps = new PrintStream(fajlnev);){
            System.out.println(teveList());
            for(Teve teve : tevek)
                ps.println( teve );
        } catch (FileNotFoundException ex) {
            System.err.println("Hiba az allomany magnyitasanal!");
        }
        
        
    }
    
    // 5.
    @Override
    public List<Teve> adottTulajdonsaguTevek(String tulajdonsag) {
        List<Teve> lista = new ArrayList<>();
        for (Teve teve : tevek) {
            if(Arrays.asList(teve.getTulajdonság()).contains(tulajdonsag))
                lista.add(teve);
        }
        return lista;
    }

    // 5.
    @Override
    public List<Teve> adottTulajdonsaguModernTevek(String tulajdonsag) {
        List<Teve> lista = new ArrayList<>();
        for (Teve teve : tevek) {
            if(Arrays.asList(teve.getTulajdonság()).contains(tulajdonsag)
                    && (teve instanceof LEDTeve && ((SmartTeve)teve).isAlkalmazásTelepítő()))
                lista.add(teve);
        }     
        return lista; 
    }
    // 9.
    public boolean nagybetusitAdottBetuvelKezdodoMarkakat(char kezdobetu){
        boolean voltModositas = false;
        for (Teve teve : tevek) {
            if (teve.getMárka().charAt(0) == kezdobetu) {
                teve.setMárka(teve.getMárka().toUpperCase());
                voltModositas = true;
            }
        }
        return voltModositas;
    } 
    
    // 10.
    public boolean tobbSmartMintLed(){
        int ledTevek = 0;
        int smartTevek = 0;
        for (Teve teve : tevek) {
            boolean led = false, smart = false;
            for (String tulajdonsag : teve.getTulajdonság()) {
                
                if (tulajdonsag.equalsIgnoreCase("LED")) {
                    led = true;
                }
                if (tulajdonsag.equalsIgnoreCase("SMART")) {
                    smart = true;
                }
            }
            if (teve instanceof LEDTeve || led) {
                ++ledTevek;
            }
            if (teve instanceof SmartTeve || smart) {
                ++smartTevek;
            }
        }
        return smartTevek > ledTevek;
    }
    
}