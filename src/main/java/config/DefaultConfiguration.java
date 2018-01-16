package config;

import java.io.File;
import java.io.IOException;

/**
 * Created by linsixin on 2017/10/11.
 * 默认加载jar包所在的目录的config.properties文件
 * 找不到的话,就加载jar内部的config.properties文件
 * 内部的config.properties文件可以作为默认的配置
 */
public class DefaultConfiguration {

    private static String lock = "ConfigLock";

    private static volatile Configuration configuration = null;

    private static String defaultConfigFile = "config.properties";

    public static Configuration getInstance() throws Exception {
        if (configuration == null) {
            synchronized (lock){
                if(configuration == null){
                    File file = new File(defaultConfigFile);
                    if (!file.exists())
                        configuration = ConfigUtils.load(
                                DefaultConfiguration.class.getClassLoader()
                                        .getResourceAsStream("config.properties")
                        );
                    else configuration = ConfigUtils.load(file);
                    if (configuration == null) {
                        throw new Exception("no default config");
                    }
                }else return configuration;
            }
        }
        return configuration;
    }

    public static void save() throws IOException {
        if(configuration == null)
            return;
        ConfigUtils.save(configuration,new File(defaultConfigFile));
    }
}