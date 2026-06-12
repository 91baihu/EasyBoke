package com.easyboke.controller;

import com.easyboke.common.Constants;
import com.easyboke.dto.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.easyboke.service.AdminService;
import com.easyboke.service.UserService;
import com.easyboke.mappers.VisitLogMapper;
import com.easyboke.entity.po.Admin;
import com.easyboke.entity.po.User;
import com.easyboke.entity.query.AdminQuery;
import com.easyboke.entity.vo.ResponseVO;
import com.easyboke.utils.MailUtil;
import com.easyboke.utils.PasswordResetTokenManager;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class AdminController extends ABaseController{

	@Resource
	private AdminService adminService;

	@Resource
	private UserService userService;

	@Resource
	private MailUtil mailUtil;

	@Resource
	private PasswordResetTokenManager tokenManager;

	@Resource
	private VisitLogMapper visitLogMapper;

	/**
	 * 显示登录页面
	 */
	@GetMapping("/login")
	public String loginPage() {
		return "redirect:/admin-login.html";
	}

	/**
	 * 管理员登录
	 */
	@PostMapping("/login")
	@ResponseBody
	public ResponseVO login(@RequestBody LoginDTO loginDTO, HttpSession session) {
		try {
			System.out.println("========== Controller接收到的数据 ==========");
			System.out.println("用户名: [" + loginDTO.getUsername() + "]");
			System.out.println("密码: [" + loginDTO.getPassword() + "]");
			System.out.println("==========================================");

			Admin admin = adminService.login(loginDTO.getUsername(), loginDTO.getPassword());
			// 单设备登录控制
			Constants.ADMIN_SESSION_MAP.put(admin.getId(), session.getId());
			session.setAttribute(Constants.SESSION_KEY_ADMIN, admin);

			ResponseVO responseVO = getSuccessResponseVo(admin);
			responseVO.setInfo("登录成功");
			return responseVO;
		} catch (Exception e) {
			System.out.println("========== 登录异常 ==========");
			System.out.println("异常信息: " + e.getMessage());
			System.out.println("================================");
			return getErrorResponseVo(e.getMessage());
		}
	}



	/**
	 * 管理员登出
	 */
	@PostMapping("/logout")
	@ResponseBody
	public ResponseVO logout(HttpSession session) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_KEY_ADMIN);
		if (admin != null) {
			Constants.ADMIN_SESSION_MAP.remove(admin.getId());
		}
		session.removeAttribute(Constants.SESSION_KEY_ADMIN);
		return getSuccessResponseVo("登出成功");
	}

	/**
	 * 获取当前登录管理员信息
	 */
	@GetMapping("/currentAdmin")
	@ResponseBody
	public ResponseVO getCurrentAdmin(HttpSession session) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_KEY_ADMIN);
		if (admin == null) {
			return getErrorResponseVo("未登录");
		}
		return getSuccessResponseVo(admin);
	}


	/**
	 * 文章管理页面
	 */
	@GetMapping("/articles")
	public String articleManagement() {
		return "redirect:/article-management.html";
	}
	/**
	 * 访问统计页面
	 */
	@GetMapping("/statistics")
	public String statistics() {
		return "redirect:/statistics.html";
	}
	/**
	 * 评论管理页面
	 */
	@GetMapping("/comments")
	public String commentManagement() {
		return "redirect:/comment-management.html";
	}

	/**
	 * 后台管理首页（Dashboard）
	 */
	@GetMapping("/dashboard")
	public String dashboard() {
		return "redirect:/admin-dashboard.html";
	}

	/**
	 * 更新管理员个人资料
	 */
	@PostMapping("/updateProfile")
	@ResponseBody
	public ResponseVO updateProfile(Integer id, String nickname, String email, String phone,
									 Integer gender, Integer age, String introduction, HttpSession session) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_KEY_ADMIN);
		if (admin == null) {
			return getErrorResponseVo("请先登录");
		}

		Admin updateAdmin = new Admin();
		updateAdmin.setNickname(nickname);
		updateAdmin.setEmail(email);
		updateAdmin.setPhone(phone);
		updateAdmin.setGender(gender);
		updateAdmin.setAge(age);
		updateAdmin.setIntroduction(introduction);
		adminService.updateById(updateAdmin, id);

		// 刷新session
		Admin refreshed = adminService.getById(id);
		refreshed.setPassword(null);
		session.setAttribute(Constants.SESSION_KEY_ADMIN, refreshed);

		return getSuccessResponseVo("更新成功");
	}

	/**
	 * 修改管理员密码
	 */
	@PostMapping("/changePassword")
	@ResponseBody
	public ResponseVO changePassword(Integer id, String oldPassword, String newPassword, HttpSession session) {
		try {
			adminService.changePassword(id, oldPassword, newPassword);
			session.removeAttribute(Constants.SESSION_KEY_ADMIN);
			return getSuccessResponseVo("密码修改成功");
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}

	/**
	 * 忘记密码 - 发送重置邮件（管理员）
	 */
	@PostMapping("/forgotPassword")
	@ResponseBody
	public ResponseVO forgotPassword(@RequestParam String email) {
		try {
			Admin admin = adminService.getByEmail(email);
			if (admin == null) {
				return getErrorResponseVo("该邮箱未注册管理员账号");
			}
			String token = tokenManager.generateToken(email, "admin");
			String resetLink = "http://localhost:8080/reset-password.html?token=" + token + "&type=admin";
			mailUtil.sendPasswordResetEmail(email, admin.getUsername(), resetLink);
			return getSuccessResponseVo("重置密码邮件已发送，请检查您的邮箱");
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}

	/**
	 * 重置密码（管理员）
	 */
	@PostMapping("/resetPassword")
	@ResponseBody
	public ResponseVO resetPassword(@RequestParam String token, @RequestParam String newPassword) {
		try {
			PasswordResetTokenManager.TokenInfo tokenInfo = tokenManager.validateToken(token);
			if (tokenInfo == null) {
				return getErrorResponseVo("重置链接已过期或无效");
			}
			if (!"admin".equals(tokenInfo.getType())) {
				return getErrorResponseVo("令牌类型错误");
			}
			adminService.resetPasswordByEmail(tokenInfo.getAccount(), newPassword);
			tokenManager.removeToken(token);
			return getSuccessResponseVo("密码重置成功，请重新登录");
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}

	/**
	 * 上传头像
	 */
	@PostMapping("/uploadAvatar")
	@ResponseBody
	public ResponseVO uploadAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_KEY_ADMIN);
		if (admin == null) {
			return getErrorResponseVo("请先登录");
		}

		if (file.isEmpty()) {
			return getErrorResponseVo("请选择文件");
		}

		String originalFilename = file.getOriginalFilename();
		String suffix = "";
		if (originalFilename != null && originalFilename.contains(".")) {
			suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
		}

		String allowedSuffix = ".jpg.jpeg.png.gif.bmp.webp";
		if (!allowedSuffix.contains(suffix.toLowerCase())) {
			return getErrorResponseVo("不支持的文件格式，仅支持 jpg/png/gif/bmp/webp");
		}

		if (file.getSize() > 2 * 1024 * 1024) {
			return getErrorResponseVo("文件大小不能超过2MB");
		}

		try {
			String projectDir = System.getProperty("user.dir");
			File dir = new File(projectDir, "uploads/avatar/");
			if (!dir.exists()) {
				dir.mkdirs();
			}

			String newFileName = UUID.randomUUID().toString() + suffix;
			File dest = new File(dir, newFileName);
			file.transferTo(dest);

			String avatarUrl = "/uploads/avatar/" + newFileName;

			Admin updateAdmin = new Admin();
			updateAdmin.setAvatar(avatarUrl);
			adminService.updateById(updateAdmin, admin.getId());

			admin.setAvatar(avatarUrl);
			session.setAttribute(Constants.SESSION_KEY_ADMIN, admin);

			return getSuccessResponseVo(avatarUrl);
		} catch (IOException e) {
			return getErrorResponseVo("上传失败: " + e.getMessage());
		}
	}



	/**
	 * 禁用/启用用户（管理员操作）
	 */
	@PostMapping("/toggleUserStatus")
	@ResponseBody
	public ResponseVO toggleUserStatus(@RequestParam Integer userId, @RequestParam Integer status) {
		try {
			User updateUser = new User();
			updateUser.setStatus(status);
			updateUser.setUpdateTime(new java.util.Date());
			userService.updateById(updateUser, userId);

			// 禁用时踢下线
			if (Constants.STATUS_DISABLED.equals(status)) {
				Constants.USER_SESSION_MAP.remove(userId);
			}

			String msg = Constants.STATUS_ENABLED.equals(status) ? "用户已启用" : "用户已禁用";
			return getSuccessResponseVo(msg);
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}


	/**
	 * 获取访问统计数据（管理员后台仪表盘用）
	 */
	@GetMapping("/statisticsData")
	@ResponseBody
	public ResponseVO getStatisticsData() {
		try {
			Map<String, Object> data = new HashMap<>();

			// 1. 统计摘要（总访问量、今日、独立IP、用户/游客）
			Map<String, Object> summary = visitLogMapper.selectStatsSummary();
			data.put("summary", summary);

			// 2. 用户vs游客访问占比
			Object userCount = summary != null ? summary.get("userVisitCount") : 0;
			Object guestCount = summary != null ? summary.get("guestVisitCount") : 0;
			Map<String, Object> userGuestRatio = new HashMap<>();
			userGuestRatio.put("userVisits", userCount);
			userGuestRatio.put("guestVisits", guestCount);
			data.put("userGuestRatio", userGuestRatio);

			// 3. 分类文章访问量占比
			data.put("categoryDistribution", visitLogMapper.selectCategoryStats());

			// 4. TOP排行：文章、视频
			data.put("topArticles", visitLogMapper.selectTopTargets("article", 10));
			data.put("topVideos", visitLogMapper.selectTopTargets("video", 10));

			// 5. 年龄分布（来自user表已有接口）
			data.put("ageDistribution", userService.getAgeDistribution());

			return getSuccessResponseVo(data);
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}

}
