package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserMapper;
import po.User;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUser(User u) {
		User user = userMapper.selectByName(u.getName());
		if (user == null)
			return null;
		if (!user.getPwd().equals(u.getPwd())) {
			return null;
		}
		return user;
	}

	@Override
	public boolean checkDuplicate(User u) {
		List<String> names = userMapper.getAllUserName();
		if (names.contains(u.getName()))
			return false;
		return true;
	}

	@Override
	public void addUser(User u) {
		u.setClazz(1);
		userMapper.insert(u);
	}

}
