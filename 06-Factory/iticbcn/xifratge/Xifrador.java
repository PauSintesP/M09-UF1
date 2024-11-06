package iticbcn.xifratge;

public interface Xifrador {
    TextXifrat xifra(String msg, String clau) throws ClauNoSuportada;
    String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada;
}

class TextXifrat {
    private final byte[] bytes;

    public TextXifrat(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return new String(bytes);
    }
}

class ClauNoSuportada extends Exception {
    public ClauNoSuportada(String message) {
        super(message);
    }
}
