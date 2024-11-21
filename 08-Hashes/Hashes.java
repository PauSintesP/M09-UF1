import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    private int npass = 0;
    private static final String CHARSET = "abcdefABCDEF1234567890!";

    public String getSHA512AmbSalt(String pw, String salt) {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            byte[] bytes = md.digest(pw.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            HexFormat hex = HexFormat.of();
            hash = hex.formatHex(bytes);
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        String hash = null;
        try {
            byte[] abSalt = salt.getBytes(java.nio.charset.StandardCharsets.UTF_8);
            KeySpec spec = new PBEKeySpec(pw.toCharArray(), abSalt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] abHash = factory.generateSecret(spec).getEncoded();
            HexFormat hex = HexFormat.of();
            hash = hex.formatHex(abHash);
        } catch (java.security.NoSuchAlgorithmException | java.security.spec.InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return hash;
    }

    public String forcaBruta(String alg, String hash, String salt) {
        npass = 0;
        char[] chars = CHARSET.toCharArray();
        int charLength = chars.length;

        for (int i = 0; i < charLength; i++) {
            for (int j = 0; j < charLength; j++) {
                for (int k = 0; k < charLength; k++) {
                    for (int l = 0; l < charLength; l++) {
                        for (int m = 0; m < charLength; m++) {
                            for (int n = 0; n < charLength; n++) {
                                String testPassword = "" + chars[i] + chars[j] + chars[k] + chars[l] + chars[m] + chars[n];
                                String generatedHash = null;

                                if (alg.equals("SHA-512")) {
                                    generatedHash = getSHA512AmbSalt(testPassword, salt);
                                } else if (alg.equals("PBKDF2")) {
                                    generatedHash = getPBKDF2AmbSalt(testPassword, salt);
                                }

                                npass++;

                                if (hash.equals(generatedHash)) {
                                    return testPassword;
                                }
                            }
                        }
                    }
                }
            }
        }

        return null;
    }
    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = (millis / (1000 * 60 * 60)) % 24;
        long days = millis / (1000 * 60 * 60 * 24);
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", days, hours, minutes, seconds, millis % 1000);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = {
            h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt)
        };

        String pwTrobat = null;
        String[] algorismes = { "SHA-512", "PBKDF2" };

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");
            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();
            System.out.printf("Pass   : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}
