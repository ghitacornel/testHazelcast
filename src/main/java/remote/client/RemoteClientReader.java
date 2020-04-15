package remote.client;

import com.hazelcast.map.IMap;

public class RemoteClientReader extends RemoteClientTemplate {

    public IMap<Object, Object> readDummyData() {
        return client.getMap(mapID);
    }

}
