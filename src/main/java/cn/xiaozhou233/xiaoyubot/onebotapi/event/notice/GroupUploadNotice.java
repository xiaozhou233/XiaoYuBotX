package cn.xiaozhou233.xiaoyubot.onebotapi.event.notice;

import com.fasterxml.jackson.databind.JsonNode;

// 群文件上传事件
public class GroupUploadNotice extends NoticeEvent {
    private final long groupId;
    private final long userId;

    public long groupId() {
        return groupId;
    }

    public long userId() {
        return userId;
    }

    public File file() {
        return file;
    }

    private File file;

    public GroupUploadNotice(JsonNode jsonNode) {
        super(jsonNode);
        this.groupId = jsonNode.get("group_id").asLong();
        this.userId = jsonNode.get("user_id").asLong();
        JsonNode fileNode = jsonNode.get("file");
        if (fileNode != null) {
            this.file = new File(fileNode);
        }
    }

    public static class File {

        public File(JsonNode fileNode) {
            String id = fileNode.get("id").asText();
            String name = fileNode.get("name").asText();
            long size = fileNode.get("size").asLong();
            long busid = fileNode.get("busid").asLong();
        }
    }
}
