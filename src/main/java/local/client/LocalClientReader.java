package local.client;

import com.hazelcast.map.IMap;

public class LocalClientReader extends LocalClientTemplate {

    public IMap<Object, Object> readDummyData() {
        return client.getMap(mapID);
    }

}
