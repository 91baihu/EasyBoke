package com.easyboke.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
/**
 * @author 高98
 * @Description: 管理员表的Mapper类
 * @date: 2026/05/10
 */

@Mapper
public interface AdminMapper<T,P> extends BaseMapper {
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
	 * 根据Email查询
	 */
	 T selectByEmail(@Param("email") String email);

}