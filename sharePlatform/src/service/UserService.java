package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import po.Code;
import po.DataSet;
import po.Paper;
import po.User;
import pojo.Source;
import pojo.UserPojo;

public interface UserService {
	
	/** 检查上传的论文是否重复 */
	public boolean isPaperDuplicate(String filename);
	
	/** 检查上传的代码是否重复 */
	public boolean isCodeDuplicate(String codename);
	
	/** 检查上传的数据集是否重复 */
	public boolean isDataSetDuplicate(String dataname);
	
	/** 将上传的文件信息填写到数据库 */
	public void addPaper(HttpServletRequest request, HttpSession session, String path);

	/** 将上传的代码信息填写到数据库 */
	public void addCode(HttpServletRequest request, HttpSession session, String path);
	
	/** 将上传的数据集信息填写到数据库 */
	public void addDataSet(HttpServletRequest request, HttpSession session, String path);

	/** 获取用户u上传的所有论文 */
	public List<Paper> getPaperByUser(User u);
	
	/** 搜索所有符合条件的资源 */
	public List<Source> getSource(String type, String field, String keyword);
	
	/** 根据id查询论文 */
	public Paper getPaperById(int id);
	
	/** 根据id查询代码 */
	public Code getCodeById(int id);
	
	/** 根据id查询数据集 */
	public DataSet getDataSetById(int id);
	
	/** 查询与论文相关的代码 */
	public List<Code> getCodeByPaperId(int id);
	
	/** 查询与论文相关的数据集 */
	public List<DataSet> getDataSetByPaperId(int id);
	
	/** 查询与代码相关的论文 */
	public List<Paper> getPaperByCodeId(int id);
	
	/** 查询与论文相关的数据集 */
	public List<Paper> getPaperByDataSetID(int id);
	
	/** 增加文章被下载次数 */
	public void addDownloadTimes(int clazz, int id);
	
	/** 查询所有用户的统计信息 */
	public List<UserPojo> getAllUsersStat();
	
	/** 获取用户上传的论文 */
	public List<Paper> selectPaperByUser(User u);
	
	/** 获取用户上传的所有代码 */
	public List<Code> selectCodeByUser(User u);
	
	/** 获取用户上传的所有数据集 */
	public List<DataSet> selectDataSetByUser(User u);
	
	/** 删除论文 */
	public void deletePaper(int id);
	
	/** 删除代码 */
	public void deleteCode(int id);
	
	/** 删除数据集 */
	public void deleteDateSet(int id);
}
