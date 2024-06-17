package ru.forinnyy.tm.enumerated;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter
public enum Status {

    NOT_STARTED("Not started"),
    IN_PROGRESS("In progress"),
    COMPLETED("Completed");

    @Nullable
    public static String toName(final Status status) {
        if (status == null) return "";
        return status.getDisplayName();
    }

    @Nullable
    public static Status toStatus(final String value) {
        if (value == null || value.isEmpty()) return null;
        for (final Status status: values()) {
            if (status.name().equals(value)) return status;
        }
        return null;
    }

    @Nullable
    private final String displayName;

    Status(@Nullable String displayName) {
        this.displayName = displayName;
    }

}
