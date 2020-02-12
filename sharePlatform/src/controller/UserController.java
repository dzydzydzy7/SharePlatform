package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import po.User;
import service.CommentService;
import service.FieldService;
import service.UserService;

@Controller
@RequestMapping("/source")
public class UserController {

	@Autowired
	private FieldService fieldService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;

	/** 显示搜索页面 */
	@RequestMapping(value = "/search")
	public String search(HttpSession session, Model model) {
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("fields", fieldService.getAllFields());
		model.addAttribute("sources", userService.getSource("请选择", "请选择", ""));
		return "userSearch";
	}

	/** 搜索内容 */
	@RequestMapping(value = "/doSearch")
	public String doSearch(HttpServletRequest request, HttpSession session, Model model) {
		String type = request.getParameter("source");
		String field = request.getParameter("field");
		String keyword = request.getParameter("name");
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("fields", fieldService.getAllFields());
		model.addAttribute("sources", userService.getSource(type, field, keyword));
		return "userSearchRes";
	}

	/** 论文详情页 */
	@RequestMapping(value = "/paper")
	public String paper(@RequestParam("id") int id, HttpSession session, Model model) {
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("paper", userService.getPaperById(id));
		model.addAttribute("codes", userService.getCodeByPaperId(id));
		model.addAttribute("datasets", userService.getDataSetByPaperId(id));
		model.addAttribute("comments", commentService.getCommentsByPaperId(id));
		return "paperDetail";
	}

	/** 下载论文 */
	@RequestMapping(value = "/downPaper")
	public String downPaper(@RequestParam("id") int id, HttpServletResponse response) {
		String url = userService.getPaperById(id).getUrl();
		downloadFile(url, response);
		userService.addDownloadTimes(1, id);
		return null;
	}

	/** 对论文评论 */
	@RequestMapping(value = "/commentPaper")
	public String commentPaper(@RequestParam("id") int id, HttpServletRequest request, HttpSession session) {
		String comment = request.getParameter("comment");
		User u = (User) session.getAttribute("currentUser");
		commentService.addComment((byte) 1, u.getId(), id, comment);
		return "redirect:/source/paper?id=" + String.valueOf(id);
	}

	/** 代码详情页 */
	@RequestMapping(value = "/code")
	public String code(@RequestParam("id") int id, HttpSession session, Model model) {
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("code", userService.getCodeById(id));
		model.addAttribute("papers", userService.getPaperByCodeId(id));
		model.addAttribute("comments", commentService.getCommentsByCodeId(id));
		return "codeDetail";
	}

	/** 代码下载 */
	@RequestMapping(value = "/downCode")
	public String downCode(@RequestParam("id") int id, HttpServletResponse response) {
		String url = userService.getCodeById(id).getUrl();
		downloadFile(url, response);
		userService.addDownloadTimes(2, id);
		return null;
	}

	/** 代码评论 */
	@RequestMapping(value = "/commentCode")
	public String commentCode(@RequestParam("id") int id, HttpServletRequest request, HttpSession session) {
		String comment = request.getParameter("comment");
		User u = (User) session.getAttribute("currentUser");
		commentService.addComment((byte) 2, u.getId(), id, comment);
		return "redirect:/source/code?id=" + String.valueOf(id);
	}

	/** 数据集详情页 */
	@RequestMapping(value = "/dataset")
	public String dataset(@RequestParam("id") int id, HttpSession session, Model model) {
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("dataset", userService.getDataSetById(id));
		model.addAttribute("papers", userService.getPaperByDataSetID(id));
		model.addAttribute("comments", commentService.getCommentsByDataSetId(id));
		return "datasetDetail";
	}

	/** 数据集下载 */
	@RequestMapping(value = "/downDataset")
	public String downDataset(@RequestParam("id") int id, HttpServletResponse response) {
		String url = userService.getDataSetById(id).getUrl();
		downloadFile(url, response);
		userService.addDownloadTimes(3, id);
		return null;
	}

	/** 数据集评论 */
	@RequestMapping(value = "/commentDataset")
	public String commentDataset(@RequestParam("id") int id, HttpServletRequest request, HttpSession session) {
		String comment = request.getParameter("comment");
		User u = (User) session.getAttribute("currentUser");
		commentService.addComment((byte) 3, u.getId(), id, comment);
		return "redirect:/source/dataset?id=" + String.valueOf(id);
	}

	/** 显示上传论文页面 */
	@RequestMapping(value = "/uploadPaper")
	public String uploadPaper(HttpSession session, Model model) {
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("fields", fieldService.getAllFields());
		return "userUploadPaper";
	}

	/** 上传论文 */
	@RequestMapping(value = "/doUploadPaper")
	public String doUploadPaper(HttpServletRequest request, HttpSession session) {
		String filename = request.getParameter("title");
		if (userService.isPaperDuplicate(filename))
			return "userUploadFail";

		String path = uploadFile(request, "D:/myupload/");

		userService.addPaper(request, session, path);
		return "userUploadOk";
	}

	/** 显示上传代码界面 */
	@RequestMapping(value = "/uploadCode")
	public String uploadCode(HttpSession session, Model model) {
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("fields", fieldService.getAllFields());
		model.addAttribute("papers", userService.getPaperByUser((User) session.getAttribute("currentUser")));
		return "userUploadCode";
	}

