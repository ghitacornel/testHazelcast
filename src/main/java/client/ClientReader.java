package client;

import com.hazelcast.core.IMap;

public class ClientReader extends ClientTemplate {

    public IMap readDummyData() {
        return client.getMap(mapID);
    }

}
