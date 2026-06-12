package com.easyboke.service.impl;

import com.easyboke.entity.query.VisitLogQuery;
import com.easyboke.entity.query.SimplePage;
import com.easyboke.entity.po.VisitLog;
import com.easyboke.entity.vo.PaginationResultVO;
import com.easyboke.mappers.VisitLogMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;
import com.easyboke.entity.enums.PageSize;
import com.easyboke.service.VisitLogService;
/**
 * @author 高98
 * @Description: 访问日志表对应的ServiceImpl
 * @date: 2026/05/10
 */

@Service("visitLogService")
public class VisitLogServiceImpl implements VisitLogService{

	@Resource
	private VisitLogMapper<VisitLog,VisitLogQuery> visitLogMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<VisitLog>findListByParam(VisitLogQuery query){
		return this.visitLogMapper.selectList(query);
	 }
	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(VisitLogQuery query){
		return this.visitLogMapper.selectCount(query);
	 }
	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVO<VisitLog> findListByPage(VisitLogQuery query ){
		Integer count = this.findCountByParam(query); 
		Integer pageSize=query.getPageSize()==null? PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<VisitLog> list = this.findListByParam(query);
		PaginationResultVO<VisitLog> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	 }
	/**
	 * 新增
	 */
	@Override
	public Integer add(VisitLog bean){
		return this.visitLogMapper.insert(bean);
	 }
	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<VisitLog> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.visitLogMapper.insertBatch(listBean);
	 }
	/**
	 * 新增或者修改
	 */
	@Override
	public Integer addOrUpdate(VisitLog visitLog){
		return this.visitLogMapper.insertOrUpdate(visitLog);
	 }
	/**
	 * 批量新增或修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<VisitLog> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.visitLogMapper.insertOrUpdateBatch(listBean);
	 }
	/**
	 * 根据Id查询
	 */
	@Override
	 public VisitLog getById(Integer id){
		return this.visitLogMapper.selectById(id);
	 }
	/**
	 * 根据Id更新
	 */
	@Override
	 public Integer updateById(VisitLog bean , Integer id){
		return this.visitLogMapper.updateById(bean,id);
	 }
	/**
	 * 根据Id删除
	 */
	@Override
	 public Integer deleteById(Integer id){
		return this.visitLogMapper.deleteById(id);
	 }

}