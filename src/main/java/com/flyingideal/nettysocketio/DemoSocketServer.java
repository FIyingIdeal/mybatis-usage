package com.flyingideal.nettysocketio;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import io.socket.client.Socket;

/**
 * @author yanchao
 * @date 2018/4/3 11:31
 */
public class DemoSocketServer {

    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        final SocketIOServer server = new SocketIOServer(config);
        // 添加connection事件监听器
        server.addConnectListener(new ConnectListener() {
            public void onConnect(SocketIOClient socketIOClient) {
                String token = socketIOClient.getHandshakeData().getUrlParams()
                        .get("token").get(0);
                System.out.println("token = " + token);
            }
        });

        server.addEventListener(Socket.EVENT_MESSAGE, String.class, new DataListener<String>() {
            public void onData(SocketIOClient socketIOClient, String data, AckRequest ackRequest) throws Exception {
                System.out.println("client data:" + data);
                server.getBroadcastOperations().sendEvent(Socket.EVENT_MESSAGE, "hi", new BroadcastAckCallback<Boolean>(Boolean.class));
                System.out.println("AckRequest: " + ackRequest.isAckRequested());
            }
        });

        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        server.stop();
    }
}
