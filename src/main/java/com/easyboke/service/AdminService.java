package com.easyboke.service;

import com.easyboke.entity.query.AdminQuery;
import com.easyboke.entity.po.Admin;
import com.easyboke.entity.vo.PaginationResultVO;
import java.util.List;
/**
 * @author 高98
 * @Description: 管理员表对应的Service
 * @date: 2026/05/10
 */

public interface AdminService{

	/**
	 * 根据条件查询列表
	 */
	List<Admin>findListByParam(AdminQuery query);

	/**
	 * 根据条件查询数量
	 */
    Integer findCountByParam(AdminQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Admin> findListByPage(AdminQuery query );

	/**
	 * 新增
	 */
	Integer add(Admin bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<Admin> listBean);
	/**
	 * 新增或修改
	 */
	Integer addOrUpdate(Admin bean);
	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<Admin> listBean);
	/**
	 * 根据Id查询
	 */
	 Admin getById(Integer id);

	/**
	 * 根据Id查询
	 */
	 Integer updateById(Admin bean , Integer id);

	/**
	 * 根据Id删除
	 */
	 Integer deleteById(Integer id);

	/**
	 * 根据Username查询
	 */
	 Admin getByUsername(String username);

	/**
	 * 根据Username查询
	 */
	 Integer updateByUsername(Admin bean , String username);

	/**
	 * 根据Username删除
	 */
	 Integer deleteByUsername(String username);

	/**
	 * 管理员登录
	 */
	Admin login(String username, String password) throws com.easyboke.exception.BusinessException;

	/**
	 * 修改密码
	 */
	void changePassword(Integer id, String oldPassword, String newPassword) throws com.easyboke.exception.BusinessException;

	/**
	 * 根据邮箱查询
	 */
	 Admin getByEmail(String email);

	/**
	 * 根据邮箱重置密码（忘记密码流程）
	 */
	Admin resetPasswordByEmail(String email, String newPassword) throws com.easyboke.exception.BusinessException;

}