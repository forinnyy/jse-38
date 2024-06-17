package ru.forinnyy.tm.util;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

@NoArgsConstructor
public final class NumberUtil {

    private static final double KILOBYTE = 1024;

    private static final double MEGABYTE = KILOBYTE * 1024;

    private static final double GIGABYTE = MEGABYTE * 1024;

    private static final double TERABYTE = GIGABYTE * 1024;

    private static final String NAME_BYTES = "B";

    private static final String NAME_BYTES_LONG = "Bytes";

    private static final String NAME_KILOBYTE = "KB";

    private static final String NAME_MEGABYTE = "MB";

    private static final String NAME_GIGABYTE = "GB";

    private static final String NAME_TERABYTE = "TB";

    private static final String SEPARATOR = " ";

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.###");

    @NotNull
    private static String render(final double bytes) {
        return DECIMAL_FORMAT.format(bytes);
    }

    @NotNull
    private static String render(final long bytes, final double size) {
        return render(bytes / size);
    }

    @NotNull
    private static String render(final long bytes, final double size, final String name) {
        return render(bytes, size) + SEPARATOR + name;
    }

    @NotNull
    private static String render(final long bytes, final String name) {
        return render(bytes) + SEPARATOR + name;
    }


    @NotNull
    public static String formatBytes(final long bytes) {
        if ((bytes >= 0) && (bytes < KILOBYTE)) return render(bytes, NAME_BYTES);
        if ((bytes >= KILOBYTE) && (bytes < MEGABYTE)) return render(bytes, KILOBYTE, NAME_KILOBYTE);
        if ((bytes >= MEGABYTE) && (bytes < GIGABYTE)) return render(bytes, MEGABYTE, NAME_MEGABYTE);
        if ((bytes >= GIGABYTE) && (bytes < TERABYTE)) return render(bytes, GIGABYTE, NAME_GIGABYTE);
        if (bytes >= TERABYTE) render(bytes, TERABYTE, NAME_TERABYTE);
        return render(bytes, NAME_BYTES_LONG);
    }

}
