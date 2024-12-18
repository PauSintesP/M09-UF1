import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] output = xifraAES(msg, clau);
            return new TextXifrat(output);
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en el xifrat AES");
        }
    }

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en el desxifrat AES");
        }
    }
    

    public byte[] xifraAES(String msg, String password) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] key = sha.digest(password.getBytes("UTF-8"));

        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORISME_XIFRAT);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        return cipher.doFinal(msg.getBytes("UTF-8"));
    }

    public String desxifraAES(byte[] bMsgXifrat, String password) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] key = sha.digest(password.getBytes("UTF-8"));


        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORISME_XIFRAT);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);


        byte[] decrypted = cipher.doFinal(bMsgXifrat);
        return new String(decrypted, "UTF-8");
    }
    
    public void main(String[] args) {
        try {
            String missatge = "Missatge secret!";
            String password = CLAU;

            byte[] msgXifrat = xifraAES(missatge, password);
            System.out.println("Missatge xifrat: " + Base64.getEncoder().encodeToString(msgXifrat));

            String missatgeDesxifrat = desxifraAES(msgXifrat, password);
            System.out.println("Missatge desxifrat: " + missatgeDesxifrat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
