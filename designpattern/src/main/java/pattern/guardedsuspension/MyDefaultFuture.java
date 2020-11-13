package pattern.guardedsuspension;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author guang.li
 * @version MyDefaultFuture.java v 1.0 2020/11/10 19:18
 */
public class MyDefaultFuture<T> extends CompletableFuture<Object> {

    private Map<Long, MyDefaultFuture<T>> FUTURES = new ConcurrentHashMap<>();

    // 通过NIO收到响应，根据reqId获取到对应的请求
    public void received(Long reqId, Object response) {
        MyDefaultFuture<T> future = FUTURES.get(reqId);
        if (future != null) {
            future.complete(response);
        }
    }


    public MyDefaultFuture<T> send(Long reqId) {
        MyDefaultFuture<T> future = new MyDefaultFuture<>();
        FUTURES.put(reqId, future);
        // 通过NIO发送网络IO
        return future;
    }
}