	/** 上传代码 */
	@RequestMapping(value = "/doUploadCode")
	public String doUploadCode(HttpServletRequest request, HttpSession session) {
		String codename = request.getParameter("name");
		if (userService.isCodeDuplicate(codename))
			return "userUploadFail";

		String path = uploadFile(request, "D:/codeRepo/");

		userService.addCode(request, session, path);
		return "userUploadOk";
	}

	/** 显示上传数据集页面 */
	@RequestMapping(value = "/uploadData")
	public String uploadData(HttpSession session, Model model) {
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("fields", fieldService.getAllFields());
		model.addAttribute("papers", userService.getPaperByUser((User) session.getAttribute("currentUser")));
		return "userUploadData";
	}

	/** 上传数据集 */
	@RequestMapping(value = "/doUploadData")
	public String doUploadData(HttpServletRequest request, HttpSession session) {
		String dataname = request.getParameter("name");
		if (userService.isDataSetDuplicate(dataname))
			return "userUploadFail";

		String path = uploadFile(request, "D:/dataRepo/");

		userService.addDataSet(request, session, path);
		return "userUploadOk";
	}

	/** 显示用户个人页面 */
	@RequestMapping(value = "/home")
	public String home(HttpSession session, Model model) {
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("clz", "paper");
		model.addAttribute("upds", userService.selectPaperByUser((User) session.getAttribute("currentUser")));
		return "userHome";
	}

	/** 选择类型后的用户个人界面 */
	@RequestMapping(value = "/doSelect")
	public String doSelect(HttpServletRequest request, HttpSession session, Model model) {
		model.addAttribute("userStat", getUserStat(session));
		String clazz = request.getParameter("source");
		if (clazz.equals("论文")) {
			model.addAttribute("clz", "paper");
			model.addAttribute("upds", userService.selectPaperByUser((User) session.getAttribute("currentUser")));
		} else if (clazz.equals("代码")) {
			model.addAttribute("clz", "code");
			model.addAttribute("upds", userService.selectCodeByUser((User) session.getAttribute("currentUser")));
		} else if (clazz.equals("数据集")) {
			model.addAttribute("clz", "dataset");
			model.addAttribute("upds", userService.selectDataSetByUser((User) session.getAttribute("currentUser")));
		}
		return "userHome";
	}
	
	/** 删除论文 */
	@RequestMapping(value = "/deletepaper")
	public String deletePaper(@RequestParam("id") int id, HttpSession session, Model model) { 
		userService.deletePaper(id);
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("clz", "paper");
		model.addAttribute("upds", userService.selectPaperByUser((User) session.getAttribute("currentUser")));
		return "userHome";
	}
	
	/** 删除代码 */
	@RequestMapping(value = "/deletecode")
	public String deleteCode(@RequestParam("id") int id, HttpSession session, Model model) { 
		userService.deleteCode(id);
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("clz", "code");
		model.addAttribute("upds", userService.selectCodeByUser((User) session.getAttribute("currentUser")));
		return "userHome";
	}
	
	/** 删除数据集 */
	@RequestMapping(value = "/deletedataset")
	public String deleteDataset(@RequestParam("id") int id, HttpSession session, Model model) { 
		userService.deleteDateSet(id);
		model.addAttribute("userStat", getUserStat(session));
		model.addAttribute("clz", "dataset");
		model.addAttribute("upds", userService.selectDataSetByUser((User) session.getAttribute("currentUser")));
		return "userHome";
	}

	/** 点击安全退出后，清除用户session，返回登录页面 */
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

	/** 上传文件到服务器文件夹 */
	private String uploadFile(HttpServletRequest request, String folder) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		File temp = new File(request.getServletContext() + "/temp");
		if (!temp.exists()) {
			temp.mkdir();
		}
		String path = null;
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multipartRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multipartRequest.getFile(iter.next().toString());
				if (file != null) {
					path = folder + file.getOriginalFilename();
					try {
						file.transferTo(new File(path));
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return path;
	}

	private void downloadFile(String url, HttpServletResponse response) {
		String[] s = url.split("/");
		String filename = s[s.length - 1];
		response.setHeader("Content-Type", "application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment; filename=" + toUTF8String(filename));
		FileInputStream fis;
		ServletOutputStream sos;
		try {
			fis = new FileInputStream(url);
			sos = response.getOutputStream();
			sos.flush();
			int read = 0;
			byte[] b = new byte[1024];
			while ((read = fis.read(b)) != -1 && fis != null) {
				sos.write(b, 0, read);
			}
			sos.flush();
			sos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 下载保存时中文文件名字符编码转换方法 */
	public String toUTF8String(String str) {
		StringBuffer sb = new StringBuffer();
		int len = str.length();
		for (int i = 0; i < len; i++) {
			// 取出字符中的每个字符
			char c = str.charAt(i);
			// Unicode码值在0-255之间，不作处理
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {// 转换UTF-8编码
				byte b[];
				try {
					b = Character.toString(c).getBytes("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					b = null;
				}
				// 转换为%HH的字符串形式
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0) {
						k &= 255;
					}
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
}
