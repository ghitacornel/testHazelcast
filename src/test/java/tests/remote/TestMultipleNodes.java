package tests.remote;

import com.hazelcast.map.IMap;
import local.client.ClientReader;
import local.client.ClientWriter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMultipleNodes {

    private final ClientWriter writer = new ClientWriter();
    private final ClientReader reader = new ClientReader();

    @Before
    public void setUp() {
        writer.startClient();
        reader.startClient();
    }

    @After
    public void tearDown() {
        writer.stopClient();
        reader.stopClient();
    }

    @Test
    public void testMultipleReadFromNewlyAddedNode() {

        writer.writeDummyData();
        writer.stopClient();

        System.out.println("ensure node 2 is down");

        // read and verify
        {
            IMap<Object, Object> map = reader.readDummyData();
            Assert.assertEquals(writer.buildDummyData(), map);
        }

        System.out.println("start node 2");

        // read and verify
        {
            IMap<Object, Object> map = reader.readDummyData();
            Assert.assertEquals(writer.buildDummyData(), map);
        }

        System.out.println("stop node 1");

        // read and verify again
        {
            IMap<Object, Object> map = reader.readDummyData();
            Assert.assertEquals(writer.buildDummyData(), map);
        }

        System.out.println("verify only node 2 is up and running");

    }
}
