import cn.xiaozhou233.xiaoyubot.onebot.event.message.*;
import cn.xiaozhou233.xiaoyubot.onebot.event.notice.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Map<String, String> jsons = new HashMap<>();

    @DisplayName("Init Message Test")
    @BeforeAll
    static void init() throws IOException {
        File file = new File("src/test/resources/EventExample");
        assertNotNull(file, "File not found in src/test/resources/EventExample");
        for (File listFile : Objects.requireNonNull(file.listFiles())) {
            if (listFile.getName().endsWith(".json")) {
               try (var reader = listFile.toURI().toURL().openStream()) {
                   jsons.put(listFile.getName(), new String(reader.readAllBytes()));
               }
            }
        }
    }

    @DisplayName("Group Message Test")
    @Test
    void testDeserializeGroupMessage() throws Exception {
        String groupMessageJson = jsons.get("GroupMessage.json");

        GroupMessage groupMessage = objectMapper.readValue(groupMessageJson, GroupMessage.class);

        assertNotNull(groupMessage);
        assertEquals(123456789, groupMessage.getGroupId());
        assertEquals("Hello, world!", groupMessage.getRawMessage());
        assertNotNull(groupMessage.getAnonymous());
        assertEquals("AnonymousUser", groupMessage.getAnonymous().getName());
        assertNotNull(groupMessage.getSender());
        assertEquals("John Doe", groupMessage.getSender().getNickname());
    }

    @DisplayName("Private message test")
    @Test
    void testDeserializePrivateMessage() throws Exception {
        String privateMessageJson = jsons.get("PrivateMessage.json");

        PrivateMessage privateMessage = objectMapper.readValue(privateMessageJson, PrivateMessage.class);

        assertNotNull(privateMessage);
        assertEquals("private", privateMessage.getMessageType());
        assertEquals("This is a private message!", privateMessage.getRawMessage());
        assertNotNull(privateMessage.getSender());
        assertEquals("Jane Doe", privateMessage.getSender().getNickname());
    }

    @DisplayName("Group File Upload Notice Test")
    @Test
    void testDeserializeGroupUploadNotice() throws Exception {
        String groupUploadNoticeJson = jsons.get("GroupUploadNotice.json");

        GroupUploadNotice groupUploadNotice = objectMapper.readValue(groupUploadNoticeJson, GroupUploadNotice.class);

        assertNotNull(groupUploadNotice);
        assertEquals(1672531200, groupUploadNotice.getTime());
        assertEquals(1234567890, groupUploadNotice.getSelfID());
        assertEquals("notice", groupUploadNotice.getPostType());
        assertEquals("group_upload", groupUploadNotice.getNoticeType());
        assertEquals(987654321, groupUploadNotice.getGroupId());
        assertEquals(1122334455, groupUploadNotice.getUserId());

        GroupUploadNotice.File file = groupUploadNotice.getFile();
        assertNotNull(file);
        assertEquals("abc123", file.getId());
        assertEquals("example.txt", file.getName());
        assertEquals(1024, file.getSize());
        assertEquals(54321, file.getBusid());
    }

    @DisplayName("Group Admin Change Notice Test")
    @Test
    void testDeserializeGroupAdminChangeNotice() throws Exception {
        String groupAdminChangeNoticeJson = jsons.get("GroupAdminNotice.json");

        GroupAdminNotice groupAdminNotice = objectMapper.readValue(groupAdminChangeNoticeJson, GroupAdminNotice.class);

        assertNotNull(groupAdminNotice);
        assertEquals(1672531200, groupAdminNotice.getTime());
        assertEquals(1234567890, groupAdminNotice.getSelfID());
        assertEquals("notice", groupAdminNotice.getPostType());
        assertEquals("group_admin", groupAdminNotice.getNoticeType());
        assertEquals("set", groupAdminNotice.getSubType());
        assertEquals(987654321, groupAdminNotice.getGroupId());
        assertEquals(1122334455, groupAdminNotice.getUserId());
    }

    @DisplayName("Group Member Decrease Notice Test")
    @Test
    void testDeserializeGroupMemberDecreaseNotice() throws Exception {
        String groupMemberDecreaseNoticeJson = jsons.get("GroupDecreaseNotice.json");

        GroupDecreaseNotice groupDecreaseNotice = objectMapper.readValue(groupMemberDecreaseNoticeJson, GroupDecreaseNotice.class);

        assertNotNull(groupDecreaseNotice);
        assertEquals(1672531200, groupDecreaseNotice.getTime());
        assertEquals(1234567890, groupDecreaseNotice.getSelfID());
        assertEquals("notice", groupDecreaseNotice.getPostType());
        assertEquals("group_decrease", groupDecreaseNotice.getNoticeType());
        assertEquals("leave", groupDecreaseNotice.getSubType());
        assertEquals(987654321, groupDecreaseNotice.getGroupId());
        assertEquals(1122334455, groupDecreaseNotice.getOperatorId());
        assertEquals(1122334455, groupDecreaseNotice.getUserId());
    }

    @DisplayName("Group Member Increase Notice Test")
    @Test
    void testDeserializeGroupMemberIncreaseNotice() throws Exception {
        String groupMemberIncreaseNoticeJson = jsons.get("GroupIncreaseNotice.json");

        GroupIncreaseNotice groupIncreaseNotice = objectMapper.readValue(groupMemberIncreaseNoticeJson, GroupIncreaseNotice.class);

        assertNotNull(groupIncreaseNotice);
        assertEquals(1672531200, groupIncreaseNotice.getTime());
        assertEquals(1234567890, groupIncreaseNotice.getSelfID());
        assertEquals("notice", groupIncreaseNotice.getPostType());
        assertEquals("group_increase", groupIncreaseNotice.getNoticeType());
        assertEquals("approve", groupIncreaseNotice.getSubType());
        assertEquals(987654321, groupIncreaseNotice.getGroupId());
        assertEquals(1122334455, groupIncreaseNotice.getOperatorId());
        assertEquals(5566778899L, groupIncreaseNotice.getUserId());
    }

    @DisplayName("Group Ban Notice Test")
    @Test
    void testDeserializeGroupBanNotice() throws Exception {
        String groupBanNoticeJson = jsons.get("GroupBanNotice.json");

        GroupBanNotice groupBanNotice = objectMapper.readValue(groupBanNoticeJson, GroupBanNotice.class);

        assertNotNull(groupBanNotice);
        assertEquals(1672531200, groupBanNotice.getTime());
        assertEquals(1234567890, groupBanNotice.getSelfID());
        assertEquals("notice", groupBanNotice.getPostType());
        assertEquals("group_ban", groupBanNotice.getNoticeType());
        assertEquals("ban", groupBanNotice.getSubType());
        assertEquals(987654321, groupBanNotice.getGroupId());
        assertEquals(1122334455, groupBanNotice.getOperatorId());
        assertEquals(5566778899L, groupBanNotice.getUserId());
        assertEquals(3600, groupBanNotice.getDuration());
    }

    @DisplayName("Friend Add Notice Test")
    @Test
    void testDeserializeFriendAddNotice() throws Exception {
        String friendAddNoticeJson = jsons.get("FriendAddNotice.json");

        FriendAddNotice friendAddNotice = objectMapper.readValue(friendAddNoticeJson, FriendAddNotice.class);

        assertNotNull(friendAddNotice);
        assertEquals(1672531200, friendAddNotice.getTime());
        assertEquals(1234567890, friendAddNotice.getSelfID());
        assertEquals("notice", friendAddNotice.getPostType());
        assertEquals("friend_add", friendAddNotice.getNoticeType());
        assertEquals(5566778899L, friendAddNotice.getUserId());
    }

    @DisplayName("Group Message Recall Notice Test")
    @Test
    void testDeserializeGroupMessageRecallNotice() throws Exception {
        String groupMessageRecallNoticeJson = jsons.get("GroupRecallNotice.json");

        GroupRecallNotice groupMessageRecallNotice = objectMapper.readValue(groupMessageRecallNoticeJson, GroupRecallNotice.class);

        assertNotNull(groupMessageRecallNotice);
        assertEquals(1672531200, groupMessageRecallNotice.getTime());
        assertEquals(1234567890, groupMessageRecallNotice.getSelfID());
        assertEquals("notice", groupMessageRecallNotice.getPostType());
        assertEquals("group_recall", groupMessageRecallNotice.getNoticeType());
        assertEquals(987654321, groupMessageRecallNotice.getGroupId());
        assertEquals(1122334455, groupMessageRecallNotice.getUserId());
        assertEquals(5566778899L, groupMessageRecallNotice.getOperatorId());
        assertEquals(99887766, groupMessageRecallNotice.getMessageId());
    }

    @DisplayName("Friend Message Recall Notice Test")
    @Test
    void testDeserializeFriendMessageRecallNotice() throws Exception {
        String friendMessageRecallNoticeJson = jsons.get("FriendRecallNotice.json");

        FriendRecallNotice friendMessageRecallNotice = objectMapper.readValue(friendMessageRecallNoticeJson, FriendRecallNotice.class);

        assertNotNull(friendMessageRecallNotice);
        assertEquals(1672531200, friendMessageRecallNotice.getTime());
        assertEquals(1234567890, friendMessageRecallNotice.getSelfID());
        assertEquals("notice", friendMessageRecallNotice.getPostType());
        assertEquals("friend_recall", friendMessageRecallNotice.getNoticeType());
        assertEquals(1122334455, friendMessageRecallNotice.getUserId());
        assertEquals(99887766, friendMessageRecallNotice.getMessageId());
    }

    @DisplayName("Group Poke Notice Test")
    @Test
    void testDeserializeGroupPokeNotice() throws Exception {
        String groupPokeNoticeJson = jsons.get("GroupPokeNotice.json");

        GroupPokeNotice groupPokeNotice = objectMapper.readValue(groupPokeNoticeJson, GroupPokeNotice.class);

        assertNotNull(groupPokeNotice);
        assertEquals(1672531200, groupPokeNotice.getTime());
        assertEquals(1234567890, groupPokeNotice.getSelfID());
        assertEquals("notice", groupPokeNotice.getPostType());
        assertEquals("notify", groupPokeNotice.getNoticeType());
        assertEquals("poke", groupPokeNotice.getSubType());
        assertEquals(987654321, groupPokeNotice.getGroupId());
        assertEquals(1122334455, groupPokeNotice.getUserId());
        assertEquals(5566778899L, groupPokeNotice.getTargetId());
    }

    @DisplayName("Group Lucky King Notice Test")
    @Test
    void testDeserializeGroupLuckyKingNotice() throws Exception {
        String groupLuckyKingNoticeJson = jsons.get("GroupLuckyKingNotice.json");

        GroupLuckyKingNotice groupLuckyKingNotice = objectMapper.readValue(groupLuckyKingNoticeJson, GroupLuckyKingNotice.class);

        assertNotNull(groupLuckyKingNotice);
        assertEquals(1672531200, groupLuckyKingNotice.getTime());
        assertEquals(1234567890, groupLuckyKingNotice.getSelfID());
        assertEquals("notice", groupLuckyKingNotice.getPostType());
        assertEquals("notify", groupLuckyKingNotice.getNoticeType());
        assertEquals("lucky_king", groupLuckyKingNotice.getSubType());
        assertEquals(987654321, groupLuckyKingNotice.getGroupId());
        assertEquals(1122334455, groupLuckyKingNotice.getUserId());
        assertEquals(5566778899L, groupLuckyKingNotice.getTargetId());
    }

    @DisplayName("Group Member Honor Change Notice Test")
    @Test
    void testDeserializeGroupMemberHonorChangeNotice() throws Exception {
        String groupMemberHonorChangeNoticeJson = jsons.get("GroupHonorChangeNotice.json");

        GroupHonorChangeNotice groupMemberHonorChangeNotice = objectMapper.readValue(groupMemberHonorChangeNoticeJson, GroupHonorChangeNotice.class);

        assertNotNull(groupMemberHonorChangeNotice);
        assertEquals(1672531200, groupMemberHonorChangeNotice.getTime());
        assertEquals(1234567890, groupMemberHonorChangeNotice.getSelfID());
        assertEquals("notice", groupMemberHonorChangeNotice.getPostType());
        assertEquals("notify", groupMemberHonorChangeNotice.getNoticeType());
        assertEquals("honor", groupMemberHonorChangeNotice.getSubType());
        assertEquals(987654321, groupMemberHonorChangeNotice.getGroupId());
        assertEquals("talkative", groupMemberHonorChangeNotice.getHonorType());
        assertEquals(1122334455, groupMemberHonorChangeNotice.getUserId());
    }

}
