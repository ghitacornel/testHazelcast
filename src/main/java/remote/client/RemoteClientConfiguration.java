package remote.client;

import com.hazelcast.client.config.ClientConfig;

enum RemoteClientConfiguration {

    INSTANCE;

    private final ClientConfig config;

    RemoteClientConfiguration() {
        config = new ClientConfig();
        config.getNetworkConfig().addAddress("127.0.0.1");// for remote connections
        config.getNetworkConfig().addOutboundPort(5701);// for remote connections
        config.getNetworkConfig().addOutboundPort(5702);// for remote connections
    }

    public ClientConfig getConfig() {
        return config;
    }

}
