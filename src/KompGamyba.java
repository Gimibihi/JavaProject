import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class KompGamyba {
    private static final String ID_CODE = "K";      //  ***** nauja
    private static int serNr = 001;               //  ***** nauja

    private Kompiuteris[] kompiuteriai;
    private String[] raktai;
    private int kiekis = 0, idKiekis = 0;

    public static Kompiuteris[] gamintiKompiuterius(int kiekis) {
        Kompiuteris[] kompiuteriai = IntStream.range(0, kiekis).mapToObj(i -> new Kompiuteris.Builder().buildRandom()).toArray(Kompiuteris[]::new);
        Collections.shuffle(Arrays.asList(kompiuteriai));
        return kompiuteriai;
    }

    public static String[] gamintiKompIDs(int kiekis) {
        String[] raktai = IntStream.range(0, kiekis).mapToObj(i -> ID_CODE + (serNr++)).toArray(String[]::new);
        Collections.shuffle(Arrays.asList(raktai));
        return raktai;
    }

    public Kompiuteris[] gamintiIrParduotiKompiuterius(int aibesDydis, int aibesImtis) throws MyException {
        if (aibesImtis > aibesDydis) {
            aibesImtis = aibesDydis;
        }
        kompiuteriai = gamintiKompiuterius(aibesDydis);
        raktai = gamintiKompIDs(aibesDydis);
        this.kiekis = aibesImtis;
        return Arrays.copyOf(kompiuteriai, aibesImtis);
    }

    // Imamas po vienas elementas iš sugeneruoto masyvo. Kai elementai baigiasi sugeneruojama
    // nuosava situacija ir išmetamas pranešimas.
    public Kompiuteris parduotiKompiuteri() {
        if (kompiuteriai == null) {
            throw new MyException("computersNotGenerated");
        }
        if (kiekis < kompiuteriai.length) {
            return kompiuteriai[kiekis++];
        } else {
            throw new MyException("allSetStoredToMap");
        }
    }

    public String gautiIsBazesKompID() {
        if (raktai == null) {
            throw new MyException("computersIdsNotGenerated");
        }
        if (idKiekis >= raktai.length) {
            idKiekis = 0;
        }
        return raktai[idKiekis++];
    }
}
