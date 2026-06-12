package com.easyboke.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper<T,P> extends BaseMapper {

	T selectById(@Param("id") Integer id);

	Integer updateById(@Param("bean") T t, @Param("id") Integer id);

	Integer deleteById(@Param("id") Integer id);

	/**
	 * 增加浏览量
	 */
	Integer incrementViewCount(@Param("id") Integer id);
	/**
	 * 增加男性访问量
	 */
	Integer incrementMaleViewCount(@Param("id") Integer id);

	/**
	 * 增加女性访问量
	 */
	Integer incrementFemaleViewCount(@Param("id") Integer id);

	/**
	 * 增加未知性别访问量
	 */
	Integer incrementUnknownViewCount(@Param("id") Integer id);

	/**
	 * 增加18岁以下访问量
	 */
	Integer incrementAgeUnder18(@Param("id") Integer id);

	/**
	 * 增加18-25岁访问量
	 */
	Integer incrementAge1825(@Param("id") Integer id);

	/**
	 * 增加26-35岁访问量
	 */
	Integer incrementAge2635(@Param("id") Integer id);

	/**
	 * 增加36-45岁访问量
	 */
	Integer incrementAge3645(@Param("id") Integer id);

	/**
	 * 增加46岁以上访问量
	 */
	Integer incrementAgeAbove45(@Param("id") Integer id);

	/**
	 * 根据标签ID查询文章列表
	 */
	java.util.List<T> selectListByTagId(@Param("tagId") Integer tagId, @Param("status") Integer status);

	Integer incrementLikeCount(@Param("id") Integer id);

	Integer decrementLikeCount(@Param("id") Integer id);

	/**
	 * 查询TOP热度文章（按权重公式排序，支持时间段筛选）
	 */
	java.util.List<T> selectTopByHeat(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("limit") Integer limit);

}
