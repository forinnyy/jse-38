package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.DataYamlLoadFasterXmlRequest;
import ru.forinnyy.tm.enumerated.Role;

public final class DataYamlLoadFasterXmlCommand extends AbstractDataCommand {

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Load data from yaml file.";
    }

    @NonNull
    @Override
    public String getName() {
        return "data-load-yaml";
    }

    @NonNull
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

    @Override
    @SneakyThrows
    public void execute() {
        @NonNull final DataYamlLoadFasterXmlRequest request = new DataYamlLoadFasterXmlRequest(getToken());
        getDomainEndpoint().loadDataYamlFasterXml(request);
    }

}
