package arp;

import org.apache.logging.log4j.simple.SimpleLogger;

import lombok.ToString;

/**
 * Created on 2021-05-09
 */
public class NetworkNode {

    private String ipAddr;
    private String macAddr;
    private SwitchHub hub;

    public NetworkNode(String ipAddr,
            String macAddr) {
        this.ipAddr = ipAddr;
        this.macAddr = macAddr;
    }

    public void connectToHub(SwitchHub hub) {
        this.hub = hub;
        this.hub.register(this);
    }

    public void heartBeat() {
        if (hub == null) {
            throw new RuntimeException("no hub specified, can not heartbeat");
        }
        hub.heartBeat(this);
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public SwitchHub getHub() {
        return hub;
    }

    public void setHub(SwitchHub hub) {
        this.hub = hub;
    }

    @Override
    public String toString() {
        return "NetworkNode{" +
                "ipAddr='" + ipAddr + '\'' +
                ", macAddr='" + macAddr + '\'' +
                '}';
    }
}
