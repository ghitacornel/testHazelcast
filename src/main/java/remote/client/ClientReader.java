package remote.client;

import com.hazelcast.map.IMap;

public class ClientReader extends ClientTemplate {

    public IMap<Object, Object> readDummyData() {
        return client.getMap(mapID);
    }

}
