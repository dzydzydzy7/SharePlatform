package service;

import java.util.List;

import pojo.CommentPojo;;

public interface CommentService {
	/** 查询一篇论文对应的所有评论 */
	public List<CommentPojo> getCommentsByPaperId(int id);
	
	/** 查询一篇代码对应的所有评论 */
	public List<CommentPojo> getCommentsByCodeId(int id);
	
	/** 查询一篇数据集对应的所有评论 */
	public List<CommentPojo> getCommentsByDataSetId(int id);
	
	/** 查询为论文、代码或数据集添加评论 */
	public void addComment(byte clazz, int userid, int otherid, String comment);
}
