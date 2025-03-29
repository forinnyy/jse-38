package ru.forinnyy.tm.command.data;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public final class DataBase64SaveCommand extends AbstractDataCommand {

    @NonNull
    public static final String NAME = "data-save-base64";

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("[DATA BASE64 SAVE]");
        @NonNull final Domain domain = getDomain();
        @NonNull final File file = new File(FILE_BASE64);
        @NonNull final Path path = file.toPath();
        Files.deleteIfExists(path);
        Files.createFile(path);

        @Cleanup @NonNull final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        @Cleanup @NonNull final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(domain);

        @NonNull final byte[] bytes = byteArrayOutputStream.toByteArray();
        @NonNull final String base64 = new BASE64Encoder().encode(bytes);

        @Cleanup @NonNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(base64.getBytes());
        fileOutputStream.flush();

    }

    @Override
    public String getArgument() {
        return null;
    }

    @Override
    public @NonNull String getDescription() {
        return "Save data to base64 file.";
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
