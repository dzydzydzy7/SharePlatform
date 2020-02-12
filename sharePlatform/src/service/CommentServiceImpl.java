package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CommentMapper;
import dao.UserMapper;
import po.Comment;
import pojo.CommentPojo;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<CommentPojo> getCommentsByPaperId(int id) {
		List<CommentPojo> commentPojo = new ArrayList<CommentPojo>();
		List<Comment> comments = commentMapper.selectByPaperId(id);
		Iterator<Comment> iter = comments.iterator();
		while(iter.hasNext()) {
			Comment next = iter.next();
			CommentPojo pojo = new CommentPojo(next);
			pojo.setUser(userMapper.selectByPrimaryKey(next.getUserid()).getName());
			commentPojo.add(pojo);
		}
		return commentPojo;
	}

	@Override
	public List<CommentPojo> getCommentsByCodeId(int id) {
		List<CommentPojo> commentPojo = new ArrayList<CommentPojo>();
		List<Comment> comments = commentMapper.selectByCodeId(id);
		Iterator<Comment> iter = comments.iterator();
		while(iter.hasNext()) {
			Comment next = iter.next();
			CommentPojo pojo = new CommentPojo(next);
			pojo.setUser(userMapper.selectByPrimaryKey(next.getUserid()).getName());
			commentPojo.add(pojo);
		}
		return commentPojo;
	}

	@Override
	public List<CommentPojo> getCommentsByDataSetId(int id) {
		List<CommentPojo> commentPojo = new ArrayList<CommentPojo>();
		List<Comment> comments = commentMapper.selectByDataSetId(id);
		Iterator<Comment> iter = comments.iterator();
		while(iter.hasNext()) {
			Comment next = iter.next();
			CommentPojo pojo = new CommentPojo(next);
			pojo.setUser(userMapper.selectByPrimaryKey(next.getUserid()).getName());
			commentPojo.add(pojo);
		}
		return commentPojo;
	}

	@Override
	public void addComment(byte clazz, int userid, int otherid, String comment) {
		Comment comm = new Comment();
		comm.setClazz(clazz);	
		comm.setUserid(userid);
		comm.setOtherid(otherid);
		comm.setComment(comment);
		comm.setComTime(new Date());
		commentMapper.insert(comm);
	}

}
