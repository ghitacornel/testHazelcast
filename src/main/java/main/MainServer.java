package main;

import node.ServerNode;

public class MainServer {

    /**
     * test with multiple servers started
     * @param args
     */
    public static void main(String... args) {
        ServerNode serverNode = new ServerNode();
        serverNode.startNode();
        serverNode.populateRandomData();
    }
}
