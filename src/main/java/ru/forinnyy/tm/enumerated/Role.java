package ru.forinnyy.tm.enumerated;

import lombok.Getter;

@Getter
public enum Role {

    USUAL("Usual user"),
    ADMIN("Administrator");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

}
