package main;


import client.Client;
import com.hazelcast.core.IMap;

public class MainClient {

    public static void main(String... args) {

        Client client = new Client();

        IMap map = client.getMap();

        System.out.println(map.get(1));

        client.getClient().shutdown();
    }
}
