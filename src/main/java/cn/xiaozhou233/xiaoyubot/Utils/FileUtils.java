package cn.xiaozhou233.xiaoyubot.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger("FileUtils");

    public static void copyFile(InputStream in, String path) {
        logger.debug("Copying file from InputStream to {}", path);
        try (OutputStream out = new FileOutputStream(path)) {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            logger.debug("Finished Copy File: {}", path);
        } catch (Exception e) {
            logger.error("Error while copying file", e);
        }
    }
}
