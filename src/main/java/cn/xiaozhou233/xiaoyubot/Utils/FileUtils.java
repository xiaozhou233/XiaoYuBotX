package cn.xiaozhou233.xiaoyubot.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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

    public static void pluginConfigInit(File pluginFile, String pluginName){
        logger.debug("Initializing plugin config for {}", pluginFile.getName());
        try (JarFile jarFile = new JarFile(pluginFile)) {
            JarEntry entry = jarFile.getJarEntry("config.json");
            if (entry == null){
                logger.debug("config.json not found in {}, pass", pluginFile.getName());
                return;
            }
            copyFile(jarFile.getInputStream(entry),
                    "./plugins/"+pluginName+"/config.json");

        } catch (Exception e){
            logger.error("Error while initializing plugin config", e);
        }
    }
}
