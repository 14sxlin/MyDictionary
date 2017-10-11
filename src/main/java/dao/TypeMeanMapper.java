package dao;

import java.util.List;
import dao.TypeMean;
import dao.TypeMeanExample;
import org.apache.ibatis.annotations.Param;

public interface TypeMeanMapper {
    int countByExample(TypeMeanExample example);

    int deleteByExample(TypeMeanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TypeMean record);

    int insertSelective(TypeMean record);

    List<TypeMean> selectByExample(TypeMeanExample example);

    TypeMean selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TypeMean record, @Param("example") TypeMeanExample example);

    int updateByExample(@Param("record") TypeMean record, @Param("example") TypeMeanExample example);

    int updateByPrimaryKeySelective(TypeMean record);

    int updateByPrimaryKey(TypeMean record);
}