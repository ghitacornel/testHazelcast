package node;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class ServerNode {

    private HazelcastInstance instance;

    public void startNode() {
        instance = Hazelcast.newHazelcastInstance();
    }

    public void stopNode() {
        instance.shutdown();
    }

}
