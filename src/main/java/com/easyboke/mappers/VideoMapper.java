package com.easyboke.mappers;

import com.easyboke.entity.po.Video;
import com.easyboke.entity.query.VideoQuery;
import com.easyboke.mappers.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface VideoMapper<T, Q> extends BaseMapper<T, Q> {
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
	 * 增加播放量
	 */
	Integer incrementViewCount(Integer id);

	/**
	 * 增加男性访问量
	 */
	Integer incrementMaleViewCount(Integer id);

	/**
	 * 增加女性访问量
	 */
	Integer incrementFemaleViewCount(Integer id);

	/**
	 * 增加未知性别访问量
	 */
	Integer incrementUnknownViewCount(Integer id);

	/**
	 * 增加18岁以下访问量
	 */
	Integer incrementAgeUnder18(Integer id);

	/**
	 * 增加18-25岁访问量
	 */
	Integer incrementAge1825(Integer id);

	/**
	 * 增加26-35岁访问量
	 */
	Integer incrementAge2635(Integer id);

	/**
	 * 增加36-45岁访问量
	 */
	Integer incrementAge3645(Integer id);

	/**
	 * 增加46岁以上访问量
	 */
	Integer incrementAgeAbove45(Integer id);

	/**
	 * 增加点赞数
	 */
	Integer incrementLikeCount(Integer id);

	/**
	 * 减少点赞数
	 */
	Integer decrementLikeCount(Integer id);
}
