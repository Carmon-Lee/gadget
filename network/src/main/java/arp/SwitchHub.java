package arp;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2021-05-09
 * 交换机
 */
@Slf4j
public class SwitchHub {

    private String hubId;

    private Map<String, NetworkNode> ipAddrMap;
    private Map<String, NetworkNode> macAddrMap;

    public SwitchHub(String hubId) {
        this.hubId = hubId;
        ipAddrMap = new HashMap<>();
        macAddrMap = new HashMap<>();
    }

    public void register(NetworkNode node) {
        log.info("node [{}] registered to hub:[{}]", node, hubId);
        ipAddrMap.put(node.getIpAddr(), node);
        macAddrMap.put(node.getMacAddr(), node);
    }

    public void heartBeat(NetworkNode node) {

    }


    public void broadCast() {

    }

    public void findMacOfIp(String ip) {

    }
}
