import java.util.*;
import iticbcn.xifratge.Xifrador;
import iticbcn.xifratge.TextXifrat;
import iticbcn.xifratge.ClauNoSuportada;
public class XifradorMonoalfabetic implements Xifrador {
    private final char[] AbecedariMin = "abcdefghijklmnopqrstuvwxyzàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿ".toCharArray();
    private final char[] AbecedariMag = "abcdefghijklmnopqrstuvwxyzàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿ".toUpperCase().toCharArray();
    private final char[] AbecedariMinPermutat = permutaAlfabet(AbecedariMin);
    private final char[] AbecedariMagPermutat = permutaAlfabet(AbecedariMag);

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        byte[] output = xifraMonoAlfa(msg).getBytes();
        return new TextXifrat(output);
    }

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        return desxifraMonoAlfa(new String(xifrat.getBytes()));
    }

    private char[] permutaAlfabet(char[] alfabet) {
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
    private String xifraMonoAlfa(String text) {

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
    private String desxifraMonoAlfa(String text) {
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

