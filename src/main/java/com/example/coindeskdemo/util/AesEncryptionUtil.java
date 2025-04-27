package com.example.coindeskdemo.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesEncryptionUtil {

  private static final String ALG = "AES";
  // 16 字元，符合 AES-128
  private static final byte[] KEY =
    "S3cr3tP@ssw0rdKy".getBytes(StandardCharsets.UTF_8);

  public static String encrypt(String data) {
    try {
      Cipher c = Cipher.getInstance(ALG);
      SecretKeySpec k = new SecretKeySpec(KEY, ALG);
      c.init(Cipher.ENCRYPT_MODE, k);
      byte[] enc = c.doFinal(data.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(enc);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String decrypt(String enc) {
    try {
      Cipher c = Cipher.getInstance(ALG);
      SecretKeySpec k = new SecretKeySpec(KEY, ALG);
      c.init(Cipher.DECRYPT_MODE, k);
      byte[] dec = Base64.getDecoder().decode(enc);
      byte[] orig = c.doFinal(dec);
      return new String(orig, StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
