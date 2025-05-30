package cn.xiaozhou233.xiaoyubot.Config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Config {
    private static final Logger logger = LoggerFactory.getLogger("Config");
    private static final ObjectMapper mapper = new ObjectMapper();
    private final File configFile;
    private JsonNode node;
    public Config(File configFile){
        this.configFile = configFile;
        if (!configFile.exists()) return;
        refresh();
    }

    public JsonNode getNode() {
        return node;
    }

    public String getString(String key){
        return node.get(key).asText();
    }

    public int getInt(String key){
        return node.get(key).asInt();
    }

    public long getLong(String key){
        return node.get(key).asLong();
    }

    public boolean getBoolean(String key){
        return node.get(key).asBoolean();
    }

    public List<?> getList(String key){
        if (node.get(key) instanceof List<?> list){
            return list;
        }
        throw new RuntimeException("Config key " + key + " is not a list");
    }

    public JsonNode get(String key){
        return node.get(key);
    }

    private void refresh(){
        try {
            node = mapper.readTree(configFile);
        } catch (IOException e) {
            logger.error("Cannot refresh config {}", configFile.getAbsoluteFile());
            throw new RuntimeException(e);
        }
    }
}
