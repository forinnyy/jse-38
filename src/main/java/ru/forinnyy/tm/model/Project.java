package ru.forinnyy.tm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.NonNull;
import ru.forinnyy.tm.api.model.IWBS;
import ru.forinnyy.tm.enumerated.Status;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public final class Project extends AbstractUserOwnedModel implements IWBS {

    private static final long serialVersionUID = 1;

    @NonNull
    private String name = "";

    @NonNull
    private String description = "";

    @NonNull
    private Status status = Status.NOT_STARTED;

    @NonNull
    private Date created = new Date();

    public Project(@NonNull final String name, @NonNull final Status status) {
        this.name = name;
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " : " + description;
    }

}
