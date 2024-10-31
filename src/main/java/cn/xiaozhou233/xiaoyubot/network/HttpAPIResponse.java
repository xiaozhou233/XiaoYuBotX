package cn.xiaozhou233.xiaoyubot.network;

import com.fasterxml.jackson.databind.JsonNode;

public class HttpAPIResponse {
    protected String status;
    protected int retcode;
    protected String message;
    protected String wording;
    protected Object data;

    public HttpAPIResponse(JsonNode jsonNode) {
        this.status = jsonNode.get("status").asText();
        this.retcode = jsonNode.get("retcode").asInt();
        this.message = jsonNode.get("message").asText();
        this.wording = jsonNode.get("wording").asText();
        this.data = jsonNode.get("data");
    }

    public String getStatus() {
        return status;
    }
    public int getRetcode() {
        return retcode;
    }
    public String getMessage() {
        return message;
    }
    public String getWording() {
        return wording;
    }
    public Object getData() {
        return data;
    }

}
