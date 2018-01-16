package config;

import java.io.File;

public class TestConfig {

    public static void main(String[] args) {
        try {
            Configuration config = DefaultConfiguration.getInstance();
            String value = config.get("man");
            System.out.println(value);

            DefaultConfiguration.getInstance().put("man","newValue");
            ConfigUtils.save(config,new File("config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
