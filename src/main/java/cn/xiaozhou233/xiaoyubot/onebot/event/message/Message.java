package cn.xiaozhou233.xiaoyubot.onebot.event.message;

import cn.xiaozhou233.xiaoyubot.onebot.event.Event;
import cn.xiaozhou233.xiaoyubot.onebot.message.Array;
import cn.xiaozhou233.xiaoyubot.onebot.message.Segments;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Message<T extends Message.Sender> extends Event {
    @JsonProperty("message_type")
    protected String message_type;
    @JsonProperty("message_id")
    protected long message_id;
    @JsonProperty("user_id")
    protected long user_id;
    @JsonProperty("message")
    protected Segments segments;
    @JsonProperty("raw_message")
    protected String raw_message;
    @JsonProperty("font")
    protected long font;
    @JsonProperty("sender")
    protected T sender;

    public String getMessageType() {
        return message_type;
    }

    public void setMessageType(String message_type) {
        this.message_type = message_type;
    }

    public long getMessageId() {
        return message_id;
    }

    public void setMessageId(long message_id) {
        this.message_id = message_id;
    }

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = user_id;
    }

    public Segments getMessage() {
        return this.segments;
    }

    public void setMessage(ArrayList<HashMap<String, Object>> messages) {
        this.segments = new Segments(messages);
    }

    public String getRawMessage() {
        return raw_message;
    }

    public void setRawMessage(String raw_message) {
        this.raw_message = raw_message;
    }

    public long getFont() {
        return font;
    }

    public void setFont(long font) {
        this.font = font;
    }

    public T getSender() {
        return sender;
    }

    public void setSender(T sender) {
        this.sender = sender;
    }

    public static class Sender {
        @JsonProperty("user_id")
        protected long user_id;
        @JsonProperty("nickname")
        protected String nickname;
        @JsonProperty("sex")
        protected String sex;
        @JsonProperty("age")
        protected String age;

        public long getUserId() {
            return user_id;
        }

        public void setUserId(long user_id) {
            this.user_id = user_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
