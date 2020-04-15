package tests.docker;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestMultipleNodes {

    private static final String mapID = "data";

    HazelcastInstance hazelcastInstance;

    @Before
    public void setUp() {
        ClientConfig config = new ClientConfig();
        ClientNetworkConfig network = config.getNetworkConfig();
        network.addAddress("192.168.0.1", "localhost");

        hazelcastInstance = HazelcastClient.newHazelcastClient(config);
    }

    @After
    public void tearDown() {
        hazelcastInstance.shutdown();
    }

    public void writeDummyData() {
        IMap<Object, Object> data = hazelcastInstance.getMap(mapID);
        data.putAll(buildDummyData());
    }

    private Map<Object, Object> buildDummyData() {
        Map<Object, Object> data = new HashMap<>();
        data.put(1, "one " + this);
        data.put(2, "two " + this);
        data.put(3, "three " + this);
        return data;
    }

    @Test
    public void testMultipleReadFromNewlyAddedNode() {

        writeDummyData();

        // read and verify
        {
            IMap<Object, Object> map = hazelcastInstance.getMap(mapID);
            Assert.assertEquals(buildDummyData(), map);
        }

        System.out.println("start instance 3");

        // read and verify
        {
            IMap<Object, Object> map = hazelcastInstance.getMap(mapID);
            Assert.assertEquals(buildDummyData(), map);
        }

        System.out.println("stop instance 1");

        // read and verify
        {
            IMap<Object, Object> map = hazelcastInstance.getMap(mapID);
            Assert.assertEquals(buildDummyData(), map);
        }

        System.out.println("stop instance 2");

        // read and verify again
        {
            IMap<Object, Object> map = hazelcastInstance.getMap(mapID);
            Assert.assertEquals(buildDummyData(), map);
        }

    }
}
