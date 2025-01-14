import cn.xiaozhou233.xiaoyubot.onebot.event.message.GroupMessage;
import cn.xiaozhou233.xiaoyubot.onebot.event.message.PrivateMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testDeserializeGroupMessage() throws Exception {
        String groupMessageJson = """
        {
          "time": 1672549200,
          "self_id": 123456789,
          "post_type": "message",
          "message_type": "group",
          "message_id": 54321,
          "user_id": 987654321,
          "group_id": 123456789,
          "anonymous": {
            "id": 11111,
            "name": "AnonymousUser",
            "flag": "some-flag"
          },
          "message": {
            "type": "text",
            "data": {
              "text": "Hello, world!"
            }
          },
          "raw_message": "Hello, world!",
          "font": 123,
          "sender": {
            "user_id": 987654321,
            "nickname": "John Doe",
            "sex": "male",
            "age": "25",
            "card": "GroupCard",
            "area": "China",
            "level": "3",
            "role": "member",
            "title": "Newbie"
          }
        }
        """;

        // 反序列化 JSON 到 GroupMessage 对象
        GroupMessage groupMessage = objectMapper.readValue(groupMessageJson, GroupMessage.class);

        // 验证反序列化结果
        assertNotNull(groupMessage);
        assertEquals(123456789, groupMessage.getGroupId());
        assertEquals("Hello, world!", groupMessage.getRawMessage());
        assertNotNull(groupMessage.getAnonymous());
        assertEquals("AnonymousUser", groupMessage.getAnonymous().getName());
        assertNotNull(groupMessage.getSender());
        assertEquals("John Doe", groupMessage.getSender().getNickname());
    }

    @Test
    void testDeserializePrivateMessage() throws Exception {
        String privateMessageJson = """
        {
          "time": 1672549200,
          "self_id": 123456789,
          "post_type": "message",
          "message_type": "private",
          "message_id": 54322,
          "user_id": 987654321,
          "message": {
            "type": "text",
            "data": {
              "text": "This is a private message!"
            }
          },
          "raw_message": "This is a private message!",
          "font": 123,
          "sender": {
            "user_id": 987654321,
            "nickname": "Jane Doe",
            "sex": "female",
            "age": "22"
          }
        }
        """;

        // 反序列化 JSON 到 PrivateMessage 对象
        PrivateMessage privateMessage = objectMapper.readValue(privateMessageJson, PrivateMessage.class);

        // 验证反序列化结果
        assertNotNull(privateMessage);
        assertEquals("private", privateMessage.getMessageType());
        assertEquals("This is a private message!", privateMessage.getRawMessage());
        assertNotNull(privateMessage.getSender());
        assertEquals("Jane Doe", privateMessage.getSender().getNickname());
    }
}
