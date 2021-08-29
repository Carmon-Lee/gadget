package http.request;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liguang
 * Created on 2021-08-27
 */
public class RequestProcessor {

    private static final char separator = ' ';
    private StringMsgHandler handler;

    public RequestProcessor() {
        this.handler = new StringMsgHandler();
    }

    public String processCommand(String command) {
        String uri = parseUri(command);
        return handler.handleRequest(uri);
    }

    private String parseUri(String command) {
        String[] split = StringUtils.split(command, ' ');
        if (split.length < 2) {
            return null;
        }
        return split[1];
    }
}
