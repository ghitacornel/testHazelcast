package tests.local;

import local.client.LocalClientReader;
import local.client.LocalClientWriter;
import com.hazelcast.map.IMap;
import local.node.LocalServerNode;
import org.junit.*;

public class TestMultipleNodes {

    private static final LocalServerNode node1 = new LocalServerNode();
    private static final LocalServerNode node2 = new LocalServerNode();
    private static final LocalServerNode node3 = new LocalServerNode();
    private static final LocalServerNode node4 = new LocalServerNode();

    private final LocalClientWriter writer = new LocalClientWriter();
    private final LocalClientReader reader = new LocalClientReader();

    @BeforeClass
    public static void setUpAll() {
        node1.startNode();
        node2.startNode();
        node3.startNode();
//        node4.startNode(); // this is started during the test
    }

    @AfterClass
    public static void tearDownAll() {
        node1.stopNode();
        node2.stopNode();
        node3.stopNode();
        node4.stopNode();
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
        node4.startNode();

        // read and verify again
        {
            IMap<Object, Object> map = reader.readDummyData();
            Assert.assertEquals(writer.buildDummyData(), map);
        }

        // shutdown first 3 nodes
        node1.stopNode();
        node2.stopNode();
        node3.stopNode();

        // read and verify again
        {
            IMap<Object, Object> map = reader.readDummyData();
            Assert.assertEquals(writer.buildDummyData(), map);
        }

    }
}
