package ru.forinnyy.tm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractModel implements Serializable {

    @NonNull
    private String id = UUID.randomUUID().toString();

}
