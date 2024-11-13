package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyPairGenerator;
import javax.crypto.Cipher;


public class ClauPublica {

    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator clau = KeyPairGenerator.getInstance("RSA");
        clau.initialize(2048);
        return clau.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        return cipher.doFinal(msg.getBytes());
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] bytesDesencriptats = cipher.doFinal(msgXifrat);
        return new String(bytesDesencriptats);
    }
}