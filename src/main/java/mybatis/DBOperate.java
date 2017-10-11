package mybatis;

import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * Created by linsixin on 2017/10/10.
 */
public class DBOperate {

    public int insert(String sql,Map valueMap){
        SqlSession session = GetSqlSession.getSqlSession();
        return session.insert(sql,valueMap);
    }

    public int delete(String sql,Map valueMap){
        SqlSession session = GetSqlSession.getSqlSession();
        return session.delete(sql,valueMap);
    }

    public int update(String sql,Map valueMap){
        SqlSession session = GetSqlSession.getSqlSession();
        return session.update(sql,valueMap);
    }

    public List<Map> select(String sql, Map valueMap){
        SqlSession session = GetSqlSession.getSqlSession();
        return session.selectList(sql,valueMap);
    }

}
