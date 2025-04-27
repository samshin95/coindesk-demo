package com.example.coindeskdemo.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AesEncryptionUtilTest {

    @Test
    void testEncryptDecrypt() {
        String original = "HelloWorld";
        String cipher = AesEncryptionUtil.encrypt(original);
        assertNotNull(cipher);
        String decrypted = AesEncryptionUtil.decrypt(cipher);
        assertEquals(original, decrypted);
    }
}
