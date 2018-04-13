package com.flyingideal.nettysocketio;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.util.Arrays;

/**
 * @author yanchao
 * @date 2018/4/3 13:33
 */
public class DemoSocketClient {

    public static void main(String[] args) throws Exception {
        IO.Options options = new IO.Options();
        options.transports = new String[]{"websocket"};
        options.reconnectionAttempts = 2;
        options.reconnectionDelay = 1000;
        options.timeout = 500;

        final Socket socket = IO.socket("http://localhost:9082/?token=123", options);
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            public void call(Object... objects) {
                // socket.send("hello"); // send方法本身就是调用了emit方法，但调用send的话，默认没有注册回调（Ack），服务器中的回调方法不会被执行
                /*socket.emit(Socket.EVENT_MESSAGE, new Object[]{"Hello"}, new Ack() {
                    public void call(Object... args) {
                        Arrays.stream(args).forEach(arg -> System.out.println("----ack" + arg.toString()));
                    }
                });*/
                socket.emit(Socket.EVENT_MESSAGE, new Object[]{"Hello"},
                        args -> Arrays.stream(args).forEach(arg -> System.out.println("----ack" + arg.toString())));
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            public void call(Object... objects) {
                System.out.println("连接关闭");
            }
        }).on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
            public void call(Object... objects) {
                System.out.println("sessionId:" + socket.id());
                int i = 0;
                // 如果服务端在sendEvent()中设置了AckCallback参数的话，则在objects中的最后一个元素就是一个Ack对象
                for (Object obj : objects) {
                    System.out.println(i + "." + obj.getClass());
                    i++;
                    // 服务端设置了的话，则最后一个元素就是Ack对象，调用其call方法可以触发服务端回调用的onSuccess()方法执行
                    if (obj instanceof Ack) {
                        ((Ack) obj).call(true);
                    }
                }
                System.out.println("收到服务器应答，关闭连接");
                // socket.disconnect();
            }
        });

        socket.connect();
    }
}
