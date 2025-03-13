package cn.xiaozhou233.xiaoyubot.handler;

import cn.xiaozhou233.xiaoyubot.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WSMsgHandler {
    private static final Logger logger = LoggerFactory.getLogger("WebsocketMessageHandler");

    public WSMsgHandler(String t) {
        JsonNode node = Utils.handleJson(t);
        String type = node.get("post_type").asText();
        switch (type){
            case "message": {
                new MessageHandler(node);
                break;
            }

            case "notice": {
                new NoticeHandler(node);
                break;
            }

            case "request": {
                new RequestHandler(node);
                break;
            }

            case "meta_event": {
                new MetaEventHandler(node);
                break;
            }

            default: {
                logger.warn("Except event type: {}", type);
                break;
            }

        }
    }
}
