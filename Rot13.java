import java.util.Scanner;

public class Rot13 {
    static final char[] lletresMin = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','à','è','é','í','ï','ò','ó','ú','ü'};
    static final char[] lletresMaj = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','À','È','É','Í','Ï','Ò','Ó','Ú','Ü'};
    
    public static void main(String[] args) {
        System.out.println("Escriu un text perque sigui codiicat");
        String text = "";
        Scanner scanner = new Scanner(System.in);
        text = scanner.nextLine();
        scanner.close();
        System.out.println("Text codificat: " + xifraRot13(text));
        System.out.println("Text descodificat: " + desxifraRot13(xifraRot13(text)));
    }

    public static String xifraRot13(String text) {
        char[] nouText = new char[text.length()];
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLowerCase(c)) {
                int index = trobarIndex(lletresMin, c);
                if (index != -1 && index < 26) {
                    nouText[i] = lletresMin[(index + 13) % 26];
                } else {
                    nouText[i] = c;
                }
            } else if (Character.isUpperCase(c)) {
                int index = trobarIndex(lletresMaj, c);
                if (index != -1 && index < 26) {
                    nouText[i] = lletresMaj[(index + 13) % 26];
                } else {
                    nouText[i] = c;
                }
            } else {
                nouText[i] = c;
            }
        }

        return new String(nouText);
    }

    public static String desxifraRot13(String text) {
        char[] nouText = new char[text.length()];

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLowerCase(c)) {
                int index = trobarIndex(lletresMin, c);
                if (index != -1 && index < 26) {
                    nouText[i] = lletresMin[(index + 13) % 26];
                } else {
                    nouText[i] = c;
                }
            } else if (Character.isUpperCase(c)) {
                int index = trobarIndex(lletresMaj, c);
                if (index != -1 && index < 26) {
                    nouText[i] = lletresMaj[(index + 13) % 26];
                } else {
                    nouText[i] = c;
                }
            } else {
                nouText[i] = c;
            }
        }

        return new String(nouText);
    }

    private static int trobarIndex(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }
}
