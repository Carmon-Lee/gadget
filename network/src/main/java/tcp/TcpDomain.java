package tcp;

import java.sql.Time;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author guang.li
 * @version MyTcp.java v 1.0 2020/9/29 9:28
 */
public class TcpDomain {


    private String ip;
    private int port;
    private volatile String content;
    private TcpDomain remote;

    public TcpDomain(String ip, int port) {
        this.ip = ip;
        this.port = port;
        new Thread(() -> {
            while (true) {
                SpinUtil.spinUtil(Objects::nonNull, content);
                String receive = content;
                if (Objects.isNull(receive)) {
                    continue;
                }
                switch (receive) {
                    case "syn":

                        break;
                    case "ack":
                        break;
                    default:
                        break;
                }
                remote.content = "";
            }
        }).start();
    }


    public String connect(TcpDomain dest) {
        return send("syn", dest);
    }

    public String send(String content, TcpDomain dest) {
        SpinUtil.spinUtil(Objects::isNull, dest.content);
        dest.content = content;
        SpinUtil.spinUtil(Objects::nonNull, this.content);
        return this.content;
    }


    public String disconnect(TcpDomain dest) {
        return send("fin", dest);
    }


    public static void main(String[] args) {
        TcpDomain server = new TcpDomain("", 8080);
        TcpDomain client = new TcpDomain("", 8081);
        System.out.println(client.connect(server));
    }


}
