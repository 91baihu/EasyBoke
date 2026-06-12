package com.easyboke.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.Date;
/**
 * @author 高98
 * @Description: 访客统计表的Mapper类
 * @date: 2026/05/10
 */

@Mapper
public interface VisitorStatisticsMapper<T,P> extends BaseMapper {
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
	 * 根据VisitDate查询
	 */
	 T selectByVisitDate(@Param("visitDate") Date visitDate);

	/**
	 * 根据VisitDate更新
	 */
	 Integer updateByVisitDate(@Param("bean") T t, @Param("visitDate") Date visitDate);

	/**
	 * 根据VisitDate删除
	 */
	 Integer deleteByVisitDate(@Param("visitDate") Date visitDate);

}