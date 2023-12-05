import java.util.*;

/**
 * Duomenų klasė
 */
public class Kompiuteris {

    private static final int minRamKiek = 2;
    private static final int maxRamKiek = 256;
    private static final int minHDDVieta = 256;
    private static final int maxHDDVieta = 8192;
    private static final double minKaina = 100.0;
    private static final double maxKaina = 25000.0;
    private String procesoriausGamintojas = "";
    private String procesoriausModelis = "";
    private int ramuKiekis = -1;
    private int kietojoDiskoVieta = -1;
    private int nasumas = -1;
    private double kaina = -1.0;


    /**
     * Metodas Paimti Kainai
     *
     * @return Kaina
     */
    public double getKaina() {
        return kaina;
    }

    /**
     * Metodas nustatyti kainai
     *
     * @param kaina kainos reiksme
     */
    public void setKaina(double kaina) {
        this.kaina = kaina;
    }

    /**
     * Metodas Paimti Procesoriaus Gamintojui
     *
     * @return procesoriaus gamintojas
     */
    public String getProcesoriausGamintojas() {
        return procesoriausGamintojas;
    }

    /**
     * Metodas nustatyti procesoriaus Gamintoja
     *
     * @param procesoriausGamintojas
     */
    public void setProcesoriausGamintojas(String procesoriausGamintojas) {
        this.procesoriausGamintojas = procesoriausGamintojas;
    }

    /**
     * Metodas Paimti procesoriaus modeli
     *
     * @return procesoriaus modeli
     */
    public String getProcesoriausModelis() {
        return procesoriausModelis;
    }

    /**
     * Metodas nustatyti procesoriaus modeli
     *
     * @param procesoriausModelis
     */
    public void setProcesoriausModelis(String procesoriausModelis) {
        this.procesoriausModelis = procesoriausModelis;
    }

    /**
     * Metodas Paimti nasuma
     *
     * @return nasuma
     */
    public int getNasumas() {
        return nasumas;
    }

    /**
     * Metodas nustatyti Nasumas
     *
     * @param nasumas
     */
    public void setNasumas(int nasumas) {
        this.nasumas = nasumas;
    }

    /**
     * Metodas Paimti ramu kieki
     *
     * @return ramu kieki
     */
    public int getRamuKiekis() {
        return ramuKiekis;
    }

    /**
     * Metodas nustatyti Ramu kieki
     *
     * @param ramuKiekis
     */
    public void setRamuKiekis(int ramuKiekis) {
        this.ramuKiekis = ramuKiekis;
    }

    /**
     * Metodas paimti Kietojo disko vieta
     *
     * @return Kietojo disko vieta
     */
    public int getKietojoDiskoVieta() {
        return kietojoDiskoVieta;
    }

    /**
     * Metodas nustatyti kietojo disko vietai
     *
     * @param kietojoDiskoVieta
     */
    public void setKietojoDiskoVieta(int kietojoDiskoVieta) {
        this.kietojoDiskoVieta = kietojoDiskoVieta;
    }

    public int hashCode() {
        return Objects.hash(procesoriausGamintojas,
                procesoriausModelis, ramuKiekis, kietojoDiskoVieta, nasumas, kaina);
    }

    /**
     * Tuščias Konstruktorius
     */
    public Kompiuteris() {
    }

    /**
     * Konstruktorius
     *
     * @param procesoriausGamintojas Gamintojas procesoriaus
     * @param procesoriausModelis    Procesoriaus Modelis
     * @param ramuKiekis             Ramu kiekis
     * @param kietojoDiskoVieta      Kietojo disko vieta
     * @param nasumas                Nasumas
     * @param kaina                  Kaina
     */
    public Kompiuteris(String procesoriausGamintojas, String procesoriausModelis, int ramuKiekis,
                       int kietojoDiskoVieta, int nasumas, double kaina) {
        this.procesoriausGamintojas = procesoriausGamintojas;
        this.procesoriausModelis = procesoriausModelis;
        this.ramuKiekis = ramuKiekis;
        this.kietojoDiskoVieta = kietojoDiskoVieta;
        this.nasumas = nasumas;
        this.kaina = kaina;
    }

