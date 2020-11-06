package me.lab.in.action.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class EncryptUtils {
    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;
    private static final int AES_KEY_BIT = 256;

    private static final Charset UTF_8 = StandardCharsets.UTF_8;
	
    private static String strKey = "this is pw";

    private static byte[] iv = Arrays.copyOfRange("this is iv".getBytes(), 0, IV_LENGTH_BYTE);
    
    public static String aesEncrypt(String source) throws Exception {
        if (source == null || source.length() == 0){
            return null;
        }
        SecretKey secretKey = CryptoUtils.getAESKeyFromPassword(strKey.toCharArray(), "test".getBytes(UTF_8));
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO, "SunJCE");
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);
        byte[] encryptedText = cipher.doFinal(source.getBytes(UTF_8));
        
        return byte2hex(encryptedText).toUpperCase();
    }

    public static String byte2hex(byte[] inStr) {
        String stmp;
        StringBuffer out = new StringBuffer(inStr.length * 2);
        for (int n = 0; n < inStr.length; n++) {
            stmp = Integer.toHexString(inStr[n] & 0xFF);
            if (stmp.length() == 1) {
                out.append("0" + stmp);
            } else {
                out.append(stmp);
            }
        }
        return out.toString();
    }


    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0){
            throw new IllegalArgumentException("not even");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    public static String aesDecrypt(String source) throws Exception {
        if (source == null || source.length() == 0){
            return null;
        }
        SecretKey secretKey = CryptoUtils.getAESKeyFromPassword(strKey.toCharArray(), "test".getBytes(UTF_8));
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] retByte = cipher.doFinal(source.getBytes(UTF_8));
        
        return new String(retByte);
    }

    public static String encryptPassword(String password){
        return  DigestUtils.md5DigestAsHex(password.getBytes());
    }
   
}
