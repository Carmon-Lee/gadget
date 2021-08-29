package http.request;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liguang
 * Created on 2021-08-29
 */
public class HttpMsgWrapper {

    private String statusLine() {
        return "HTTP/1.1 200 OK";
    }

    public String responseHeader(String response) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Content-Length", StringUtils.length(response));
        StringBuilder sb = new StringBuilder();

        for (Entry<String, Object> entry : headers.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append("\r\n");
        }

        return sb.toString();
    }

    public String httpMsg(String msg) {
        return String.format("%s\r\n%s\r\n%s\r\n", statusLine(), responseHeader(msg), msg);
    }

    public static void main(String[] args) {
        System.out.println(new HttpMsgWrapper().httpMsg("echo"));;
    }
}
