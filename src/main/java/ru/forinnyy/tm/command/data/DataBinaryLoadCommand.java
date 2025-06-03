package ru.forinnyy.tm.command.data;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public final class DataBinaryLoadCommand extends AbstractDataCommand {

    @NonNull
    public static final String NAME = "data-load-bin";

    @Override
    @SneakyThrows
    public void execute() {
        getDomainService().loadDataBinary();
    }

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Load data from binary file.";
    }

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }

    @NonNull
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }
}
