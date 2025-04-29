package ru.forinnyy.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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

    @Override
    public @NonNull String getDescription() {
        return "Load data from yaml file.";
    }

    @Override
    public @NonNull String getName() {
        return "data-load-yaml";
    }

    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

    @SneakyThrows
    @Override
    public void execute() {
        System.out.println("[DATA LOAD YAML]");
        @NonNull final byte[] bytes = Files.readAllBytes(Paths.get(FILE_YAML));
        @NonNull final String yaml = new String(bytes);
        @NonNull final ObjectMapper objectMapper = new YAMLMapper();
        @NonNull final Domain domain = objectMapper.readValue(yaml, Domain.class);
        setDomain(domain);
    }

}
