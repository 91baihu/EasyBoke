package com.easyboke.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
/**
 * @author 高98
 * @Description: 文章标签表的Mapper类
 * @date: 2026/05/10
 */

@Mapper
public interface TagMapper<T,P> extends BaseMapper<T, P> {
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
	 * 根据TagName查询
	 */
	T selectByTagName(@Param("tagName") String tagName);

	/**
	 * 根据TagName更新
	 */
	Integer updateByTagName(@Param("bean") T t, @Param("tagName") String tagName);

	/**
	 * 根据TagName删除
	 */
	Integer deleteByTagName(@Param("tagName") String tagName);

}
