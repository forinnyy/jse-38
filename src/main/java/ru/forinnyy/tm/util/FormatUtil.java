package ru.forinnyy.tm.util;

public interface FormatUtil {
    
    static String formatBytes(long bytes) {
        final long kilobyte = 1024;
        final long megabyte = kilobyte * 1024;
        final long gigabyte = megabyte * 1024;
        final long terabyte = gigabyte * 1024;

        if ((bytes >= 0) && (bytes < kilobyte)) {
            return bytes + " B";
        } else if ((bytes >= kilobyte) && (bytes < megabyte)) {
            return (bytes / kilobyte) + " KB";
        } else if ((bytes >= megabyte) && (bytes < gigabyte)) {
            return (bytes / megabyte) + " MB";
        } else if ((bytes >= gigabyte) && (bytes < terabyte)) {
            return (bytes / gigabyte) + " GB";
        } else if (bytes >= terabyte) {
            return (bytes / terabyte) + " TB";
        } else {
            return bytes + " Bytes";
        }
    }

}
