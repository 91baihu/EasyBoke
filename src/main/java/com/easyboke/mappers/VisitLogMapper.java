package com.easyboke.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
/**
 * @author 高98
 * @Description: 访问日志表的Mapper类
 * @date: 2026/05/10
 */

@Mapper
public interface VisitLogMapper<T,P> extends BaseMapper {
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
	 * 统计摘要：总访问量、今日访问量、独立IP数、用户/游客访问量
	 */
	java.util.Map<String, Object> selectStatsSummary();

	/**
	 * TOP目标排行（按访问量）
	 */
	java.util.List<java.util.Map<String, Object>> selectTopTargets(@Param("targetType") String targetType, @Param("limit") Integer limit);

	/**
	 * 分类文章访问量占比
	 */
	java.util.List<java.util.Map<String, Object>> selectCategoryStats();

}