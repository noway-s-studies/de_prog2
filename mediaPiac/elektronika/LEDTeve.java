package elektronika;
import java.util.Collection;

public class LEDTeve extends Teve{
    // 4.
    private boolean OledTech;

    // 4.
    public LEDTeve(boolean OledTech, String marka, String tipus, Collection<String> tulajdonsag, int ar) {
        super(marka, tipus, tulajdonsag, ar);
        this.OledTech = OledTech;
    }
    
    // 4.
    public boolean isOledTech() {
        return OledTech;
    }
}
