package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.DataBinaryLoadRequest;
import ru.forinnyy.tm.enumerated.Role;

public final class DataBinaryLoadCommand extends AbstractDataCommand {

    @NonNull
    public static final String NAME = "data-load-bin";

    @Override
    @SneakyThrows
    public void execute() {
        @NonNull final DataBinaryLoadRequest request = new DataBinaryLoadRequest(getToken());
        getDomainEndpoint().loadDataBinary(request);
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
