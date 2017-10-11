package mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by linsixin on 2017/10/10.
 */
public class GetSqlSession {

    private static ThreadLocal<SqlSession> local = new ThreadLocal<>();

    public static SqlSession getSqlSession() {
        SqlSession session = local.get();
        if(session == null){
            SqlSessionFactory factory = GetSqlSessionFactory.getSqlSessionFactory();
            session = factory.openSession();
            local.set(session);
        }
        return session;
    }
}
