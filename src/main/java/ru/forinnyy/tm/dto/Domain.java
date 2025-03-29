package ru.forinnyy.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public final class Domain implements Serializable {

    private static final long serialVersionUID = 1;

    @NonNull
    private String id = UUID.randomUUID().toString();

    @NonNull
    private Date created = new Date();

    @NonNull
    private List<User> users = new ArrayList<>();

    @NonNull
    private List<Project> projects = new ArrayList<>();

    @NonNull
    private List<Task> tasks = new ArrayList<>();

}
