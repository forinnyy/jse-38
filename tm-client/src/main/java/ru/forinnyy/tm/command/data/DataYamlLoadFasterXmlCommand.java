package ru.forinnyy.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;

import java.nio.file.Files;
import java.nio.file.Paths;

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
        getDomainService().loadDataYamlFasterXml();
    }

}
