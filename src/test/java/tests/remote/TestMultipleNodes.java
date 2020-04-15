package tests.remote;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import local.client.ClientReader;
import local.client.ClientWriter;
import local.node.ServerNode;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;

public class TestMultipleNodes {

    private static final ServerNode server1 = new ServerNode();
    private static final ServerNode server2 = new ServerNode();
    private static final ServerNode server3 = new ServerNode();
    private static final ServerNode server4 = new ServerNode();

    private final ClientWriter writer = new ClientWriter();
    private final ClientReader reader = new ClientReader();

    @BeforeClass
    public static void setUpAll() {
        server1.startNode();
        server2.startNode();
        server3.startNode();
//        server4.startNode(); // this is started during the test
    }

    @AfterClass
    public static void tearDownAll() {
        server1.stopNode();
        server2.stopNode();
        server3.stopNode();
        server4.stopNode();
    }

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

        // read and verify
        {
            IMap<Object, Object> map = reader.readDummyData();
            Assert.assertEquals(writer.buildDummyData(), map);
        }

        // start the 4th local.node
        server4.startNode();

        // read and verify again
        {
            IMap<Object, Object> map = reader.readDummyData();
            Assert.assertEquals(writer.buildDummyData(), map);
        }

        // shutdown first 3 nodes
        server1.stopNode();
        server2.stopNode();
        server3.stopNode();

        // read and verify again
        {
            IMap<Object, Object> map = reader.readDummyData();
            Assert.assertEquals(writer.buildDummyData(), map);
        }

    }
}
