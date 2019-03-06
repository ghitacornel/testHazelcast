package client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class Client {

    private static final String mapID = "data";

    final private HazelcastInstance client;

    public Client() {
        ClientConfig config = new ClientConfig();
        config.getNetworkConfig().setConnectionAttemptPeriod(3);
        config.getNetworkConfig().setConnectionAttemptPeriod(500);
//        config.getNetworkConfig().addAddress("192.168.0.1","172.19.0.2");// for remote connections
//        config.getNetworkConfig().addOutboundPort(5701);// for remote connections
//        config.getGroupConfig().setName("dev");
//        config.getGroupConfig().setPassword("dev-pass");
        client = HazelcastClient.newHazelcastClient(config);
    }

    public HazelcastInstance getClient() {
        return client;
    }

    public IMap getMap() {
        return client.getMap(mapID);
    }
}
