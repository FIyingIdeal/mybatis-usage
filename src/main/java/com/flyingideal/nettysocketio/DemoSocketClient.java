package com.flyingideal.nettysocketio;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

        final Socket socket = IO.socket("http://192.168.3.20:9082/?token=123", options);
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            public void call(Object... objects) {
                // socket.send("hello"); // send方法本身就是调用了emit方法，但调用send的话，默认没有注册回调（Ack），服务器中的回调方法不会被执行
                /*socket.emit(Socket.EVENT_MESSAGE, new Object[]{"Hello"}, new Ack() {
                    public void call(Object... args) {
                        Arrays.stream(args).forEach(arg -> System.out.println("----ack" + arg.toString()));
                    }
                });*/
                /*socket.emit(Socket.EVENT_MESSAGE, new Object[]{"Hello"},
                        args -> Arrays.stream(args).forEach(arg -> System.out.println("----ack" + arg.toString())));*/
                for (int i = 3; i >= 0; i--) {
                    Map<String, String> data = new HashMap<>();
                    data.put("designerId", "666");
                    data.put("designerUuid", "888");
                    data.put("operatorId", "6868");
                    socket.emit("analysis_response_message", data);
                }
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            public void call(Object... objects) {
                System.out.println("连接关闭");
            }
        }).on("analysis_response_message", new Emitter.Listener() {
            // 如果服务端在sendEvent()中设置了AckCallback参数的话，则在objects中的最后一个元素就是一个Ack对象
            public void call(Object... objects) {
                handlerResult(objects);
                // socket.disconnect();
            }
        });

        socket.connect();
    }

    public static void handlerResult(Object... objects) {
        // 如果服务端在sendEvent()中设置了AckCallback参数的话，则在objects中的最后一个元素就是一个Ack对象
        int length = objects.length;
        if (length == 1) {
            print(objects[0]);
        } else if (length > 1) {
            if (objects[length - 1] instanceof Ack) {
                ((Ack) objects[length - 1]).call(true);
            }
            for (int i = 0; i < length - 1; i++) {
                print(objects[i]);
            }
        } else {
            System.err.println("没有数据或数据异常");
        }
    }

    public static void print(Object object) {
        if (object instanceof String) {
            System.out.println(String.valueOf(object));
        } else if (object instanceof Map) {
            System.out.println((Map)object);
        } else {
            System.out.println(object.toString());
        }
    }
}
