package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import po.DataSet;

@Repository("dataSetMapper")
@Mapper
public interface DataSetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataSet record);

    int insertSelective(DataSet record);
    
    List<DataSet> selectAllDataSet();
    
    List<DataSet> selectByField(String field);
    
    List<Integer> selectByUserId(int userid);
    
    List<DataSet> selectDataSetByUserId(int userid);
    
    DataSet selectByName(String name);

    DataSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataSet record);

    int updateByPrimaryKeyWithBLOBs(DataSet record);

    int updateByPrimaryKey(DataSet record);
}