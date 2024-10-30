package cn.xiaozhou233.xiaoyubot.network;

import cn.xiaozhou233.xiaoyubot.utils.MessageHandle;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WsListener extends WebSocketListener {
    private static ObjectMapper mapper = new ObjectMapper();
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        System.out.println(String.format("[INFO] Connected to WebSocket! [%s]", response.request().url()));
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        System.out.println("[Debug] Received text message: " + text);
        // 解析json
        try{
            MessageHandle.handle(mapper.readTree(text));
        }catch (Exception e){
            System.out.println("[WARN] Received message is not a valid json: " + text);
        }
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(1000, null);
        System.out.println("[WARN] WebSocket is closing: " + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        System.out.println("[ERROR] WebSocket connection failed: " + t.getMessage());
    }
}
