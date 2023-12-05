import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UnrolledLinkedListADT<Kompiuteris> ULL = new UnrolledLinkedList<Kompiuteris>(10);
        KompGamyba gamyba = new KompGamyba();
        Kompiuteris[] arrayKomp = KompGamyba.gamintiKompiuterius(10000);
        ArrayList<Kompiuteris> k = new ArrayList<>();
        for (int i = 0; i < arrayKomp.length; i++) {
            ULL.add(arrayKomp[i]);
            k.add(arrayKomp[i]);
        }
        System.out.println(ULL.get(1).toString());
        System.out.println(ULL.get(2).toString());
        System.out.println(ULL.get(3).toString());
        System.out.println(ULL.get(4).toString());
        ULL.insert(arrayKomp[4],4);
        System.out.println(ULL.get(4).toString());
        System.out.println(ULL.get(10).toString());
        System.out.println(ULL.get(11).toString());
        System.out.println(ULL.get(12).toString());
        System.out.println(ULL.get(9999).toString());
        ULL.sort(Kompiuteris.pagalGamintoja);
        System.out.println("Surikiuotas");
        System.out.println(ULL.get(1).toString());
        System.out.println(ULL.get(2).toString());
        System.out.println(ULL.get(3).toString());
        System.out.println(ULL.get(4).toString());
        System.out.println(ULL.get(10).toString());
        System.out.println(ULL.get(11).toString());
        System.out.println(ULL.get(12).toString());
        ULL.clear();
        GreitaveikosTyrimas gt = new GreitaveikosTyrimas();
        
        new Thread(() -> {
            try {
                String result;
                while (!(result = gt.getResultsLogger().take())
                        .equals(GreitaveikosTyrimas.FINISH_COMMAND)) {
                    System.out.println(result);
                    gt.getSemaphore().release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            gt.getSemaphore().release();
            
        }, "Greitaveikos_rezultatu_gija").start();

        //Šioje gijoje atliekamas greitaveikos tyrimas
        new Thread(() -> gt.pradetiTyrima(), "Greitaveikos_tyrimo_gija").start();
    }
}
