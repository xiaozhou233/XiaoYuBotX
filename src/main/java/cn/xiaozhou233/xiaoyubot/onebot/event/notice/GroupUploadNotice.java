package cn.xiaozhou233.xiaoyubot.onebot.event.notice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupUploadNotice extends Notice {
    @JsonProperty("group_id")
    private long groupId;
    @JsonProperty("file")
    private File file;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static class File {
        @JsonProperty("id")
        private String id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("size")
        private long size;
        @JsonProperty("busid")
        private long busid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public long getBusid() {
            return busid;
        }

        public void setBusid(long busid) {
            this.busid = busid;
        }
    }
}
