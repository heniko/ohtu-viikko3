package ohtu.intjoukkosovellus;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IllegalArgumentException();
        }
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public IntJoukko(int kapasiteetti) {// Käytetään täyden konstruktorin koodia apuna
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko() {// Käytetään täyden konstruktorin koodia apuna
        this(OLETUSKAPASITEETTI, OLETUSKASVATUS);
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == ljono.length) {
                kasvata();
            }
            return true;
        }
        return false;
    }

    public void kasvata() {//Joukon koon kasvatus muutettu omaksi metodiksi
        int[] uusiLjono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(ljono, uusiLjono);
        ljono = uusiLjono;
    }

    public boolean kuuluu(int luku) {// Lukujen määrän laskeminen poistettu
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                for (int j = i; i < alkioidenLkm; i++) {
                    ljono[i] = ljono[i + 1];
                }
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {//toString() muutettu hieman lyhyemmäksi
        String palautus = "{";
        String taulukkoString = IntStream.of(toIntArray())
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));
        palautus += taulukkoString + "}";
        return palautus;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }

    private static void lisaaKaikki(IntJoukko joukko, int[] lisattava) {
        for (int i = 0; i < lisattava.length; i++) {
            joukko.lisaa(lisattava[i]);
        }
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteJoukko = new IntJoukko();// Joukon nimeä muutettu kuvaavammaksi
        lisaaKaikki(yhdisteJoukko, a.toIntArray());
        lisaaKaikki(yhdisteJoukko, b.toIntArray());
        return yhdisteJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausJoukko = new IntJoukko();// Joukon nimeä muutettu kuvaavammaksi
        int[] leikkausTaulukko = Arrays.stream(a.toIntArray())
                .filter(x -> Arrays.stream(b.toIntArray()).anyMatch(y -> y == x))
                .toArray();
        lisaaKaikki(leikkausJoukko, leikkausTaulukko);
        return leikkausJoukko;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotusJoukko = new IntJoukko();// Joukon nimeä muutettu kuvaavammaksi
        int[] erotusTaulukko = Arrays.stream(a.toIntArray())
                .filter(x -> Arrays.stream(b.toIntArray()).anyMatch(y -> y != x))
                .toArray();
        lisaaKaikki(erotusJoukko, erotusTaulukko);
        return erotusJoukko;
    }
}
