package ru.forinnyy.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface HashUtil {

    @NotNull
    String SECRET = "34534";

    @NotNull
    Integer ITERATION = 7657;

    @Nullable
    static String salt(@Nullable final String value) {
        if (value == null) return null;
        @Nullable String result = value;
        for (int i = 0; i < ITERATION; i++) {
            result = md5(SECRET + result + SECRET);
        }
        return result;
    }

    @Nullable
    static String md5(@Nullable final String value) {
        if (value == null) return null;
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
        return null;
    }

}
