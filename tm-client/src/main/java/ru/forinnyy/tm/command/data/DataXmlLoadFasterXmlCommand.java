package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.DataXmlLoadFasterXmlRequest;
import ru.forinnyy.tm.enumerated.Role;

public final class DataXmlLoadFasterXmlCommand extends AbstractDataCommand {

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Load data from xml file.";
    }

    @NonNull
    @Override
    public String getName() {
        return "data-load-xml";
    }

    @NonNull
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

    @Override
    @SneakyThrows
    public void execute() {
        @NonNull final DataXmlLoadFasterXmlRequest request = new DataXmlLoadFasterXmlRequest(getToken());
        getDomainEndpoint().loadDataXmlFasterXml(request);
    }

}
