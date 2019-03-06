package main;


import client.Client;
import com.hazelcast.core.IMap;

public class MainClient {

    public static void main(String... args) {

        Client client = new Client();

        IMap map = client.getMap();

        System.out.println("get data >>>>>>>>> " + map.get(1));

        client.getClient().shutdown();
    }
}
