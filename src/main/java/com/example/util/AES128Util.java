package com.example.util;

import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class AES128Util {

    private static final String AES_ALGORITHM = "AES";
    private static final String AES_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    // AES-128 암호화 키 (16바이트)
    private static final String AES_SECRET_KEY = "bkiok46258mlkgfj";

    public static String encrypt(String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, generateKey());
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, generateKey());
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }

    private static SecretKeySpec generateKey() {
        return new SecretKeySpec(AES_SECRET_KEY.getBytes(), AES_ALGORITHM);
    }

//    private static byte[] generateRandomKey() {
//        byte[] key = new byte[32];
//        new SecureRandom().nextBytes(key);
//        return key;
//    }

    public static void main(String[] args) {
        // Base64 인코딩하여 출력
        String encodedKey = Base64.getEncoder().encodeToString(AES_SECRET_KEY.getBytes());
        System.out.println("AES-128 Key: " + encodedKey);
    }
}