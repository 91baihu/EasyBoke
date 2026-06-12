package com.easyboke.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
/**
 * @author 高98
 * @Description: 用户表的Mapper类
 * @date: 2026/05/10
 */

@Mapper
public interface UserMapper<T,P> extends BaseMapper {
	/**
	 * 根据Id查询
	 */
	 T selectById(@Param("id") Integer id);

	/**
	 * 根据Id更新
	 */
	 Integer updateById(@Param("bean") T t, @Param("id") Integer id);

	/**
	 * 根据Id删除
	 */
	 Integer deleteById(@Param("id") Integer id);

	/**
	 * 根据Username查询
	 */
	 T selectByUsername(@Param("username") String username);

	/**
	 * 根据Username更新
	 */
	 Integer updateByUsername(@Param("bean") T t, @Param("username") String username);

	/**
	 * 根据Username删除
	 */
	 Integer deleteByUsername(@Param("username") String username);

	/**
	 * 根据Account查询
	 */
	 T selectByAccount(@Param("account") String account);

	/**
	 * 根据Account更新
	 */
	 Integer updateByAccount(@Param("bean") T t, @Param("account") String account);

	/**
	 * 根据Account删除
	 */
	 Integer deleteByAccount(@Param("account") String account);

	/**
	 * 根据Email查询
	 */
	 T selectByEmail(@Param("email") String email);

	/**
	 * 查询用户年龄分布统计数据
	 */
	 java.util.List<java.util.Map<String, Object>> selectAgeDistribution();

}