package cn.xiaozhou233.xiaoyubot.onebot.message;

import cn.xiaozhou233.xiaoyubot.onebot.message.segment.Text;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.ArrayList;

public class Segments {
    private int count;
    private ArrayList<Text> textSegments = new ArrayList<>();

    private static final ObjectMapper mapper = new ObjectMapper();

    public Segments(ArrayList<HashMap<String, Object>> segments) {
        for (HashMap<String, Object> segment : segments) {
            String type = segment.get("type").toString();
            Object dataObj = segment.get("data");

            if (dataObj instanceof HashMap) {
                @SuppressWarnings("unchecked")
                HashMap<String, Object> data = (HashMap<String, Object>) dataObj;
                if ("text".equals(type)) {
                    Text text = mapper.convertValue(data, Text.class);
                    textSegments.add(text);
                }
            } else {
                throw new RuntimeException("Data is not of the expected type: HashMap<String, Object>");
            }
        }
    }

    protected int getCount(){
        return this.count;
    }

    protected void setCount(int count){
        this.count = count;
    }
}
