package com.easyboke.service;

import com.easyboke.entity.query.UserQuery;
import com.easyboke.entity.po.User;
import com.easyboke.entity.vo.PaginationResultVO;
import com.easyboke.exception.BusinessException;

import java.util.List;
/**
 * @author 高98
 * @Description: 用户表对应的Service
 * @date: 2026/05/10
 */

public interface UserService{

	/**
	 * 根据条件查询列表
	 */
	List<User>findListByParam(UserQuery query);

	/**
	 * 根据条件查询数量
	 */
    Integer findCountByParam(UserQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<User> findListByPage(UserQuery query );

	/**
	 * 新增
	 */
	Integer add(User bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<User> listBean);
	/**
	 * 新增或修改
	 */
	Integer addOrUpdate(User bean);
	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<User> listBean);
	/**
	 * 根据Id查询
	 */
	 User getById(Integer id);

	/**
	 * 根据Id查询
	 */
	 Integer updateById(User bean , Integer id);

	/**
	 * 根据Id删除
	 */
	 Integer deleteById(Integer id);

	/**
	 * 根据Username查询
	 */
	 User getByUsername(String username);

	/**
	 * 根据Username查询
	 */
	 Integer updateByUsername(User bean , String username);

	/**
	 * 根据Username删除
	 */
	 Integer deleteByUsername(String username);

	/**
	 * 根据Account查询
	 */
	 User getByAccount(String account);

	/**
	 * 根据Account查询
	 */
	 Integer updateByAccount(User bean , String account);

	/**
	 * 根据Account删除
	 */
	 Integer deleteByAccount(String account);

	/**
	 * 用户登录
	 */
	User login(String username, String password) throws com.easyboke.exception.BusinessException;

	User register(User user) throws com.easyboke.exception.BusinessException;
	/**
	 * 修改密码
	 */
	void changePassword(Integer id, String oldPassword, String newPassword) throws BusinessException;

	/**
	 * 根据邮箱查询
	 */
	 User getByEmail(String email);

	/**
	 * 根据账号重置密码（忘记密码流程）
	 */
	User resetPasswordByAccount(String account, String newPassword) throws BusinessException;

	/**
	 * 获取用户年龄分布统计数据
	 */
	java.util.List<java.util.Map<String, Object>> getAgeDistribution();

}