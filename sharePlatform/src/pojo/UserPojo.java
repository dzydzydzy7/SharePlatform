package pojo;

import po.User;

public class UserPojo {
	String name;
	String pwd;
	String clazz;
	int times;
	
	public UserPojo() {
		
	}
	
	public UserPojo(User user) {
		this.name = user.getName();
		this.pwd = user.getPwd();
		if(user.getClazz() == 0) {
			this.clazz = "管理员";
		} else if(user.getClazz() == 1) {
			this.clazz = "普通用户";
		} else {
			this.clazz = "被禁止登录";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}
}
