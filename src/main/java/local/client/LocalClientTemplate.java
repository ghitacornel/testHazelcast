package local.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

public abstract class LocalClientTemplate {

    protected static final String mapID = "data";

    protected HazelcastInstance client;

    public void startClient() {
        ClientConfig config = LocalClientConfiguration.INSTANCE.getConfig();
        client = HazelcastClient.newHazelcastClient(config);
    }

    public void stopClient() {
        client.shutdown();
    }

}
