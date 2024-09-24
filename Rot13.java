public class Rot13 {
    private static final char[] LLETRES = {'a', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm', 'n', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] LLETRES_MAYUS = {'A', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L', 'M', 'N', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V', 'W', 'X', 'Y', 'Z'};

    public String xifraRot13(String text) {
        String resultat = "";
        char[] textArray = text.toCharArray();
        for (int i = 0; i < textArray.length; i++) {
            char c = textArray[i];
            if (Character.isLowerCase(c)) {
                int index = (c - 'a' + 13) % 26;
                resultat += LLETRES[index];
            } else if (Character.isUpperCase(c)) {
                int index = (c - 'A' + 13) % 26;
                resultat += LLETRES_MAYUS[index];
            } else {
                resultat += c;
            }
        }
        return resultat;
    }

    public String desxifraRot13(String text){
        String resultat = "";
        char[] textArray = text.toCharArray();
        for (int i = 0; i < textArray.length; i++) {
            char c = textArray[i];
            if (Character.isLowerCase(c)) {
                int index = (c - 'a' - 13) % 26;
                if (index < 0) index += 26;
                resultat += LLETRES[index];
            } else if (Character.isUpperCase(c)) {
                int index = (c - 'A' - 13) % 26;
                if (index < 0) index += 26;
                resultat += LLETRES_MAYUS[index];
            } else {
                resultat += c;
            }
        }
        return resultat;
    }
}
