package cn.xiaozhou233.xiaoyubot.Plugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginClassLoader extends URLClassLoader {
    public PluginClassLoader(File jar, ClassLoader parent) throws MalformedURLException {
        super(new URL[]{jar.toURI().toURL()}, parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
