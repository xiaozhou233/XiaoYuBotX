package cn.xiaozhou233.xiaoyubot.network;

import cn.xiaozhou233.xiaoyubot.utils.MessageHandle;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WsListener extends WebSocketListener {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        System.out.printf("[INFO] Connected to WebSocket! [%s]%n", response.request().url());
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        System.out.println("[DEBUG] Received text message: " + text);
        // Parse JSON
        try {
            MessageHandle.handle(MAPPER.readTree(text));
        } catch (Exception e) {
            System.err.println("[WARN] Failed to parse JSON message: " + text);
            e.printStackTrace();
        }
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        System.out.println("[WARN] WebSocket is closing: " + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        System.err.println("[ERROR] WebSocket connection failed: " + t.getMessage());
        if (response != null) {
            System.err.println("[ERROR] Response code: " + response.code());
        }
    }
}
