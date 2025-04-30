package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public final class DataBackupLoadCommand extends AbstractDataCommand {

    @NonNull
    public static final String NAME = "backup-load";

    @Override
    @SneakyThrows
    public void execute() {
        @NonNull final byte[] base64Byte = Files.readAllBytes(Paths.get(FILE_BACKUP));
        final String base64Date = new String(base64Byte);
        final byte[] bytes = Base64.getDecoder().decode(base64Date);
        @NonNull final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        @NonNull final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        @NonNull final Domain domain = (Domain) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        setDomain(domain);
    }

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Load backup from file.";
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

