package cn.xiaozhou233.xiaoyubot.onebotapi.event.request;

import cn.xiaozhou233.xiaoyubot.onebotapi.api.set_friend_add_request;
import com.fasterxml.jackson.databind.JsonNode;

// 加好友请求事件类
public class FriendRequestEvent extends RequestEvent {
    public FriendRequestEvent(JsonNode jsonNode) {
        super(jsonNode);
    }

    public class FastAction {
        public void accept() {
            String flag = getFlag();
            boolean approve = true;
            new set_friend_add_request(flag, approve, "").send();
        }

        public void accept(String remark) {
            String flag = getFlag();
            boolean approve = true;
            new set_friend_add_request(flag, approve, remark).send();
        }

        public void reject() {
            String flag = getFlag();
            boolean approve = false;
            new set_friend_add_request(flag, approve, "").send();
        }
    }
}
