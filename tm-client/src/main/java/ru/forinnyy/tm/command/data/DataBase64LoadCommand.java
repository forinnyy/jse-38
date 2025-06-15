package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.DataBase64LoadRequest;
import ru.forinnyy.tm.enumerated.Role;

public final class DataBase64LoadCommand extends AbstractDataCommand {

    @NonNull
    public static final String NAME = "data-load-base64";

    @Override
    @SneakyThrows
    public void execute() {
        @NonNull final DataBase64LoadRequest request = new DataBase64LoadRequest(getToken());
        getDomainEndpoint().loadDataBase64(request);
    }

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Load data from base64 file.";
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
