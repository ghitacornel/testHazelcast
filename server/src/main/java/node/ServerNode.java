package node;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class ServerNode {

    private static final String mapID = "data";

    private HazelcastInstance instance;

    public void startNode() {
        instance = Hazelcast.newHazelcastInstance();
    }

    public void populateRandomData() {
        IMap<Object, Object> data = instance.getMap(mapID);
        data.put(1, "one");
        data.put(2, "two");
        data.put(3, "three");
    }
}
