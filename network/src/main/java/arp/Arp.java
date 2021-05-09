package arp;


import java.util.UUID;

public class Arp {

    public static void main(String[] args) {
        NetworkNode node1 = new NetworkNode("127.0.0.1", "fd58ca3b-012b-49c1-9a68-2ae35c2430b6");
        NetworkNode node2 = new NetworkNode("127.0.0.2", "6ce73fa2-3cdf-4e24-bb7d-32d324f9e553");

        SwitchHub hub = new SwitchHub("32d324f9e553");
        node1.connectToHub(hub);
        node2.connectToHub(hub);

    }
}
