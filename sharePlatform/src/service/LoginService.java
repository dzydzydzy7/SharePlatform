package service;

import po.User;

public interface LoginService {
	
	/** 获取用户信息，如果用户不存在，返回null */
	public User getUser(User u);
	
	/** 用户注册时，检查用户名是否重复 */
	public boolean checkDuplicate(User u);
	
	/** 用户注册时，向数据库添加该用户 */
	public void addUser(User u);
}
