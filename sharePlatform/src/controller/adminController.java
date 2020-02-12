package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import po.Field;
import po.User;
import service.FieldService;
import service.UserService;

@Controller
@RequestMapping("/admin")
public class adminController {
	@Autowired
	private FieldService fieldService;
	
	@Autowired
	private UserService userService;
	
	/** 管理领域页面 */
	@RequestMapping(value = "/field")
	public String field(HttpSession session, Model model) {
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("fields", fieldService.getAllFields());
		model.addAttribute("addField", new Field());
		return "adminField";
	}
	
	/** 添加领域 */
	@RequestMapping(value = "/doAddField")
	public String doAddField(@ModelAttribute Field addField, HttpSession session, Model model) {
		fieldService.addField(addField);
		return "redirect:/admin/field";	
	}
	
	/** 显示论文统计 */
	@RequestMapping(value = "/paper")
	public String paper(HttpSession session, Model model) {
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("statistics", userService.getSource("论文", "请选择", ""));
		return "statPapers";
	}
	
	/** 显示代码统计 */
	@RequestMapping(value = "/code")
	public String code(HttpSession session, Model model) {
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("statistics", userService.getSource("代码", "请选择", ""));
		return "statCodes";
	}
	
	/** 显示数据集统计 */
	@RequestMapping(value = "/dataset")
	public String dataset(HttpSession session, Model model) {
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("statistics", userService.getSource("数据集", "请选择", ""));
		return "statDatasets";
	}
	
	/** 显示用户统计 */
	@RequestMapping(value = "/users")
	public String users(HttpSession session, Model model) {
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("users", userService.getAllUsersStat());
		return "statUsers";
	}
	
	/** 安全退出 */
	@RequestMapping(value = "/quit")
	public String quit(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/user/login";
	}
	
	/** 获取session中的用户名，如果不存在，返回null */
	private String getUserStat(HttpSession session) {
		User u = (User) session.getAttribute("currentUser");
		if (u == null) {
			return "未登录";
		}
		return u.getName() + " 已登录";
	}
}
