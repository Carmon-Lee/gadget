//package com.mynetty;
//
//import java.io.IOException;
//import java.net.SocketOption;
//import java.nio.channels.SelectionKey;
//import java.nio.channels.Selector;
//import java.nio.channels.ServerSocketChannel;
//import java.nio.channels.SocketChannel;
//import java.util.concurrent.ThreadPoolExecutor;
//
///**
// * @author guang.li
// * @version MyNettyServer.java v 1.0 2020/10/8 10:18
// */
//public class MyNettyServer {
//
//
//    public static void main(String[] args) {
//
//
//        try {
//
//            ThreadPoolExecutor acceptPool = new ThreadPoolExecutor()
//            Selector acceptSelector = Selector.open();
//            Selector read = Selector.open();
//            Selector write = Selector.open();
//
//            ServerSocketChannel ss = ServerSocketChannel.open();
//            ss.configureBlocking(false);
//            ss.register(acceptSelector, SelectionKey.OP_ACCEPT);
//
//            acceptSelector.select();
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
