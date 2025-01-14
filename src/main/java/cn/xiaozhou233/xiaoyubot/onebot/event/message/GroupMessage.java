package cn.xiaozhou233.xiaoyubot.onebot.event.message;

public class GroupMessage extends Message{
    private long group_id;
    private anonymous anonymous;
    private sender sender;

    private static class anonymous {
        private long id;
        private String name;
        private String flag;
    }

    public static class sender extends Message.sender {
        public String card;
        public String area;
        public String level;
        public String role;
        public String title;
    }

    // TODO: GroupMessage
    public GroupMessage(){
        this.sender = new sender();
    }
}
