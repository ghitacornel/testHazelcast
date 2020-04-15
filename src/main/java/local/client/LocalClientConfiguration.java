package local.client;

import com.hazelcast.client.config.ClientConfig;

enum LocalClientConfiguration {

    INSTANCE;

    private final ClientConfig config;

    LocalClientConfiguration() {
        config = new ClientConfig();
    }

    public ClientConfig getConfig() {
        return config;
    }

}
