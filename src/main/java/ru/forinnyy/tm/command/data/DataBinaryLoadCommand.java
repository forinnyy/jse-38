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

    @SneakyThrows
    @Override
    public void execute() {
        System.out.println("[DATA BINARY LOAD]");
        @Cleanup @NonNull final FileInputStream fileInputStream = new FileInputStream(FILE_BINARY);
        @Cleanup @NonNull final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        @NonNull final Domain domain = (Domain) objectInputStream.readObject();
        setDomain(domain);
    }

    @Override
    public String getArgument() {
        return null;
    }

    @Override
    public @NonNull String getDescription() {
        return "Load data from binary file.";
    }

    @Override
    public @NonNull String getName() {
        return NAME;
    }

    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }
}
