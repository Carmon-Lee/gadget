package http;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author liguang
 * Created on 2021-08-30
 */
public class HttpRequestHandler extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add("hello");
        ctx.writeAndFlush("fni");
        System.out.println("request");
    }
}
