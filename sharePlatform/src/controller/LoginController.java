package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import po.User;
import service.LoginService;

@Controller
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private LoginService loginService;

	/** 登录界面 */
	@RequestMapping(value = "/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	/** 点击登录按钮后的处理登录 */
	@RequestMapping(value = "/doLogin")
	public String doLogin(@ModelAttribute User user, HttpServletRequest requset, HttpSession session, Model model) {
		// 检查验证码是否正确
		Pattern pattern = Pattern.compile(requset.getParameter("code"), Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(requset.getSession().getAttribute("code").toString());
		if (!matcher.matches()) {
			return "loginAgainVerify";
		}
		// 检查用户名是否存在，密码是否错误
		User u = loginService.getUser(user);
		if (u == null) {
			return "loginAgainPwd";
		}
		// 登录表单填写无误，转到相应页面
		session.setAttribute("currentUser", u);
		model.addAttribute("userStat", getUserName(session));
		if (u.getClazz() == 0) {
			return "redirect:/admin/field";
		} else if (u.getClazz() == 1) {
			return "redirect:/source/search";
		} else {
			return "loginAgainPwd";
		}
	}

	/** 获取注册信息 */
	@RequestMapping(value = "/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	/** 处理注册 */
	@RequestMapping(value = "/doRegist")
	public String doRegist(@ModelAttribute User user, Model model) {
		if (loginService.checkDuplicate(user)) {
			loginService.addUser(user);
			return "registOk";
		}
		return "registAgain";
	}

	/** 获取session中的用户名，如果不存在，返回null */
	private String getUserName(HttpSession session) {
		User u = (User) session.getAttribute("currentUser");
		if (u == null) {
			return "未登录";
		}
		return u.getName() + " 已登录";
	}
}
