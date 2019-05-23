package tests;

import client.ClientReader;
import client.ClientWriter;
import com.hazelcast.core.IMap;
import node.ServerNode;
import org.junit.*;

public class TestMultipleNodes {

    private static final ServerNode server1 = new ServerNode();
    private static final ServerNode server2 = new ServerNode();
    private static final ServerNode server3 = new ServerNode();

    private ClientWriter writer = new ClientWriter();
    private ClientReader reader = new ClientReader();

    @BeforeClass
    public static void setUpAll() {
        server1.startNode();
        server2.startNode();
        server3.startNode();
    }

    @AfterClass
    public static void tearDownAll() {
        server1.stopNode();
        server2.stopNode();
        server3.stopNode();
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
    public void testMultipleRead() {

        writer.writeDummyData();

        reader.readDummyData();

        IMap map1 = reader.readDummyData();
        Assert.assertEquals(writer.buildDummyData(), map1);
        IMap map2 = reader.readDummyData();
        Assert.assertEquals(writer.buildDummyData(), map2);
        Assert.assertSame(map1, map2);

    }
}
