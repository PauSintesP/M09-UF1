import java.util.*;

public class Polialfabetic {
    private static final long CONTRASENYA = 123456789;
    private static final char[] AbecedariMin = "abcdefghijklmnopqrstuvwxyzàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿ".toCharArray();
    private static char[] AbecedariMinPermutat;
    private static Random random;

    public static void main(String[] args) {
        String textOriginal = "text random àáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿ";
        System.out.println("Text original: " + textOriginal);

        String textXifrat = xifraPoliAlfa(textOriginal);
        System.out.println("Text xifrat: " + textXifrat);

        String textDesxifrat = desxifraPoliAlfa(textXifrat);
        System.out.println("Text desxifrat: " + textDesxifrat);
    }

    public static void inicialitzaRandom() {
        random = new Random(CONTRASENYA);
    }

    public static void permutaAlfabet() {
        AbecedariMinPermutat = permutaArray(AbecedariMin);
    }

    public static char[] permutaArray(char[] alfabet) {
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

    public static String xifraPoliAlfa(String text) {
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

    public static String desxifraPoliAlfa(String textXifrat) {
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

    public static char substitueixLletra(char lletra, char[] alfabetOriginal, char[] alfabetPermutat) {
        for (int i = 0; i < alfabetOriginal.length; i++) {
            if (alfabetOriginal[i] == lletra) {
                return alfabetPermutat[i];
            }
        }
        return lletra;
    }
}
