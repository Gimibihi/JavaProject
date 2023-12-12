
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * @author eimutis
 */
public class GreitaveikosTyrimas {

    public static final String FINISH_COMMAND = "finishCommand";
    private final BlockingQueue resultsLogger = new SynchronousQueue();
    private final Semaphore semaphore = new Semaphore(-1);
    private final Timekeeper tk;

    private final String[] TYRIMU_VARDAI = {"add", "rem", "get", "cont", "sort"};
    private final int[] TIRIAMI_KIEKIAI = {100000, 200000, 400000, 800000};

    private final UnrolledLinkedListADT<Kompiuteris> komp1 = new UnrolledLinkedList<Kompiuteris>(100);
    private final UnrolledLinkedListADT<Kompiuteris> komp2 = new UnrolledLinkedList<Kompiuteris>(100);
    private final Queue<String> chainsSizes = new LinkedList<>();

    public GreitaveikosTyrimas() {
        semaphore.release();
        tk = new Timekeeper(TIRIAMI_KIEKIAI, resultsLogger, semaphore);
    }

    public void pradetiTyrima() {
        try {
            SisteminisTyrimas();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void SisteminisTyrimas() throws InterruptedException {
        try {
            chainsSizes.add("Greitaveika");
            chainsSizes.add("   kiekis      " + TYRIMU_VARDAI[0] + "   " + TYRIMU_VARDAI[1] + "   " + TYRIMU_VARDAI[2] + "   " + TYRIMU_VARDAI[3]);
            for (int k : TIRIAMI_KIEKIAI) {
                Kompiuteris[] autoArray = KompGamyba.gamintiKompiuterius(k);
                komp1.clear();
                komp2.clear();

                tk.startAfterPause();
                tk.start();

                for (int i = 0; i < k; i++) {
                    komp1.add(autoArray[i]);
                }
                tk.finish(TYRIMU_VARDAI[0]);

                for (int i = 0; i < k; i++) {
                    komp2.add(autoArray[i]);
                }
                for (int i = 0; i < k - 1; i++) {
                    komp1.remove(i);
                }
                tk.finish(TYRIMU_VARDAI[1]);


                for (int i = 0; i < k - 1; i++) {
                    komp2.get(i);
                }
                tk.finish(TYRIMU_VARDAI[2]);

                for (int i = 0; i < k - 1; i++) {
                    komp2.contains(autoArray[i]);
                }
                tk.finish(TYRIMU_VARDAI[3]);

                komp2.sort(Kompiuteris.pagalKaina);
                tk.finish(TYRIMU_VARDAI[4]);
                tk.seriesFinish();
            }

            StringBuilder sb = new StringBuilder();
            chainsSizes.stream().forEach(p -> sb.append(p).append(System.lineSeparator()));
            tk.logResult(sb.toString());
            tk.logResult(FINISH_COMMAND);
        } catch (MyException e) {
            tk.logResult(e.getMessage());
        }
    }

    public BlockingQueue<String> getResultsLogger() {
        return resultsLogger;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