    /**
     * Konstrutorius
     *
     * @param dataString duomenu eilute
     */
    public Kompiuteris(String dataString) {
        this.parse(dataString);
    }

    public Kompiuteris(Builder builder) {
        this.procesoriausGamintojas = builder.procesoriausGamintojas;
        this.procesoriausModelis = builder.procesoriausModelis;
        this.ramuKiekis = builder.ramuKiekis;
        this.kietojoDiskoVieta = builder.kietojoDiskoVieta;
        this.nasumas = builder.nasumas;
        this.kaina = builder.kaina;
        validate();
    }

    /**
     * Metodas sukurti duomenu Klase
     *
     * @param dataString duomenu eilute
     * @return nauja duomenu klase
     */

    public Kompiuteris create(String dataString) {
        return new Kompiuteris(dataString);
    }

    /**
     * Metodas Kuris patikrina duotus duomenis
     *
     * @return Klaida jeigu ji yra
     */
    public String validate() {
        String klaidosTipas = "";
        if (ramuKiekis < minRamKiek || ramuKiekis > maxRamKiek) {
            klaidosTipas = "Netinkamas ramų kiekis, turi būti ["
                    + minRamKiek + ":" + maxRamKiek + "]";
        }
        if (kietojoDiskoVieta < minHDDVieta || kietojoDiskoVieta > maxHDDVieta) {
            klaidosTipas = "Netinkamas HDD vieta, turi būti ["
                    + minHDDVieta + ":" + maxHDDVieta + "]";
        }
        if (kaina < minKaina || kaina > maxKaina) {
            klaidosTipas = "Netinkamas kaina, turi būti ["
                    + minKaina + ":" + maxKaina + "]";
        }
        return klaidosTipas;
    }

    /**
     * Metodas ivesti duomenis i klase is duomenu eilutes
     *
     * @param dataString duomenu eilute
     */
    public final void parse(String dataString) {
        try {
            String[] data = dataString.split(";");
            procesoriausGamintojas = data[0];
            procesoriausModelis = data[1];
            ramuKiekis = Integer.parseInt(data[2]);
            kietojoDiskoVieta = Integer.parseInt(data[3]);
            nasumas = Integer.parseInt(data[4]);
            try {
                setKaina(Double.parseDouble(data[5]));
            } catch (NumberFormatException e) {
                String line = data[5].replace(',', '.');
                setKaina(Double.parseDouble(line));
            }

        } catch (InputMismatchException e) {
            System.out.println("Blogas duomenų formatas apie kompiuterį -> " + dataString);
        } catch (NoSuchElementException e) {
            System.out.println("Trūksta duomenų apie kompiuterį -> " + dataString);
        }
    }

    /**
     * Metodas kuris sukuria duomenu eilute
     *
     * @return duomenu eilute
     */
    @Override
    public String toString() {
        return " Kompiuteris_" + procesoriausGamintojas + ":" + procesoriausModelis + " " + ramuKiekis + "GB " + kietojoDiskoVieta + "GB " + nasumas + " " + String.format("%7.2f", kaina) + "\u20AC";
    }

    /**
     * Metodas kuris sukuria duomenu masyva
     *
     * @return duomenu masyva
     */
    public String[] toStringArray() {
        String[] array = {procesoriausGamintojas, procesoriausModelis, Integer.toString(ramuKiekis), Integer.toString(kietojoDiskoVieta), Integer.toString(nasumas), Double.toString(kaina)};
        return array;
    }

    /**
     * Komparatorius pagal gamintoja
     */

