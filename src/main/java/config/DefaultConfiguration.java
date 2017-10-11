package config;

import java.io.File;

/**
 * Created by linsixin on 2017/10/11.
 * 默认加载jar包所在的目录的config.properties文件
 * 找不到的话,就加载jar内部的config.properties文件
 * 内部的config.properties文件可以作为默认的配置
 */
public class DefaultConfiguration {

    private static Configuration configuration = null;

    private static String defaultConfigFile = "config.properties";

    public static synchronized Configuration getInstance() throws Exception {
        if (configuration == null) {
            File file = new File(defaultConfigFile);
            if (!file.exists())
                configuration = ConfigUtils.loadAndClose(
                        DefaultConfiguration.class.getClassLoader()
                                .getResourceAsStream("config.properties")
                );
            else configuration = ConfigUtils.load(file);
            if (configuration == null) {
                throw new Exception("no default config");
            }
        }
        return configuration;
    }

    public static void save() {
        if(configuration == null)
            return;
        ConfigUtils.save(configuration,new File(defaultConfigFile));
    }
}