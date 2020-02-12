package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import po.PaperData;

@Repository("paperDataMapper")
@Mapper
public interface PaperDataMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByPaperId(Integer id);
    
    int deleteByDataSetid(Integer id);

    int insert(PaperData record);

    int insertSelective(PaperData record);
    
    List<Integer> selectDataSetIdByPaperId(Integer id);
    
    List<Integer> selectPaperIdByDataSetId(Integer id);

    PaperData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PaperData record);

    int updateByPrimaryKey(PaperData record);
}