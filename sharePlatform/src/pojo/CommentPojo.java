package pojo;

import java.util.Date;

import po.Comment;

public class CommentPojo {
	private Integer id;

	private String user;

	private Byte clazz;

	private Integer otherid;

	private Date comTime;

	private String comment;

	public CommentPojo() {
	}
	
	public CommentPojo(Comment comment){
		this.id = comment.getId();
		this.clazz = comment.getClazz();
		this.otherid = comment.getOtherid();
		this.comTime = comment.getComTime();
		this.comment = comment.getComment();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Byte getClazz() {
		return clazz;
	}

	public void setClazz(Byte clazz) {
		this.clazz = clazz;
	}

	public Integer getOtherid() {
		return otherid;
	}

	public void setOtherid(Integer otherid) {
		this.otherid = otherid;
	}

	public Date getComTime() {
		return comTime;
	}

	public void setComTime(Date comTime) {
		this.comTime = comTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