    public final static Comparator<Kompiuteris> pagalGamintoja = new Comparator<Kompiuteris>() {
        public int compare(Kompiuteris komp1, Kompiuteris komp2) {
            int cmp = komp1.getProcesoriausGamintojas().compareTo(komp2.getProcesoriausGamintojas());
            return cmp;
        }
    };
    /**
     * Komparatorius pagal Modeli
     */
    public final static Comparator<Kompiuteris> pagalModeli = new Comparator<Kompiuteris>() {
        public int compare(Kompiuteris komp1, Kompiuteris komp2) {
            int cmp = komp1.getProcesoriausModelis().compareTo(komp2.getProcesoriausModelis());
            return cmp;
        }
    };
    /**
     * Komparatorius pagal Nasuma
     */
    public final static Comparator<Kompiuteris> pagalNasuma = new Comparator<Kompiuteris>() {
        public int compare(Kompiuteris komp1, Kompiuteris komp2) {
            int cmp = -1;
            if (komp1.getNasumas() > komp2.getNasumas())
                cmp = 1;
            else if (komp1.getNasumas() == komp2.getNasumas())
                cmp = 0;
            return cmp;
        }
    };
    /**
     * Komparatorius pagal Kaina
     */
    public final static Comparator<Kompiuteris> pagalKaina = new Comparator<Kompiuteris>() {
        @Override
        public int compare(Kompiuteris komp1, Kompiuteris komp2) {
            int cmp = -1;
            if (komp1.getKaina() > komp2.getKaina())
                cmp = 1;
            else if (komp1.getKaina() == komp2.getKaina())
                cmp = 0;
            return cmp;
        }
    };

    /**
     * Tuscias metodas
     *
     * @param o the object to be compared.
     * @return
     */
    public int compareTo(Kompiuteris o) {
        return 0;
    }

    /**
     * Builder klase kuri sugeneruoja duomenis
     */
    public static class Builder {
        private final static Random RANDOM = new Random(1949);
        private final static String[][] MODELIAI = {
                {"Intel", "i3", "i5", "i7", "i9", "Xeon"},
                {"AMD", "Ryzen 3", "Ryzen 5", "Ryzen 7", "Ryzen 9", "Threadripper"},
                {"Apple", "M1", "M2", "M3"},
                {"Qualcomm", "Snapdragon 8", "Snapdragon 888", "Snapdragon 870"}
        };
        private String procesoriausGamintojas = "";
        private String procesoriausModelis = "";
        private int ramuKiekis = -1;
        private int kietojoDiskoVieta = -1;
        private int nasumas = -1;
        private double kaina = -1.0;

        /**
         * Metodas sukurti nauja objekta
         *
         * @return nauja Kompiuteris objekta
         */
        public Kompiuteris build() {
            return new Kompiuteris(this);
        }

        /**
         * Metodas kuris sugeneruoja objekta pagal nustatytus duomenis
         *
         * @return naujas objektas
         */
        public Kompiuteris buildRandom() {
            int ma = RANDOM.nextInt(MODELIAI.length);
            int mo = RANDOM.nextInt(1, MODELIAI[ma].length);
            int HDD = 1 << RANDOM.nextInt(13);
            if (HDD < 256) {
                HDD = 256;
            }
            return new Kompiuteris(MODELIAI[ma][0], MODELIAI[ma][mo], 1 << RANDOM.nextInt(8),
                    HDD, RANDOM.nextInt(35000), 100 + RANDOM.nextDouble() * 24000);
        }

        public Builder procGamintojas(String procesoriausGamintojas) {
            this.procesoriausGamintojas = procesoriausGamintojas;
            return this;
        }

        public Builder procModelis(String procesoriausModelis) {
            this.procesoriausModelis = procesoriausModelis;
            return this;
        }

        public Builder ramuKiekis(int ramuKiekis) {
            this.ramuKiekis = ramuKiekis;
            return this;
        }

        public Builder HDDVieta(int kietojoDiskoVieta) {
            this.kietojoDiskoVieta = kietojoDiskoVieta;
            return this;
        }

        public Builder nasumas(int nasumas) {
            this.nasumas = nasumas;
            return this;
        }

        public Builder kaina(double kaina) {
            this.kaina = kaina;
            return this;
        }

    }
}
