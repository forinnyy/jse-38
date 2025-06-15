package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.DataJsonLoadFasterXmlRequest;
import ru.forinnyy.tm.enumerated.Role;

public final class DataJsonLoadFasterXmlCommand extends AbstractDataCommand {

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Load data from json file.";
    }

    @NonNull
    @Override
    public String getName() {
        return "data-load-json";
    }

    @NonNull
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

    @Override
    @SneakyThrows
    public void execute() {
        @NonNull final DataJsonLoadFasterXmlRequest request = new DataJsonLoadFasterXmlRequest(getToken());
        getDomainEndpoint().loadDataJsonFasterXml(request);
    }

}
