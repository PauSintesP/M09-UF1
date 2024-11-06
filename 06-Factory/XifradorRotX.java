package iticbcn.xifratge;
import java.util.Scanner;

public class XifradorRotX {
     final char[] lletresMin = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','à','è','é','í','ï','ò','ó','ú','ü'};
     final char[] lletresMaj = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','À','È','É','Í','Ï','Ò','Ó','Ú','Ü'};
    
    public  void main(String[] args) {
        System.out.println("Escriu un text perque sigui codificat");
        String text = "";
        Scanner scanner = new Scanner(System.in);
        text = scanner.nextLine();
        System.out.println("Escriu un valor per xifrar el text");
        Scanner scanner1 = new Scanner(System.in);
        int x = scanner1.nextInt();
        scanner1.close();
        scanner.close();
        System.out.println("Text codificat: " + xifraRotX(text, x));
        System.out.println("Text descodificat: " + desxifraRotX(xifraRotX(text, x),x));
        forcaBrutaRotX(xifraRotX(text, x));
    }

    public  String xifraRotX(String text, int x) {
        String nouText = "";
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLowerCase(c)) {
                int index = trobarIndex(lletresMin, c);
                if (index != -1 && index < lletresMaj.length) {
                    nouText += lletresMin[(index + x) % lletresMaj.length];
                } else {
                    nouText += c;
                }
            } else if (Character.isUpperCase(c)) {
                int index = trobarIndex(lletresMaj, c);
                if (index != -1 && index < lletresMaj.length) {
                    nouText += lletresMaj[(index + x) % lletresMaj.length];
                } else {
                    nouText += c;
                }
            } else {
                nouText += c;
            }
        }

        return  nouText;
    }

    public  String desxifraRotX(String text, int x) {
        String nouText = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLowerCase(c)) {
                int index = trobarIndex(lletresMin, c);
                if (index != -1 && index < lletresMaj.length) {
                    nouText += lletresMin[(index - x + lletresMin.length) % lletresMin.length];
                } else {
                    nouText += c;
                }
            } else if (Character.isUpperCase(c)) {
                int index = trobarIndex(lletresMaj, c);
                if (index != -1 && index < lletresMaj.length) {
                    nouText += lletresMaj[(index - x + lletresMaj.length) % lletresMaj.length];
                } else {
                    nouText+= c;
                }
            } else {
                nouText += c;
            }
        }

        return nouText;
    }

    private  int trobarIndex(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public  void forcaBrutaRotX(String text) {
        for ( int i = 0; i < lletresMin.length; i++){
            System.out.println("Xifrat amb x = " + i + ": " + desxifraRotX(text, i));
        }
    }
}