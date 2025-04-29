package ru.forinnyy.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

public final class DataYamlSaveFasterXmlCommand extends AbstractDataCommand {

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Save data in yaml file.";
    }

    @NonNull
    @Override
    public String getName() {
        return "data-save-yaml";
    }

    @NonNull
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

    @SneakyThrows
    @Override
    public void execute() {
        System.out.println("[DATA SAVE YAML]");
        @NonNull final Domain domain = getDomain();
        @NonNull final File file = new File(FILE_YAML);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());
        @Cleanup @NonNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        @NonNull final ObjectMapper objectMapper = new YAMLMapper();
        @NonNull final String yaml = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain);
        fileOutputStream.write(yaml.getBytes());
        fileOutputStream.flush();
    }

}
