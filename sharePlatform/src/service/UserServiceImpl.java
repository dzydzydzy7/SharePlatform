package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CodeMapper;
import dao.DataSetMapper;
import dao.PaperCodeMapper;
import dao.PaperDataMapper;
import dao.PaperMapper;
import dao.UserMapper;
import po.Code;
import po.DataSet;
import po.Paper;
import po.PaperCode;
import po.PaperData;
import po.User;
import pojo.Source;
import pojo.UserPojo;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PaperMapper paperMapper;

	@Autowired
	private CodeMapper codeMapper;

	@Autowired
	private DataSetMapper dataSetMapper;

	@Autowired
	private PaperCodeMapper paperCodeMapper;

	@Autowired
	private PaperDataMapper paperDataMapper;

	@Override
	public boolean isPaperDuplicate(String filename) {
		List<Paper> list = paperMapper.selectAllPaper();
		Iterator<Paper> iter = list.iterator();
		while (iter.hasNext()) {
			Paper next = iter.next();
			if (next.getTitle() != null && next.getTitle().equals(filename)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isCodeDuplicate(String codename) {
		List<Code> list = codeMapper.selectAllCode();
		Iterator<Code> iter = list.iterator();
		if (iter.hasNext()) {
			Code next = iter.next();
			if (next.getName() != null && next.getName().equals(codename)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isDataSetDuplicate(String dataname) {
		List<DataSet> list = dataSetMapper.selectAllDataSet();
		Iterator<DataSet> iter = list.iterator();
		if (iter.hasNext()) {
			DataSet next = iter.next();
			if (next.getName() != null && next.getName().equals(dataname)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void addPaper(HttpServletRequest request, HttpSession session, String path) {
		Paper paper = new Paper();
		paper.setTitle(request.getParameter("title"));
		paper.setAuthor(request.getParameter("author"));
		paper.setJournal(request.getParameter("journal"));
		paper.setField(request.getParameter("field"));
		paper.setAbstarct(request.getParameter("abstract"));
		// 这里如果不登录会空指针错误
		paper.setUserid(((User) session.getAttribute("currentUser")).getId());
		paper.setDowntimes(0);
		paper.setUploadtime(new Date());
		paper.setUrl(path);
		paperMapper.insert(paper);
	}

	@Override
	public void addCode(HttpServletRequest request, HttpSession session, String path) {
		Code code = new Code();
		String name = request.getParameter("name");
		code.setName(name);
		code.setUserid(((User) session.getAttribute("currentUser")).getId());
		code.setAuthor(request.getParameter("author"));
		code.setField(request.getParameter("field"));
		code.setIntro(request.getParameter("intro"));
		code.setUploadtime(new Date());
		code.setDowntimes(0);
		code.setUrl(path);
		codeMapper.insert(code);

		int paperid = Integer.parseInt(request.getParameter("paper"));
		if (paperid >= 0) {
			int codeid = codeMapper.selectByName(name).getId();
			PaperCode paperCode = new PaperCode();
			paperCode.setPaperid(paperid);
			paperCode.setCodeid(codeid);
			paperCodeMapper.insert(paperCode);
		}
	}

	@Override
	public void addDataSet(HttpServletRequest request, HttpSession session, String path) {
		DataSet dataSet = new DataSet();
		String name = request.getParameter("name");
		dataSet.setName(name);
		dataSet.setUserid(((User) session.getAttribute("currentUser")).getId());
		dataSet.setAuthor(request.getParameter("author"));
		dataSet.setField(request.getParameter("field"));
		dataSet.setIntro(request.getParameter("intro"));
		dataSet.setUploadtime(new Date());
		dataSet.setDowntimes(0);
		dataSet.setUrl(path);
		dataSetMapper.insert(dataSet);

		int paperid = Integer.parseInt(request.getParameter("paper"));
		if (paperid >= 0) {
			int dataid = dataSetMapper.selectByName(name).getId();
			PaperData paperData = new PaperData();
			paperData.setPaperid(paperid);
			paperData.setDataid(dataid);
			paperDataMapper.insert(paperData);
		}
	}

	@Override
	public List<Paper> getPaperByUser(User u) {
		if (u == null)
			return null;
		List<Paper> list = new ArrayList<Paper>();
		List<Paper> papers = paperMapper.selectByUser(u.getId());
		Paper paper = new Paper();
		paper.setId(-1);
		paper.setTitle("-不关联论文-");
		list.add(paper);
		list.addAll(papers);
		return list;
	}

	@Override
	public List<Source> getSource(String type, String field, String keyword) {
		List<Source> sources = new ArrayList<Source>();

		if (type.equals("请选择")) {
			if (field.equals("请选择")) {
				List<Paper> papers = paperMapper.selectAllPaper();
				sources.addAll(papersToSources(papers));
				List<Code> codes = codeMapper.selectAllCode();
				sources.addAll(codesToSources(codes));
				List<DataSet> dataSets = dataSetMapper.selectAllDataSet();
				sources.addAll(dataSetsToSources(dataSets));
			} else {
				List<Paper> papers = paperMapper.selectByField(field);
				sources.addAll(papersToSources(papers));
				List<Code> codes = codeMapper.selectByField(field);
				sources.addAll(codesToSources(codes));
				List<DataSet> dataSets = dataSetMapper.selectByField(field);
				sources.addAll(dataSetsToSources(dataSets));
			}
		} else if (type.equals("论文")) {
			if (field.equals("请选择")) {
				List<Paper> papers = paperMapper.selectAllPaper();
				sources.addAll(papersToSources(papers));
			} else {
				List<Paper> papers = paperMapper.selectByField(field);
				sources.addAll(papersToSources(papers));
			}
		} else if (type.equals("代码")) {
			if (field.equals("请选择")) {
				List<Code> codes = codeMapper.selectAllCode();
				sources.addAll(codesToSources(codes));
			} else {
				List<Code> codes = codeMapper.selectByField(field);
				sources.addAll(codesToSources(codes));
			}
		} else if (type.equals("数据集")) {
			if (field.equals("请选择")) {
				List<DataSet> dataSets = dataSetMapper.selectAllDataSet();
				sources.addAll(dataSetsToSources(dataSets));
			} else {
				List<DataSet> dataSets = dataSetMapper.selectByField(field);
				sources.addAll(dataSetsToSources(dataSets));
			}
		}

		Iterator<Source> iter = sources.iterator();
		while (iter.hasNext()) {
			Source next = iter.next();
			if (next.getName() == null || !next.getName().contains(keyword)) {
				iter.remove();
			}
		}
		return sources;
	}

	/** 将List<Paper>类型转换为显示在搜索结果中的List<source>类型 */
	private List<Source> papersToSources(List<Paper> papers) {
		List<Source> sources = new ArrayList<Source>();
		Iterator<Paper> iter = papers.iterator();
		while (iter.hasNext()) {
			Paper next = iter.next();
			sources.add(paperToSource(next));
		}
		return sources;
	}

	/** 将List<Code>类型转换为显示在搜索结果中的List<source>类型 */
	private List<Source> codesToSources(List<Code> codes) {
		List<Source> sources = new ArrayList<Source>();
		Iterator<Code> iter = codes.iterator();
		while (iter.hasNext()) {
			Code next = iter.next();
			sources.add(codeToSource(next));
		}
		return sources;
	}

	/** 将List<Paper>类型转换为显示在搜索结果中的List<source>类型 */
	private List<Source> dataSetsToSources(List<DataSet> dataSets) {
		List<Source> sources = new ArrayList<Source>();
		Iterator<DataSet> iter = dataSets.iterator();
		while (iter.hasNext()) {
			DataSet next = iter.next();
			sources.add(dataSetToSource(next));
		}
		return sources;
	}

	/** 将paper类型转换为显示在搜索结果中的source类型 */
	private Source paperToSource(Paper paper) {
		Source source = new Source();
		source.setClazz("论文");
		source.setLabel("paper");
		source.setId(paper.getId());
		source.setName(paper.getTitle());
		source.setField(paper.getField());
		source.setAuthor(paper.getAuthor());
		source.setIntro(paper.getAbstarct());
		source.setDownTimes(paper.getDowntimes());
		source.setUploadTime(paper.getUploadtime());
		return source;
	}

	/** 将code类型转换为显示在搜索结果中的source类型 */
	private Source codeToSource(Code code) {
		Source source = new Source();
		source.setClazz("代码");
		source.setLabel("code");
		source.setId(code.getId());
		source.setName(code.getName());
		source.setField(code.getField());
		source.setIntro(code.getIntro());
		source.setAuthor(code.getAuthor());
		source.setDownTimes(code.getDowntimes());
		source.setUploadTime(code.getUploadtime());
		return source;
	}

	/** 将DataSet类型转换为显示在搜索结果中的source类型 */
	private Source dataSetToSource(DataSet dataSet) {
		Source source = new Source();
		source.setClazz("数据集");
		source.setLabel("dataset");
		source.setId(dataSet.getId());
		source.setName(dataSet.getName());
		source.setField(dataSet.getField());
		source.setIntro(dataSet.getIntro());
		source.setAuthor(dataSet.getAuthor());
		source.setDownTimes(dataSet.getDowntimes());
		source.setUploadTime(dataSet.getUploadtime());
		return source;
	}

	@Override
	public Paper getPaperById(int id) {
		return paperMapper.selectByPrimaryKey(id);
	}

	@Override
	public Code getCodeById(int id) {
		return codeMapper.selectByPrimaryKey(id);
	}

	@Override
	public DataSet getDataSetById(int id) {
		return dataSetMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Code> getCodeByPaperId(int id) {
		List<Integer> codeid = paperCodeMapper.selectCodeIdByPaperId(id);
		List<Code> codes = new ArrayList<Code>();
		Iterator<Integer> iter = codeid.iterator();
		while (iter.hasNext()) {
			int next = iter.next();
			codes.add(codeMapper.selectByPrimaryKey(next));
		}
		return codes;
	}

	@Override
	public List<DataSet> getDataSetByPaperId(int id) {
		List<Integer> dataSetId = paperDataMapper.selectDataSetIdByPaperId(id);
		List<DataSet> dataSets = new ArrayList<DataSet>();
		Iterator<Integer> iter = dataSetId.iterator();
		while (iter.hasNext()) {
			int next = iter.next();
			dataSets.add(dataSetMapper.selectByPrimaryKey(next));
		}
		return dataSets;
	}

	@Override
	public List<Paper> getPaperByCodeId(int id) {
		List<Integer> paperid = paperCodeMapper.selectPaperIdByCodeId(id);
		List<Paper> papers = new ArrayList<Paper>(); 
		Iterator<Integer> iter = paperid.iterator();
		while(iter.hasNext()) {
			int next = iter.next();
			papers.add(paperMapper.selectByPrimaryKey(next));
		}
		return papers;
	}

	@Override
	public List<Paper> getPaperByDataSetID(int id) {
		List<Integer> paperid = paperDataMapper.selectPaperIdByDataSetId(id);
		List<Paper> papers = new ArrayList<Paper>(); 
		Iterator<Integer> iter = paperid.iterator();
		while(iter.hasNext()) {
			int next = iter.next();
			papers.add(paperMapper.selectByPrimaryKey(next));
		}
		return papers;
	}
	
	@Override
	public void addDownloadTimes(int clazz, int id) {
		if (clazz == 1) {
			Paper paper = paperMapper.selectByPrimaryKey(id);
			paper.setDowntimes(paper.getDowntimes() + 1);
			paperMapper.updateByPrimaryKeyWithBLOBs(paper);
		} else if (clazz == 2) {
			Code code = codeMapper.selectByPrimaryKey(id);
			code.setDowntimes(code.getDowntimes() + 1);
			codeMapper.updateByPrimaryKeyWithBLOBs(code);
		} else if (clazz == 3) {
			DataSet dataSet = dataSetMapper.selectByPrimaryKey(id);
			dataSet.setDowntimes(dataSet.getDowntimes() + 1);
			dataSetMapper.updateByPrimaryKeyWithBLOBs(dataSet);
		}

	}

	@Override
	public List<UserPojo> getAllUsersStat() {
		List<UserPojo> userPojos = new ArrayList<UserPojo>();
		List<User> users = userMapper.getAllUsers();
		Iterator<User> iter = users.iterator();
		while(iter.hasNext()) {
			User next = iter.next();
			UserPojo pojo = new UserPojo(next);
			pojo.setTimes(getDownloadTimes(next.getId()));
			userPojos.add(pojo);
		}
		return userPojos;
	}
	
	/** 根据用户id计算被下载次数 */
	private int getDownloadTimes(int userid) {
		int count = 0;
		List<Paper> papers = paperMapper.selectByUser(userid);
		List<Integer> codes = codeMapper.selectByUserid(userid);
		List<Integer> datasets = dataSetMapper.selectByUserId(userid);
		Iterator<Paper> iter = papers.iterator();
		while(iter.hasNext()) {
			Paper next = iter.next();
			count += next.getDowntimes();
		}
		Iterator<Integer> iterInt = codes.iterator();
		while (iterInt.hasNext()) {
			count += iterInt.next();
		}
		iterInt = datasets.iterator();
		while (iterInt.hasNext()) {
			count += iterInt.next();
		}
		return count;
	}

	@Override
	public void deletePaper(int id) {
		paperMapper.deleteByPrimaryKey(id);
		paperCodeMapper.deleteByPaperId(id);
		paperDataMapper.deleteByPaperId(id);
	}

	@Override
	public void deleteCode(int id) {
		codeMapper.deleteByPrimaryKey(id);
		paperCodeMapper.deleteByCodeId(id);
	}

	@Override
	public void deleteDateSet(int id) {
		dataSetMapper.deleteByPrimaryKey(id);
		paperDataMapper.deleteByDataSetid(id);
	}

	@Override
	public List<Paper> selectPaperByUser(User u) {
		return paperMapper.selectByUser(u.getId());
	}

	@Override
	public List<Code> selectCodeByUser(User u) {
		return codeMapper.selectCodeByUserid(u.getId());
	}

	@Override
	public List<DataSet> selectDataSetByUser(User u) {
		return dataSetMapper.selectDataSetByUserId(u.getId());
	}

}
