package tests;

import client.ClientReader;
import client.ClientWriter;
import com.hazelcast.core.IMap;
import node.ServerNode;
import org.junit.*;

import java.util.Map;

public class TestSingleNode {

    private static final ServerNode server = new ServerNode();

    private ClientWriter writer = new ClientWriter();
    private ClientReader reader = new ClientReader();

    @BeforeClass
    public static void setUpAll() {
        server.startNode();
    }

    @AfterClass
    public static void tearDownAll() {
        server.stopNode();
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
    public void testSingleRead() {

        writer.writeDummyData();

        reader.readDummyData();

        Assert.assertEquals(writer.buildDummyData(), reader.readDummyData());

    }

    @Test
    public void testTwiceRead() {

        writer.writeDummyData();

        IMap map1 = reader.readDummyData();
        Assert.assertEquals(writer.buildDummyData(), map1);
        IMap map2 = reader.readDummyData();
        Assert.assertEquals(writer.buildDummyData(), map2);
        Assert.assertSame(map1, map2);

    }

    @Test
    public void testMultipleRead() {

        writer.writeDummyData();
        Map<Object, Object> dummyData = writer.buildDummyData();

        for (int i = 0; i < 100; i++) {
            IMap map = reader.readDummyData();
            Assert.assertEquals(dummyData, map);
        }

    }
}