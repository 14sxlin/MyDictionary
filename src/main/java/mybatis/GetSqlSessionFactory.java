package mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by linsixin on 2017/10/10.
 */
public class GetSqlSessionFactory {

    private static SqlSessionFactory factory;

    public static synchronized SqlSessionFactory getSqlSessionFactory(){
        if(factory == null){
            try {
                String configPath = "mybatis-config.xml";
                Reader reader = Resources.getResourceAsReader(configPath);
                factory = new SqlSessionFactoryBuilder().build(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return factory;
    }
}
