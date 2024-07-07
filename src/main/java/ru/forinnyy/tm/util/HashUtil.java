package ru.forinnyy.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.api.component.ISaltProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface HashUtil {

    @Nullable
    static String salt(@Nullable final String value,
                       @Nullable final String secret,
                       @Nullable final Integer iteration
    ) {
        if (value == null || secret == null || iteration == null) return null;
        @Nullable String result = value;
        for (int i = 0; i < iteration; i++) {
            result = md5(secret + result + secret);
        }
        return result;
    }

    @Nullable
    static String salt(@Nullable final ISaltProvider saltProvider,
                       @Nullable final String value
    ) {
        if (saltProvider == null) return null;
        @NotNull final String secret = saltProvider.getPasswordSecret();
        @NotNull final Integer iteration = saltProvider.getPasswordIteration();
        return salt(value, secret, iteration);
    }


    @NotNull
    static String md5(@NotNull final String value) {
        try {
            @NotNull final MessageDigest md = MessageDigest.getInstance("MD5");
            @NotNull final byte[] array = md.digest(value.getBytes());
            @NotNull final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (@NotNull final NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return value;
    }

}
