package ru.forinnyy.tm.util;

import lombok.NonNull;
import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public interface CryptUtil {

    @NonNull
    String TYPE = "AES/ECB/PKCS5Padding";

    @NonNull
    @SneakyThrows
    static SecretKeySpec getKey(@NonNull final String secretKey) {
        @NonNull final MessageDigest sha = MessageDigest.getInstance("SHA-1");
        @NonNull final byte[] key = secretKey.getBytes("UTF-8");
        @NonNull final byte[] digest = sha.digest(key);
        @NonNull final byte[] secret = Arrays.copyOf(digest, 16);
        return new SecretKeySpec(secret, "AES");
    }

    @NonNull
    @SneakyThrows
    static String encrypt(@NonNull final String secret, @NonNull final String strToEncrypt) {
        @NonNull final SecretKeySpec secretKey = getKey(secret);
        @NonNull final Cipher cipher = Cipher.getInstance(TYPE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        @NonNull final byte[] bytes = strToEncrypt.getBytes("UTF-8");
        return Base64.getEncoder().encodeToString(cipher.doFinal(bytes));
    }

    @NonNull
    @SneakyThrows
    static String decrypt(@NonNull final String secret, @NonNull final String strToDecrypt) {
        getKey(secret);
        @NonNull final Cipher cipher = Cipher.getInstance(TYPE);
        cipher.init(Cipher.DECRYPT_MODE, getKey(secret));
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }

}
