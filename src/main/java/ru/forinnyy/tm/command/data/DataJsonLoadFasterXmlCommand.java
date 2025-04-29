package ru.forinnyy.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;

import java.nio.file.Files;
import java.nio.file.Paths;

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

    @SneakyThrows
    @Override
    public void execute() {
        System.out.println("[DATA LOAD JSON]");
        @NonNull final byte[] bytes = Files.readAllBytes(Paths.get(FILE_JSON));
        @NonNull final String json = new String(bytes);
        @NonNull final ObjectMapper objectMapper = new JsonMapper();
        @NonNull final Domain domain = objectMapper.readValue(json, Domain.class);
        setDomain(domain);
    }

}
