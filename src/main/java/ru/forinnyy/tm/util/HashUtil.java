package ru.forinnyy.tm.util;

import lombok.NonNull;
import ru.forinnyy.tm.api.component.ISaltProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface HashUtil {

    static String salt(final String value,
                       final String secret,
                       final Integer iteration
    ) {
        if (value == null || secret == null || iteration == null) return null;
        String result = value;
        for (int i = 0; i < iteration; i++) {
            result = md5(secret + result + secret);
        }
        return result;
    }

    static String salt(final ISaltProvider saltProvider,
                       final String value
    ) {
        if (saltProvider == null) return null;
        @NonNull final String secret = saltProvider.getPasswordSecret();
        @NonNull final Integer iteration = saltProvider.getPasswordIteration();
        return salt(value, secret, iteration);
    }

    @NonNull
    static String md5(@NonNull final String value) {
        try {
            @NonNull final MessageDigest md = MessageDigest.getInstance("MD5");
            @NonNull final byte[] array = md.digest(value.getBytes());
            @NonNull final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (@NonNull final NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return value;
    }

}
