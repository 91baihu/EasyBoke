package com.easyboke.service;

import com.easyboke.entity.query.VisitorStatisticsQuery;
import com.easyboke.entity.po.VisitorStatistics;
import com.easyboke.entity.vo.PaginationResultVO;
import java.util.List;
import java.util.Date;
/**
 * @author 高98
 * @Description: 访客统计表对应的Service
 * @date: 2026/05/10
 */

public interface VisitorStatisticsService{

	/**
	 * 根据条件查询列表
	 */
	List<VisitorStatistics>findListByParam(VisitorStatisticsQuery query);

	/**
	 * 根据条件查询数量
	 */
    Integer findCountByParam(VisitorStatisticsQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<VisitorStatistics> findListByPage(VisitorStatisticsQuery query );

	/**
	 * 新增
	 */
	Integer add(VisitorStatistics bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<VisitorStatistics> listBean);
	/**
	 * 新增或修改
	 */
	Integer addOrUpdate(VisitorStatistics bean);
	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<VisitorStatistics> listBean);
	/**
	 * 根据Id查询
	 */
	 VisitorStatistics getById(Integer id);

	/**
	 * 根据Id查询
	 */
	 Integer updateById(VisitorStatistics bean , Integer id);

	/**
	 * 根据Id删除
	 */
	 Integer deleteById(Integer id);

	/**
	 * 根据VisitDate查询
	 */
	 VisitorStatistics getByVisitDate(Date visitDate);

	/**
	 * 根据VisitDate查询
	 */
	 Integer updateByVisitDate(VisitorStatistics bean , Date visitDate);

	/**
	 * 根据VisitDate删除
	 */
	 Integer deleteByVisitDate(Date visitDate);


}