package ru.forinnyy.tm.command.data;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public final class DataBinarySaveCommand extends AbstractDataCommand {

    @NonNull
    public static final String NAME = "data-save-bin";

    @SneakyThrows
    @Override
    public void execute() {
        System.out.println("[DATA SAVE BINARY]");
        @NonNull final Domain domain = getDomain();
        @NonNull final File file = new File(FILE_BINARY);
        @NonNull final Path path = file.toPath();

        Files.deleteIfExists(path);
        Files.createFile(path);

        @Cleanup @NonNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        @Cleanup @NonNull final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(domain);
    }

    @Override
    public String getArgument() {
        return null;
    }

    @Override
    public @NonNull String getDescription() {
        return "Save data to binary file.";
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
