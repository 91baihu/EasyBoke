package com.easyboke.service.impl;

import com.easyboke.entity.query.VisitorStatisticsQuery;
import com.easyboke.entity.query.SimplePage;
import com.easyboke.entity.po.VisitorStatistics;
import com.easyboke.entity.vo.PaginationResultVO;
import com.easyboke.mappers.VisitorStatisticsMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import com.easyboke.entity.enums.PageSize;
import com.easyboke.service.VisitorStatisticsService;

/**
 * @author 高98
 * @Description: 访客统计表对应的ServiceImpl
 * @date: 2026/05/10
 */

@Service("visitorStatisticsService")
public class VisitorStatisticsServiceImpl implements VisitorStatisticsService{

	@Resource
	private VisitorStatisticsMapper<VisitorStatistics,VisitorStatisticsQuery> visitorStatisticsMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<VisitorStatistics>findListByParam(VisitorStatisticsQuery query){
		return this.visitorStatisticsMapper.selectList(query);
	 }
	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(VisitorStatisticsQuery query){
		return this.visitorStatisticsMapper.selectCount(query);
	 }
	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVO<VisitorStatistics> findListByPage(VisitorStatisticsQuery query ){
		Integer count = this.findCountByParam(query); 
		Integer pageSize=query.getPageSize()==null? PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<VisitorStatistics> list = this.findListByParam(query);
		PaginationResultVO<VisitorStatistics> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	 }
	/**
	 * 新增
	 */
	@Override
	public Integer add(VisitorStatistics bean){
		return this.visitorStatisticsMapper.insert(bean);
	 }
	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<VisitorStatistics> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.visitorStatisticsMapper.insertBatch(listBean);
	 }
	/**
	 * 新增或者修改
	 */
	@Override
	public Integer addOrUpdate(VisitorStatistics visitorStatistics){
		return this.visitorStatisticsMapper.insertOrUpdate(visitorStatistics);
	 }
	/**
	 * 批量新增或修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<VisitorStatistics> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.visitorStatisticsMapper.insertOrUpdateBatch(listBean);
	 }
	/**
	 * 根据Id查询
	 */
	@Override
	 public VisitorStatistics getById(Integer id){
		return this.visitorStatisticsMapper.selectById(id);
	 }
	/**
	 * 根据Id更新
	 */
	@Override
	 public Integer updateById(VisitorStatistics bean , Integer id){
		return this.visitorStatisticsMapper.updateById(bean,id);
	 }
	/**
	 * 根据Id删除
	 */
	@Override
	 public Integer deleteById(Integer id){
		return this.visitorStatisticsMapper.deleteById(id);
	 }
	/**
	 * 根据VisitDate查询
	 */
	@Override
	 public VisitorStatistics getByVisitDate(Date visitDate){
		return this.visitorStatisticsMapper.selectByVisitDate(visitDate);
	 }
	/**
	 * 根据VisitDate更新
	 */
	@Override
	 public Integer updateByVisitDate(VisitorStatistics bean , Date visitDate){
		return this.visitorStatisticsMapper.updateByVisitDate(bean,visitDate);
	 }
	/**
	 * 根据VisitDate删除
	 */
	@Override
	 public Integer deleteByVisitDate(Date visitDate){
		return this.visitorStatisticsMapper.deleteByVisitDate(visitDate);
	 }

}