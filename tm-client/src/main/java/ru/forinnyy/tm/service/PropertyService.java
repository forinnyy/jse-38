package ru.forinnyy.tm.service;

import com.jcabi.manifests.Manifests;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.forinnyy.tm.api.service.IPropertyService;

import java.util.Properties;

@NoArgsConstructor
public final class PropertyService implements IPropertyService {

    @NonNull
    public static final String APPLICATION_FILE_NAME_KEY = "config";

    @NonNull
    public static final String APPLICATION_FILE_NAME_DEFAULT = "application.properties";

    @NonNull
    public static final String APPLICATION_VERSION_KEY = "buildNumber";

    @NonNull
    public static final String PORT = "server.port";

    @NonNull
    public static final String PORT_DEFAULT = "8080";

    @NonNull
    public static final String EMPTY_VALUE = "---";


    @NonNull
    private final Properties properties = new Properties();

    @NonNull
    private String read(final String key) {
        if (key == null || key.isEmpty()) return EMPTY_VALUE;
        if (!Manifests.exists(key)) return EMPTY_VALUE;
        return Manifests.read(key);
    }

    @NonNull
    private String getEnvKey(@NonNull final String key) {
        return key.replace(".", "_").toUpperCase();
    }

    @NonNull
    private Integer getIntegerValue(@NonNull final String key, @NonNull final String defaultValue) {
        return Integer.parseInt(getStringValue(key, defaultValue));
    }

    @NonNull
    private String getStringValue(@NonNull final String key, @NonNull final String defaultValue) {
        if (System.getProperties().containsKey(key)) return System.getProperties().getProperty(key);
        @NonNull final String envKey = getEnvKey(key);
        if (System.getenv().containsKey(envKey)) return System.getenv(envKey);
        return properties.getProperty(key, defaultValue);
    }

    @NonNull
    private String getStringValue(@NonNull final String key) {
        return getStringValue(key, EMPTY_VALUE);
    }

    @Override
    public @NonNull String getApplicationVersion() {
        return "1.1.1";
    }

    @Override
    public @NonNull String getHost() {
        return "localhost";
    }

    @Override
    public @NonNull String getPort() {
        return "8080";
    }
}
