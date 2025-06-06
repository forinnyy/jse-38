package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.DataBackupLoadRequest;
import ru.forinnyy.tm.enumerated.Role;

public final class DataBackupLoadCommand extends AbstractDataCommand {

    @NonNull
    public static final String NAME = "backup-load";

    @Override
    @SneakyThrows
    public void execute() {
        @NonNull final DataBackupLoadRequest request = new DataBackupLoadRequest();
        getDomainEndpoint().loadDataBackup(request);
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

