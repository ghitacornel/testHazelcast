package client;

import com.hazelcast.client.config.ClientConfig;

enum ClientConfiguration {

    INSTANCE;

    private static final String mapID = "data";

    private final ClientConfig config;

    ClientConfiguration() {
        config = new ClientConfig();
        config.getNetworkConfig().setConnectionAttemptPeriod(3);
        config.getNetworkConfig().setConnectionAttemptPeriod(500);
//        config.getNetworkConfig().addAddress("192.168.0.1","172.19.0.2");// for remote connections
//        config.getNetworkConfig().addOutboundPort(5701);// for remote connections
//        config.getGroupConfig().setName("dev");
//        config.getGroupConfig().setPassword("dev-pass");
    }

    public ClientConfig getConfig() {
        return config;
    }

}
