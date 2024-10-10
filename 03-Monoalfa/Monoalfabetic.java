import java.util.*;
public class Monoalfabetic {
    private static final char[] AbecedariMin = "abcdefghijklmnopqrstuvwxyzàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿ".toCharArray();
    private static final char[] AbecedariMag = "abcdefghijklmnopqrstuvwxyzàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿ".toUpperCase().toCharArray();
    private static final char[] AbecedariMinPermutat = permutaAlfabet(AbecedariMin);
    private static final char[] AbecedariMagPermutat = permutaAlfabet(AbecedariMag);
    public static void main(String[] args) {
        System.out.println("Introdueix el text a xifrar:");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String textXifrat = xifraMonoAlfa(text);
        System.out.println("Text xifrat:"+textXifrat);
        System.out.println("Text desxifrat:"+desxifraMonoAlfa(textXifrat));
        scanner.close();
    }
    public static char[] permutaAlfabet(char[] alfabet) {
        List<Character> alfabetList = new ArrayList<>();
        for (char c : alfabet) {
            alfabetList.add(c);
        }
        Collections.shuffle(alfabetList);
        char[] permutat = new char[alfabet.length];
        for (int i = 0; i < alfabet.length; i++) {
            permutat[i] = alfabetList.get(i);
        }
        return permutat;
    }
    public static String xifraMonoAlfa(String text) {

        char[]  nouText = new char[text.length()];

        for (int i = 0; i < text.length(); i++) {
            char lletra = text.charAt(i);
            if (Character.isLowerCase(lletra)) {
                int index = 0;
                for (int j = 0; j < AbecedariMin.length; j++) {
                    if (AbecedariMin[j] == lletra) {
                        index = j;
                        break;
                    }
                }
                nouText[i] = AbecedariMinPermutat[index];
            } else if (Character.isUpperCase(lletra)) {
                int index = 0;
                for (int j = 0; j < AbecedariMag.length; j++) {
                    if (AbecedariMag[j] == lletra) {
                        index = j;
                        break;
                    }
                }
                nouText[i] = AbecedariMagPermutat[index];
            } else {
                nouText[i] = lletra;
            }
        }
        return new String(nouText);
    }
    public static String desxifraMonoAlfa(String text) {
        char[] nouText = new char[text.length()];

        for (int i = 0; i < text.length(); i++) {
            char lletra = text.charAt(i);
            if (Character.isLowerCase(lletra)) {
                int index = 0;
                for (int j = 0; j < AbecedariMinPermutat.length; j++) {
                    if (AbecedariMinPermutat[j] == lletra) {
                        index = j;
                        break;
                    }
                }
                nouText[i] = AbecedariMin[index];
            } else if (Character.isUpperCase(lletra)) {
                int index = 0;
                for (int j = 0; j < AbecedariMagPermutat.length; j++) {
                    if (AbecedariMagPermutat[j] == lletra) {
                        index = j;
                        break;
                    }
                }
                nouText[i] = AbecedariMag[index];
            } else {
                nouText[i] = lletra;
            }
        }
        return new String(nouText);
    }
}

