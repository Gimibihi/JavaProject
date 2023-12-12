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
        System.out.println("Pradiniai duomenys:");
        for(int i=0;i<10;i++) {
            System.out.println(ULL.get(i).toString());
        }
        System.out.println("Iterpiam elementa i 5 vieta:");
        ULL.insert(arrayKomp[4],4);
        System.out.println(ULL.get(4).toString());

        System.out.println("Surikiuojam duomenis:");
        ULL.sort(Kompiuteris.pagalGamintoja);
        for(int i=0;i<10;i++) {
            System.out.println(ULL.get(i).toString());
        }
        System.out.println("Panaikiname 9 elementa:");
        ULL.remove(8);
        System.out.println(8);

        System.out.println("Patikriname ar tuscias sarasas:");
        System.out.println(ULL.isEmpty());
        System.out.println("Istustiname sarasa:");
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
