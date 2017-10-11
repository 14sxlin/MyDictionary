package config;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created by linsixin on 2017/10/11.
 */
public class Configuration extends HashMap<String,String>{

    private final Properties props;

    public Configuration(final Properties props){
        this.props = props;
    }

    @Override
    public String put(String key, String value) {
        props.setProperty(key,value);
        return super.put(key, value);
    }

    Properties getProps() {
        return props;
    }
}
