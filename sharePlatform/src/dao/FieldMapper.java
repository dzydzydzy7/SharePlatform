package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import po.Field;

@Repository("fieldMapper")
@Mapper
public interface FieldMapper {
    int deleteByPrimaryKey(String fieldcn);

    int insert(Field record);

    int insertSelective(Field record);
    
    List<Field> selectAllFields();

    Field selectByPrimaryKey(String fieldcn);

    int updateByPrimaryKeySelective(Field record);

    int updateByPrimaryKey(Field record);
}