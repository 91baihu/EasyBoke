package com.easyboke.service.impl;

import com.easyboke.common.Constants;
import com.easyboke.entity.query.AdminQuery;
import com.easyboke.entity.query.SimplePage;
import com.easyboke.entity.po.Admin;
import com.easyboke.entity.vo.PaginationResultVO;
import com.easyboke.exception.BusinessException;
import com.easyboke.mappers.AdminMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;
import com.easyboke.entity.enums.PageSize;
import com.easyboke.service.AdminService;
/**
 * @author 高98
 * @Description: 管理员表对应的ServiceImpl
 * @date: 2026/05/10
 */

@Service("adminService")
public class AdminServiceImpl implements AdminService{

	@Resource
	private AdminMapper<Admin,AdminQuery> adminMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Admin>findListByParam(AdminQuery query){
		return this.adminMapper.selectList(query);
	 }
	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(AdminQuery query){
		return this.adminMapper.selectCount(query);
	 }
	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVO<Admin> findListByPage(AdminQuery query ){
		Integer count = this.findCountByParam(query); 
		Integer pageSize=query.getPageSize()==null? PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<Admin> list = this.findListByParam(query);
		PaginationResultVO<Admin> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	 }
	/**
	 * 新增
	 */
	@Override
	public Integer add(Admin bean){
		return this.adminMapper.insert(bean);
	 }
	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Admin> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.adminMapper.insertBatch(listBean);
	 }
	/**
	 * 新增或者修改
	 */
	@Override
	public Integer addOrUpdate(Admin admin){
		return this.adminMapper.insertOrUpdate(admin);
	 }
	/**
	 * 批量新增或修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Admin> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.adminMapper.insertOrUpdateBatch(listBean);
	 }
	/**
	 * 根据Id查询
	 */
	@Override
	 public Admin getById(Integer id){
		return this.adminMapper.selectById(id);
	 }
	/**
	 * 根据Id更新
	 */
	@Override
	 public Integer updateById(Admin bean , Integer id){
		return this.adminMapper.updateById(bean,id);
	 }
	/**
	 * 根据Id删除
	 */
	@Override
	 public Integer deleteById(Integer id){
		return this.adminMapper.deleteById(id);
	 }
	/**
	 * 根据Username查询
	 */
	@Override
	 public Admin getByUsername(String username){
		return this.adminMapper.selectByUsername(username);
	 }
	/**
	 * 根据Username更新
	 */
	@Override
	 public Integer updateByUsername(Admin bean , String username){
		return this.adminMapper.updateByUsername(bean,username);
	 }
	/**
	 * 根据Username删除
	 */
	@Override
	 public Integer deleteByUsername(String username){
		return this.adminMapper.deleteByUsername(username);
	 }

	@Override
	public void changePassword(Integer id, String oldPassword, String newPassword) throws BusinessException {
		Admin admin = this.adminMapper.selectById(id);
		if (admin == null) {
			throw new BusinessException("管理员不存在");
		}

		boolean isPasswordEncrypted = admin.getPassword() != null &&
				(admin.getPassword().startsWith("$2a$") ||
						admin.getPassword().startsWith("$2b$") ||
						admin.getPassword().startsWith("$2y$"));

		boolean passwordMatches;
		if (isPasswordEncrypted) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			passwordMatches = passwordEncoder.matches(oldPassword, admin.getPassword());
		} else {
			passwordMatches = oldPassword.equals(admin.getPassword());
		}

		if (!passwordMatches) {
			throw new BusinessException("原密码错误");
		}

		Admin updateBean = new Admin();
		updateBean.setPassword(newPassword);
		this.adminMapper.updateById(updateBean, id);
	}

	@Override
	public Admin login(String username, String password) throws BusinessException {
		System.out.println("========== Service层收到的参数 ==========");
		System.out.println("输入用户名: [" + username + "]");
		System.out.println("输入密码: [" + password + "]");
		System.out.println("==========================================");

		Admin admin = this.adminMapper.selectByUsername(username);

		if (admin == null) {
			System.out.println("错误: 数据库中不存在该用户名");
			throw new BusinessException("用户名或密码错误");
		}

		System.out.println("找到用户: " + admin.getUsername());
		System.out.println("数据库密码: [" + admin.getPassword() + "]");
		System.out.println("账号状态: " + admin.getStatus());

		if (admin.getStatus() != null && admin.getStatus() == Constants.STATUS_DISABLED) {
			System.out.println("错误: 账号已被禁用");
			throw new BusinessException("账号已被禁用，请联系管理员");
		}

		boolean isPasswordEncrypted = admin.getPassword() != null &&
				(admin.getPassword().startsWith("$2a$") ||
						admin.getPassword().startsWith("$2b$") ||
						admin.getPassword().startsWith("$2y$"));

		System.out.println("密码加密方式: " + (isPasswordEncrypted ? "BCrypt加密" : "明文"));

		boolean passwordMatches;
		if (isPasswordEncrypted) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			passwordMatches = passwordEncoder.matches(password, admin.getPassword());
			System.out.println("使用BCrypt验证");
		} else {
			passwordMatches = password.equals(admin.getPassword());
			System.out.println("使用明文比较");
		}

		System.out.println("密码匹配结果: " + passwordMatches);
		System.out.println("输入密码长度: " + password.length());
		System.out.println("数据库密码长度: " + admin.getPassword().length());
		System.out.println("==========================================");

		if (!passwordMatches) {
			throw new BusinessException("用户名或密码错误");
		}

		admin.setPassword(null);

		return admin;
	}

	@Override
	public Admin getByEmail(String email) {
		return this.adminMapper.selectByEmail(email);
	}

	@Override
	public Admin resetPasswordByEmail(String email, String newPassword) throws BusinessException {
		Admin admin = this.adminMapper.selectByEmail(email);
		if (admin == null) {
			throw new BusinessException("该邮箱未注册管理员账号");
		}

		Admin updateBean = new Admin();
		updateBean.setPassword(new BCryptPasswordEncoder().encode(newPassword));
		this.adminMapper.updateById(updateBean, admin.getId());

		return admin;
	}

}



