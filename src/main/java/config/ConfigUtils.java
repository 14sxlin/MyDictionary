package config;

import java.io.*;
import java.util.Properties;

public class ConfigUtils {

    public static Configuration load(File file) throws IOException {
        if (file == null || !file.exists() || file.isDirectory())
            throw new IllegalArgumentException("file not legal");
        try(InputStream in = new FileInputStream(file)) {
            return load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Configuration load(InputStream in) {
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

    public static void save(Configuration configuration, OutputStream out) {
        Properties properties = configuration.getProps();
        try {
            properties.store(out, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(Configuration configuration, File file) throws IOException {
        try(OutputStream out = new FileOutputStream(file)) {
            save(configuration,out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
