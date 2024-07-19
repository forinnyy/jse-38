package ru.forinnyy.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.api.service.IPropertyService;

import java.util.Properties;

public final class PropertyService implements IPropertyService {

    @NotNull
    public static final String FILE_NAME = "application.properties";

    @NotNull
    public static final String APPLICATION_VERSION_KEY = "application.version";

    @NotNull
    public static final String AUTHOR_EMAIL_KEY = "author.email";

    @NotNull
    public static final String AUTHOR_NAME_KEY = "author.name";

    @NotNull
    public static final String PASSWORD_ITERATION_DEFAULT = "25456";

    @NotNull
    public static final String PASSWORD_ITERATION_KEY = "password.iteration";

    @NotNull
    public static final String PASSWORD_SECRET_DEFAULT = "345345345345";

    @NotNull
    public static final String PASSWORD_SECRET_KEY = "password.secret";

    @NotNull
    public static final String EMPTY_VALUE = "--//--";

    @NotNull
    private final Properties properties = new Properties();

    @SneakyThrows
    public PropertyService() {
        properties.load(ClassLoader.getSystemResourceAsStream(FILE_NAME));
    }

    @NotNull
    @Override
    public String getApplicationVersion() {
        return getStringValue(APPLICATION_VERSION_KEY);
    }

    @NotNull
    @Override
    public String getAuthorEmail() {
        return getStringValue(AUTHOR_EMAIL_KEY);
    }

    @NotNull
    @Override
    public String getAuthorName() {
        return getStringValue(AUTHOR_NAME_KEY);
    }

    @NotNull
    private String getEnvKey(@NotNull final String key) {
        return key.replace(".", "_").toUpperCase();
    }

    @NotNull
    @Override
    public Integer getPasswordIteration() {
        return getIntegerValue(PASSWORD_ITERATION_KEY, PASSWORD_ITERATION_DEFAULT);
    }

    @NotNull
    @Override
    public String getPasswordSecret() {
        return getStringValue(PASSWORD_SECRET_KEY, PASSWORD_SECRET_DEFAULT);
    }

    @NotNull
    private Integer getIntegerValue(@NotNull final String key, @NotNull final String defaultValue) {
        return Integer.parseInt(getStringValue(key, defaultValue));
    }

    @NotNull
    private String getStringValue(@NotNull final String key, @NotNull final String defaultValue) {
        if (System.getProperties().containsKey(key)) return System.getProperties().getProperty(key);
        @NotNull final String envKey = getEnvKey(key);
        if (System.getenv().containsKey(envKey)) return System.getenv(envKey);
        return properties.getProperty(key, defaultValue);
    }

    @NotNull
    private String getStringValue(@NotNull final String key) {
        return getStringValue(key, EMPTY_VALUE);
    }

}
