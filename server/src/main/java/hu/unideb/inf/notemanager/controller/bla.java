import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Használat: java Main <input_fajl> <eletkor_kuszob> [tipus] [fizetes_szures]");
            return;
        }

        String inputFajl = args[0];
        int eletkorKuszob = Integer.parseInt(args[1]);
        String tipusSzures = args.length > 2 ? args[2] : null;
        String fizetesSzures = args.length > 3 ? args[3] : null;

        List<Alkalmazott> alkalmazottak = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFajl))) {
            String sor;
            while ((sor = br.readLine()) != null) {
                try {
                    alkalmazottak.add(parseAlkalmazott(sor));
                } catch (HianyosAdatException e) {
                    System.out.println("Hibás adat: " + sor + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Hiba az állomány olvasásakor: " + e.getMessage());
            return;
        }

        // Alkalmazottak szűrése
        List<Alkalmazott> szurtAlkalmazottak = alkalmazottak.stream()
                .filter(a -> a.getEletkor() > eletkorKuszob)
                .filter(a -> tipusSzures == null || a.getClass().getSimpleName().equals(tipusSzures))
                .filter(a -> {
                    if (fizetesSzures != null && fizetesSzures.startsWith("fizetes>")) {
                        int minimumFizetes = Integer.parseInt(fizetesSzures.substring(8));
                        return a.fizetesSzamitas() > minimumFizetes;
                    }
                    return true;
                })
                .sorted()
                .collect(Collectors.toList());

        // Szűrt adatok mentése
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("szurt_alkalmazottak.txt"))) {
            for (Alkalmazott alkalmazott : szurtAlkalmazottak) {
                bw.write(alkalmazott.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Hiba az állomány írásakor: " + e.getMessage());
        }

        // Fizetés összesítése, ha 3 paraméter van
        if (args.length == 4) {
            int osszesitettFizetes = szurtAlkalmazottak.stream()
                    .mapToInt(Alkalmazott::fizetesSzamitas)
                    .sum();
            System.out.println("Összesített fizetés: " + osszesitettFizetes + " Ft");
        }
    }

    // Sor feldolgozása, alkalmazott létrehozása
    private static Alkalmazott parseAlkalmazott(String sor) throws HianyosAdatException {
        String[] reszek = sor.split(":");
        if (reszek.length != 2) {
            throw new HianyosAdatException("Hibás formátum: " + sor);
        }

        String tipus = reszek[0];
        String[] adatok = reszek[1].split(",");
        if (adatok.length != 3) {
            throw new HianyosAdatException("Hibás formátum: " + sor);
        }

        String nev = adatok[0];
        int eletkor = Integer.parseInt(adatok[1]);
        int ertek = Integer.parseInt(adatok[2]);

        switch (tipus) {
            case "IrodaiAlkalmazott":
                return new IrodaiAlkalmazott(nev, eletkor, ertek);
            case "FizikaiMunkas":
                return new FizikaiMunkas(nev, eletkor, ertek);
            default:
                throw new HianyosAdatException("Ismeretlen típus: " + tipus);
        }
    }
}
