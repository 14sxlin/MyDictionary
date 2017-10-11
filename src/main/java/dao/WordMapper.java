package dao;

import java.util.List;
import dao.Word;
import dao.WordExample;
import org.apache.ibatis.annotations.Param;

public interface WordMapper {
    int countByExample(WordExample example);

    int deleteByExample(WordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Word record);

    int insertSelective(Word record);

    List<Word> selectByExample(WordExample example);

    Word selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Word record, @Param("example") WordExample example);

    int updateByExample(@Param("record") Word record, @Param("example") WordExample example);

    int updateByPrimaryKeySelective(Word record);

    int updateByPrimaryKey(Word record);
}