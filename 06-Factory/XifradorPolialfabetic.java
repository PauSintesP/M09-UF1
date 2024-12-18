import java.util.*;

public class XifradorPolialfabetic implements Xifrador {
    private static final long CONTRASENYA = 123456789;
    private static final char[] AbecedariMin = "abcdefghijklmnopqrstuvwxyzàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿ".toCharArray();
    private static char[] AbecedariMinPermutat;
    private Random random;

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            long seed = Long.parseLong(clau);
            this.random = new Random(seed);
            byte[] output = xifraPoliAlfa(msg).getBytes();
            return new TextXifrat(output);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            long seed = Long.parseLong(clau);
            this.random = new Random(seed);
            return desxifraPoliAlfa(new String(xifrat.getBytes()));
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }

    private void inicialitzaRandom() {
        random = new Random(CONTRASENYA);
    }

    private void permutaAlfabet() {
        AbecedariMinPermutat = permutaArray(AbecedariMin);
    }

    private char[] permutaArray(char[] alfabet) {
        List<Character> alfabetList = new ArrayList<>();
        for (char c : alfabet) {
            alfabetList.add(c);
        }
        Collections.shuffle(alfabetList, random);
        char[] permutat = new char[alfabet.length];
        for (int i = 0; i < alfabet.length; i++) {
            permutat[i] = alfabetList.get(i);
        }
        return permutat;
    }

    private String xifraPoliAlfa(String text) {
        inicialitzaRandom();
        char[] nouText = new char[text.length()];

        for (int i = 0; i < text.length(); i++) {
            char lletra = text.charAt(i);
            permutaAlfabet();
            if (Character.isLowerCase(lletra)) {
                nouText[i] = substitueixLletra(lletra, AbecedariMin, AbecedariMinPermutat);
            } else {
                nouText[i] = lletra;
            }
        }
        return String.valueOf(nouText);
    }

    private String desxifraPoliAlfa(String textXifrat) {
        inicialitzaRandom();
        char[] nouText = new char[textXifrat.length()];

        for (int i = 0; i < textXifrat.length(); i++) {
            char lletra = textXifrat.charAt(i);
            permutaAlfabet();
            if (Character.isLowerCase(lletra)) {
                nouText[i] = substitueixLletra(lletra, AbecedariMinPermutat, AbecedariMin);
            } else {
                nouText[i] = lletra;
            }
        }
        return String.valueOf(nouText);
    }

    private char substitueixLletra(char lletra, char[] alfabetOriginal, char[] alfabetPermutat) {
        for (int i = 0; i < alfabetOriginal.length; i++) {
            if (alfabetOriginal[i] == lletra) {
                return alfabetPermutat[i];
            }
        }
        return lletra;
    }
}
