package remote.client;

import com.hazelcast.client.config.ClientConfig;

enum RemoteClientConfiguration {

    INSTANCE;

    private final ClientConfig config;

    RemoteClientConfiguration() {
        config = new ClientConfig();
    }

    public ClientConfig getConfig() {
        return config;
    }

}
