package http.request;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author liguang
 * Created on 2021-08-27
 */
public class StringMsgHandler {

    private Map<String, Function<String, String>> handlers;
    private HttpMsgWrapper msgWrapper;

    public void init() {
        handlers = new HashMap<>();
        handlers.put("/echo", this::echo);
        this.msgWrapper = new HttpMsgWrapper();
    }

    public StringMsgHandler() {
        init();
    }

    public String handleRequest(String request) {
        if (request == null) {
            return null;
        }
        String msg = handlers.getOrDefault(request, this::defaultHandler).apply(request);
        return msgWrapper.httpMsg(msg);
    }


    // **************************************************
    private String echo(String request) {
        return String.format("Request Received: %s\n", request);
    }

    private String defaultHandler(String request) {
        return String.format("Endpoint Not Found for %s\n", request);
    }


}
