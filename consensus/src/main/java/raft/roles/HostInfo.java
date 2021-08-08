package raft.roles;

/**
 * @author liguang
 * Created on 2021-05-23
 */
public class HostInfo {

    private long ip;
    private int port;

    public HostInfo(long ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public long getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
