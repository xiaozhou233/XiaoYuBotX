import cn.xiaozhou233.xiaoyubot.plugins.PluginManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class PluginManagerTest {
    private static File file;
    private static PluginManager pluginManager;

    @DisplayName("Init PluginManager Test")
    @BeforeAll
    static void init() {
        file = new File("src/test/resources/ExamplePluginMetaTest.jar");
        assertTrue(file.exists(), "Meta file not exist");
        pluginManager = new PluginManager();
    }

    @DisplayName("Load Plugin Test")
    @Test
    void testLoadPlugin() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        assertDoesNotThrow(() -> pluginManager.loadPlugin(file));
        assertFalse(pluginManager.getPlugins().isEmpty());
    }


    @DisplayName("Plugin Meta Read Test")
    @Test
    void testReadPluginMeta() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = PluginManager.class;
        Method method = clazz.getDeclaredMethod("getPluginMeta", File.class);
        method.setAccessible(true);
        Object result = method.invoke(pluginManager, file);
        Object expected = new PluginManager.PluginMeta("TestNamePlugin", "1.0.0", "org.example.Test.Plugin");
        assertEquals(expected, result);
    }


}
