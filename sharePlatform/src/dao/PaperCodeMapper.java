package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import po.PaperCode;

@Repository("paperCodeMapper")
@Mapper
public interface PaperCodeMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByPaperId(Integer id);
    
    int deleteByCodeId(Integer id);

    int insert(PaperCode record);

    int insertSelective(PaperCode record);
    
    List<Integer> selectCodeIdByPaperId(Integer id);
    
    List<Integer> selectPaperIdByCodeId(Integer id);

    PaperCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PaperCode record);

    int updateByPrimaryKey(PaperCode record);
}