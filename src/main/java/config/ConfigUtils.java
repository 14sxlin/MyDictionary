package config;

import java.io.*;
import java.util.Properties;

/**
 * Created by linsixin on 2017/10/11.
 */
public class ConfigUtils {

    public static Configuration load(File file) {
        if (file == null || !file.exists() || file.isDirectory())
            throw new IllegalArgumentException("file not legal");
        try {
            return loadAndClose(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Configuration loadAndClose(InputStream in) {
        Properties properties = new Properties();
        try {
            properties.load(in);

            Configuration configuration = new Configuration(properties);
            for (String name : properties.stringPropertyNames()) {
                System.out.println(name + " " + properties.getProperty(name));
                configuration.put(name, properties.getProperty(name));
            }

            in.close();

            return configuration;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveAndClose(Configuration configuration, OutputStream out) {
        Properties properties = configuration.getProps();
        try {
            properties.store(out, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(Configuration configuration, File file) {
        try {
            saveAndClose(configuration, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
