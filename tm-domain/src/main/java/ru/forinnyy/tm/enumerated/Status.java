package ru.forinnyy.tm.enumerated;

import lombok.Getter;
import lombok.NonNull;

@Getter
public enum Status {

    NOT_STARTED("Not started"),
    IN_PROGRESS("In progress"),
    COMPLETED("Completed");

    public static String toName(final Status status) {
        if (status == null) return "";
        return status.getDisplayName();
    }

    public static Status toStatus(final String value) {
        if (value == null || value.isEmpty()) return null;
        for (@NonNull final Status status: values()) {
            if (status.name().equals(value)) return status;
        }
        return null;
    }

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

}
