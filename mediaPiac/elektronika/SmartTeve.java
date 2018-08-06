package elektronika;
import java.util.Collection;

public class SmartTeve extends Teve{
    // 4.
    private boolean AlkalmazásTelepítő;

    // 4.
    public SmartTeve(boolean AlkalmazásTelepítő, String marka, String tipus, Collection<String> tulajdonsag, int ar) {
        super(marka, tipus, tulajdonsag, ar);
        this.AlkalmazásTelepítő = AlkalmazásTelepítő;
    }

    // 4.
    public boolean isAlkalmazásTelepítő() {
        return AlkalmazásTelepítő;
    }

}
