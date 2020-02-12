package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import po.Code;

@Repository("codeMapper")
@Mapper
public interface CodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Code record);

    int insertSelective(Code record);
    
    List<Code> selectAllCode();
    
    List<Code> selectByField(String field);
    
    List<Integer> selectByUserid(int userid);
    
    List<Code> selectCodeByUserid(int userid);

    Code selectByName(String name);

    Code selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Code record);

    int updateByPrimaryKeyWithBLOBs(Code record);

    int updateByPrimaryKey(Code record);
}