package com.easyboke.controller;

import com.easyboke.common.Constants;
import com.easyboke.dto.LoginDTO;
import com.easyboke.entity.po.User;
import com.easyboke.service.UserService;
import com.easyboke.entity.vo.ResponseVO;
import com.easyboke.utils.MailUtil;
import com.easyboke.utils.PasswordResetTokenManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController extends ABaseController {

	@Resource
	private UserService userService;

	@Resource
	private MailUtil mailUtil;

	@Resource
	private PasswordResetTokenManager tokenManager;

	/**
	 * 用户登录页面
	 */
	@GetMapping("/login")
	public String loginPage() {
		return "redirect:/login.html";
	}

	/**
	 * 普通用户登录
	 */
	@PostMapping("/login")
	@ResponseBody
	public ResponseVO login(@RequestBody LoginDTO loginDTO, HttpSession session) throws com.easyboke.exception.BusinessException{
		try {
			User user = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
			// 单设备登录控制：记录新session，旧session下次请求会被踢下线
			Constants.USER_SESSION_MAP.put(user.getId(), session.getId());
			session.setAttribute(Constants.SESSION_KEY_USER, user);
			return getSuccessResponseVo(user);
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}

	/**
	 * 用户登出
	 */
	@PostMapping("/logout")
	@ResponseBody
	public ResponseVO logout(HttpSession session) {
		User user = (User) session.getAttribute(Constants.SESSION_KEY_USER);
		if (user != null) {
			Constants.USER_SESSION_MAP.remove(user.getId());
		}
		session.removeAttribute(Constants.SESSION_KEY_USER);
		return getSuccessResponseVo("登出成功");
	}

	/**
	 * 获取当前登录用户信息
	 */
	@GetMapping("/currentUser")
	@ResponseBody
	public ResponseVO getCurrentUser(HttpSession session) {
		User user = (User) session.getAttribute(Constants.SESSION_KEY_USER);
		if (user == null) {
			return getErrorResponseVo("未登录");
		}
		return getSuccessResponseVo(user);
	}

	// ... existing code ...
	//注册
	@GetMapping("/register")
	public String registerPage() {
		return "redirect:/register.html";
	}

	@PostMapping("/register")
	@ResponseBody
	public ResponseVO register(@RequestBody com.easyboke.dto.RegisterDTO registerDTO) throws com.easyboke.exception.BusinessException{
		try {
			User user = new User();
			user.setUsername(registerDTO.getUsername());
			user.setAccount(registerDTO.getAccount());
			user.setPassword(registerDTO.getPassword());
			user.setGender(registerDTO.getGender());
			user.setAge(registerDTO.getAge());
			// 处理生日
			if (registerDTO.getBirthday() != null && !registerDTO.getBirthday().isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				user.setBirthday(sdf.parse(registerDTO.getBirthday()));
			}

			User registeredUser = userService.register(user);
			return getSuccessResponseVo(registeredUser);
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}


	/**
	 * 根据条件分页查询用户列表（管理员）
	 */
	@RequestMapping("loadDataList")
	@ResponseBody
	public ResponseVO loadDataList(com.easyboke.entity.query.UserQuery query) {
		return getSuccessResponseVo(userService.findListByPage(query));
	}

	/**
	 * 根据Id查询用户（管理员）
	 */
	@RequestMapping("getUserById")
	@ResponseBody
	public ResponseVO getUserById(Integer id) {
		return getSuccessResponseVo(this.userService.getById(id));
	}

	/**
	 * 根据Id更新用户（管理员）
	 */
	@RequestMapping("updateUserById")
	@ResponseBody
	public ResponseVO updateUserById(User bean, Integer id) {
		return getSuccessResponseVo(this.userService.updateById(bean, id));
	}
	/**
	 * 修改密码
	 */
	@RequestMapping("changePassword")
	@ResponseBody
	public ResponseVO changePassword(Integer id, String oldPassword, String newPassword, HttpSession session) {
		try {
			userService.changePassword(id, oldPassword, newPassword);
			session.removeAttribute(Constants.SESSION_KEY_USER);
			return getSuccessResponseVo("密码修改成功");
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}

	/**
	 * 个人中心页面
	 */
	@GetMapping("/profile")
	public String profilePage() {
		return "redirect:/profile.html";
	}

	/**
	 * 忘记密码 - 发送重置邮件
	 */
	@PostMapping("/forgotPassword")
	@ResponseBody
	public ResponseVO forgotPassword(@RequestParam String account) {
		try {
			// 根据账号查找用户
			com.easyboke.entity.po.User existUser = userService.getByAccount(account);
			if (existUser == null) {
				return getErrorResponseVo("该账号不存在");
			}
			String email = existUser.getEmail();
			if (email == null || email.trim().isEmpty()) {
				return getErrorResponseVo("该账号未绑定邮箱，无法重置密码");
			}
			// 生成token（用账号做标识，确保唯一）
			String token = tokenManager.generateToken(account, "user");
			// 构建重置链接
			String resetLink = "http://localhost:8080/reset-password.html?token=" + token + "&type=user";
			// 发送邮件到用户绑定的邮箱
			mailUtil.sendPasswordResetEmail(email, account, resetLink);
			return getSuccessResponseVo("重置密码邮件已发送，请检查您的邮箱");
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}

	/**
	 * 重置密码
	 */
	@PostMapping("/resetPassword")
	@ResponseBody
	public ResponseVO resetPassword(@RequestParam String token, @RequestParam String newPassword) {
		try {
			PasswordResetTokenManager.TokenInfo tokenInfo = tokenManager.validateToken(token);
			if (tokenInfo == null) {
				return getErrorResponseVo("重置链接已过期或无效");
			}
			if (!"user".equals(tokenInfo.getType())) {
				return getErrorResponseVo("令牌类型错误");
			}
			userService.resetPasswordByAccount(tokenInfo.getAccount(), newPassword);
			tokenManager.removeToken(token);
			return getSuccessResponseVo("密码重置成功，请重新登录");
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}

	/**
	 * 更新用户个人资料（个人中心）
	 */
	@PostMapping("/updateProfile")
	@ResponseBody
	public ResponseVO updateProfile(Integer id, Integer gender, Integer age,
									  @RequestParam(required = false) String birthday,
									  HttpSession session) {
		try {
			User updateUser = new User();
			if (gender != null) updateUser.setGender(gender);
			if (age != null) updateUser.setAge(age);
			if (birthday != null && !birthday.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				updateUser.setBirthday(sdf.parse(birthday));
			}
			updateUser.setUpdateTime(new java.util.Date());
			userService.updateById(updateUser, id);
			// 刷新session
			User refreshed = userService.getById(id);
			refreshed.setPassword(null);
			session.setAttribute(Constants.SESSION_KEY_USER, refreshed);
			return getSuccessResponseVo("更新成功");
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}

	/**
	 * 获取用户年龄分布统计数据
	 */
	@GetMapping("/ageDistribution")
	@ResponseBody
	public ResponseVO getAgeDistribution() {
		try {
			return getSuccessResponseVo(userService.getAgeDistribution());
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
		User user = (User) session.getAttribute(Constants.SESSION_KEY_USER);
		if (user == null) {
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

			User updateUser = new User();
			updateUser.setAvatar(avatarUrl);
			userService.updateById(updateUser, user.getId());

			user.setAvatar(avatarUrl);
			session.setAttribute(Constants.SESSION_KEY_USER, user);

			return getSuccessResponseVo(avatarUrl);
		} catch (IOException e) {
			return getErrorResponseVo("上传失败: " + e.getMessage());
		}
	}


}
