package com.easyboke.service.impl;

import com.easyboke.common.Constants;
import com.easyboke.entity.query.UserQuery;
import com.easyboke.entity.query.SimplePage;
import com.easyboke.entity.po.User;
import com.easyboke.entity.vo.PaginationResultVO;
import com.easyboke.exception.BusinessException;
import com.easyboke.mappers.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;
import com.easyboke.entity.enums.PageSize;
import com.easyboke.service.UserService;
/**
 * @author 高98
 * @Description: 用户表对应的ServiceImpl
 * @date: 2026/05/10
 */

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper<User,UserQuery> userMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<User>findListByParam(UserQuery query){
		return this.userMapper.selectList(query);
	 }
	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(UserQuery query){
		return this.userMapper.selectCount(query);
	 }
	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVO<User> findListByPage(UserQuery query ){
		Integer count = this.findCountByParam(query); 
		Integer pageSize=query.getPageSize()==null? PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<User> list = this.findListByParam(query);
		PaginationResultVO<User> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	 }
	/**
	 * 新增
	 */
	@Override
	public Integer add(User bean){
		return this.userMapper.insert(bean);
	 }
	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<User> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.userMapper.insertBatch(listBean);
	 }
	/**
	 * 新增或者修改
	 */
	@Override
	public Integer addOrUpdate(User user){
		return this.userMapper.insertOrUpdate(user);
	 }
	/**
	 * 批量新增或修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<User> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.userMapper.insertOrUpdateBatch(listBean);
	 }
	/**
	 * 根据Id查询
	 */
	@Override
	 public User getById(Integer id){
		return this.userMapper.selectById(id);
	 }
	/**
	 * 根据Id更新
	 */
	@Override
	 public Integer updateById(User bean , Integer id){
		return this.userMapper.updateById(bean,id);
	 }
	/**
	 * 根据Id删除
	 */
	@Override
	 public Integer deleteById(Integer id){
		return this.userMapper.deleteById(id);
	 }
	/**
	 * 根据Username查询
	 */
	@Override
	 public User getByUsername(String username){
		return this.userMapper.selectByUsername(username);
	 }
	/**
	 * 根据Username更新
	 */
	@Override
	 public Integer updateByUsername(User bean , String username){
		return this.userMapper.updateByUsername(bean,username);
	 }
	/**
	 * 根据Username删除
	 */
	@Override
	 public Integer deleteByUsername(String username){
		return this.userMapper.deleteByUsername(username);
	 }
	/**
	 * 根据Account查询
	 */
	@Override
	 public User getByAccount(String account){
		return this.userMapper.selectByAccount(account);
	 }
	/**
	 * 根据Account更新
	 */
	@Override
	 public Integer updateByAccount(User bean , String account){
		return this.userMapper.updateByAccount(bean,account);
	 }
	/**
	 * 根据Account删除
	 */
	@Override
	 public Integer deleteByAccount(String account){
		return this.userMapper.deleteByAccount(account);
	 }

	@Override
	public User login(String username, String password) throws BusinessException {
		User user = this.userMapper.selectByUsername(username);
			if (user == null) {
				user = this.userMapper.selectByAccount(username);
			}
		if (user == null) {
			throw new BusinessException("用户名或密码错误");
		}

		if (user.getStatus() != null && user.getStatus() == Constants.STATUS_DISABLED) {
			throw new BusinessException("账号已被禁用，请联系管理员");
		}

		// 判断密码是否已经加密（BCrypt密码通常以$2a$、$2b$或$2y$开头）
		boolean isPasswordEncrypted = user.getPassword() != null &&
				(user.getPassword().startsWith("$2a$") ||
						user.getPassword().startsWith("$2b$") ||
						user.getPassword().startsWith("$2y$"));

		boolean passwordMatches;
		if (isPasswordEncrypted) {
			// 使用BCrypt验证
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			passwordMatches = passwordEncoder.matches(password, user.getPassword());
		} else {
			// 直接比较明文密码
			passwordMatches = password.equals(user.getPassword());
		}

		if (!passwordMatches) {
			throw new BusinessException("用户名或密码错误");
		}

		user.setPassword(null);

		return user;
	}


	@Override
	public User register(User user) throws BusinessException {
		if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
			throw new BusinessException("用户名不能为空");
		}

		if (user.getAccount() == null || user.getAccount().trim().isEmpty()) {
			throw new BusinessException("账号不能为空");
		}

		if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
			throw new BusinessException("密码不能为空");
		}

		String usernamePattern = "^[a-zA-Z0-9_]{4,20}$";
		if (!user.getUsername().matches(usernamePattern)) {
			throw new BusinessException("用户名格式不正确，只能包含字母、数字、下划线，长度4-20位");
		}

		if (!user.getAccount().matches(usernamePattern)) {
			throw new BusinessException("账号格式不正确，只能包含字母、数字、下划线，长度4-20位");
		}

		if (user.getPassword().length() < 6) {
			throw new BusinessException("密码长度不能少于6位");
		}

		User existUser = this.userMapper.selectByUsername(user.getUsername());
		if (existUser != null) {
			throw new BusinessException("用户名已被注册");
		}

		existUser = this.userMapper.selectByAccount(user.getAccount());
		if (existUser != null) {
			throw new BusinessException("账号已被注册");
		}

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

		if (user.getGender() == null) {
			user.setGender(0);
		}

		// 如果有生日，根据生日自动计算年龄
		if (user.getBirthday() != null) {
			Integer age = calculateAge(user.getBirthday());
			user.setAge(age != null ? age : 0);
		} else if (user.getAge() == null || user.getAge() < 0 || user.getAge() > 150) {
			user.setAge(0);
		}

		user.setStatus(Constants.STATUS_ENABLED);

		java.util.Date now = new java.util.Date();
		user.setCreateTime(now);
		user.setUpdateTime(now);

		Integer result = this.userMapper.insert(user);
		if (result <= 0) {
			throw new BusinessException("注册失败，请稍后重试");
		}

		user.setPassword(null);
		return user;
	}
	@Override
	public void changePassword(Integer id, String oldPassword, String newPassword) throws BusinessException {
		User user = this.userMapper.selectById(id);
		if (user == null) {
			throw new BusinessException("用户不存在");
		}

		// 验证旧密码
		boolean isPasswordEncrypted = user.getPassword() != null &&
				(user.getPassword().startsWith("$2a$") ||
						user.getPassword().startsWith("$2b$") ||
						user.getPassword().startsWith("$2y$"));

		boolean passwordMatches;
		if (isPasswordEncrypted) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			passwordMatches = passwordEncoder.matches(oldPassword, user.getPassword());
		} else {
			passwordMatches = oldPassword.equals(user.getPassword());
		}

		if (!passwordMatches) {
			throw new BusinessException("原密码错误");
		}

		// 更新新密码
		User updateBean = new User();
		updateBean.setPassword(newPassword);
		updateBean.setUpdateTime(new java.util.Date());
		this.userMapper.updateById(updateBean, id);
	}

	@Override
	public java.util.List<java.util.Map<String, Object>> getAgeDistribution() {
		return this.userMapper.selectAgeDistribution();
	}

	@Override
	public User getByEmail(String email) {
		return this.userMapper.selectByEmail(email);
	}

	@Override
	public User resetPasswordByAccount(String account, String newPassword) throws BusinessException {
		User user = this.userMapper.selectByAccount(account);
		if (user == null) {
			throw new BusinessException("该账号不存在");
		}

		User updateBean = new User();
		updateBean.setPassword(new BCryptPasswordEncoder().encode(newPassword));
		updateBean.setUpdateTime(new java.util.Date());
		this.userMapper.updateById(updateBean, user.getId());

		return user;
	}

	/**
	 * 根据生日计算年龄
	 */
	private Integer calculateAge(java.util.Date birthday) {
		if (birthday == null) return null;
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int currentYear = cal.get(java.util.Calendar.YEAR);
		int currentMonth = cal.get(java.util.Calendar.MONTH) + 1;
		int currentDay = cal.get(java.util.Calendar.DAY_OF_MONTH);

		cal.setTime(birthday);
		int birthYear = cal.get(java.util.Calendar.YEAR);
		int birthMonth = cal.get(java.util.Calendar.MONTH) + 1;
		int birthDay = cal.get(java.util.Calendar.DAY_OF_MONTH);

		int age = currentYear - birthYear;
		// 如果今年的生日还没过，减一岁
		if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
			age--;
		}
		return age;
	}


}
