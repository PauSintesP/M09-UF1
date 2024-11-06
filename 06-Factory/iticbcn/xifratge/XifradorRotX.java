package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {

    private int parseClau(String clau) throws ClauNoSuportada {
        try {
            return Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Format Invalid");
        }
    }
     final static char[] lletresMin = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','à','è','é','í','ï','ò','ó','ú','ü'};
     final static char[] lletresMaj = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','À','È','É','Í','Ï','Ò','Ó','Ú','Ü'};
    


     public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int x = parseClau(clau);
        byte[] output = xifraRotX(msg, x).getBytes();
        return new TextXifrat(output);
    }

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int x = parseClau(clau);
        return desxifraRotX(new String(xifrat.getBytes()), x);
    }
    private String xifraRotX(String text, int x) {
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
    

    private String desxifraRotX(String text, int x) {
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

    private void forcaBrutaRotX(String text) {
        for ( int i = 0; i < lletresMin.length; i++){
            System.out.println("Xifrat amb x = " + i + ": " + desxifraRotX(text, i));
        }
    }
}