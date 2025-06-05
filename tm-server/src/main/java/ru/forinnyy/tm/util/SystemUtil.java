package ru.forinnyy.tm.util;

import lombok.NonNull;

import java.lang.management.ManagementFactory;

public interface SystemUtil {

    static long getPID() {
        final String processName = ManagementFactory.getRuntimeMXBean().getName();
        if (processName != null && processName.length() > 0) {
            try {
                return Long.parseLong(processName.split("@")[0]);
            } catch (@NonNull final Exception e) {
                return 0;
            }
        }
        return 0;
    }

}
