package remote.client;

import com.hazelcast.client.config.ClientConfig;

enum ClientConfiguration {

    INSTANCE;

    private final ClientConfig config;

    ClientConfiguration() {
        config = new ClientConfig();
        config.getNetworkConfig().addAddress("192.168.0.1");// for remote connections
        config.getNetworkConfig().addOutboundPort(5701);// for remote connections
        config.getNetworkConfig().addOutboundPort(5702);// for remote connections
    }

    public ClientConfig getConfig() {
        return config;
    }

}
