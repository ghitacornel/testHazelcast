package remote.client;

import com.hazelcast.map.IMap;

import java.util.HashMap;
import java.util.Map;

public class ClientWriter extends ClientTemplate {

    public void writeDummyData() {
        IMap<Object, Object> data = client.getMap(mapID);
        data.putAll(buildDummyData());
    }

    public Map<Object, Object> buildDummyData() {
        Map<Object, Object> data = new HashMap<>();
        data.put(1, "one " + this);
        data.put(2, "two " + this);
        data.put(3, "three " + this);
        return data;
    }
}
