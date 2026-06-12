package com.easyboke.service;

import com.easyboke.entity.query.VisitLogQuery;
import com.easyboke.entity.po.VisitLog;
import com.easyboke.entity.vo.PaginationResultVO;
import java.util.List;
/**
 * @author 高98
 * @Description: 访问日志表对应的Service
 * @date: 2026/05/10
 */

public interface VisitLogService{

	/**
	 * 根据条件查询列表
	 */
	List<VisitLog>findListByParam(VisitLogQuery query);

	/**
	 * 根据条件查询数量
	 */
    Integer findCountByParam(VisitLogQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<VisitLog> findListByPage(VisitLogQuery query );

	/**
	 * 新增
	 */
	Integer add(VisitLog bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<VisitLog> listBean);
	/**
	 * 新增或修改
	 */
	Integer addOrUpdate(VisitLog bean);
	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<VisitLog> listBean);
	/**
	 * 根据Id查询
	 */
	 VisitLog getById(Integer id);

	/**
	 * 根据Id查询
	 */
	 Integer updateById(VisitLog bean , Integer id);

	/**
	 * 根据Id删除
	 */
	 Integer deleteById(Integer id);


}