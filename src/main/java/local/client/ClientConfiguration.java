package local.client;

import com.hazelcast.client.config.ClientConfig;

enum ClientConfiguration {

    INSTANCE;

    private final ClientConfig config;

    ClientConfiguration() {
        config = new ClientConfig();
    }

    public ClientConfig getConfig() {
        return config;
    }

}
