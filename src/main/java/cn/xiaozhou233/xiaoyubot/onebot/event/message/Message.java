package cn.xiaozhou233.xiaoyubot.onebot.event.message;

import cn.xiaozhou233.xiaoyubot.onebot.event.Event;
import cn.xiaozhou233.xiaoyubot.onebot.message.Segment;

public class Message extends Event {
    protected String message_type;
    protected long message_id;
    protected long user_id;
    // TODO: Segment
    protected Segment message;
    protected String raw_message;
    protected long font;
    protected sender sender;

    protected static class sender {
        protected long user_id;
        protected String nickname;
        protected String sex;
        protected String age;
    }
}
