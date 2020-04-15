package tests.local;

import local.client.LocalClientReader;
import local.client.LocalClientWriter;
import com.hazelcast.map.IMap;
import local.node.LocalServerNode;
import org.junit.*;

import java.util.Map;

public class TestSingleNode {

    private static final LocalServerNode node = new LocalServerNode();

    private final LocalClientWriter writer = new LocalClientWriter();
    private final LocalClientReader reader = new LocalClientReader();

    @BeforeClass
    public static void setUpAll() {
        node.startNode();
    }

    @AfterClass
    public static void tearDownAll() {
        node.stopNode();
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

        IMap<Object, Object> map1 = reader.readDummyData();
        Assert.assertEquals(writer.buildDummyData(), map1);
        IMap<Object, Object> map2 = reader.readDummyData();
        Assert.assertEquals(writer.buildDummyData(), map2);
        Assert.assertSame(map1, map2);

    }

    @Test
    public void testMultipleRead() {

        writer.writeDummyData();
        Map<Object, Object> dummyData = writer.buildDummyData();

        for (int i = 0; i < 100; i++) {
            IMap<Object, Object> map = reader.readDummyData();
            Assert.assertEquals(dummyData, map);
        }

    }
}
