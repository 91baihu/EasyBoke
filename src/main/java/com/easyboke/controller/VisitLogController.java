package com.easyboke.controller;

import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.easyboke.service.VisitLogService;
import com.easyboke.entity.po.VisitLog;
import com.easyboke.entity.query.VisitLogQuery;
import com.easyboke.entity.vo.ResponseVO;
import java.util.List;
/**
 * @author 高98
 * @Description: 访问日志表的Controller类
 * @date: 2026/05/10
 */

@RestController
@RequestMapping("/visitLog")
public class VisitLogController extends ABaseController{

	@Resource
	private VisitLogService visitLogService;

	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(VisitLogQuery query) {
		return getSuccessResponseVo(visitLogService.findListByPage(query));
	}
	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(VisitLog bean){
		return getSuccessResponseVo(visitLogService.add(bean));
	 }
	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<VisitLog> listBean){
		return getSuccessResponseVo(visitLogService.addBatch(listBean));
	 }
	/**
	 * 新增或者修改
	 */
	@RequestMapping("addOrUpdate")
	public ResponseVO addOrUpdate(VisitLog bean){
		return getSuccessResponseVo(visitLogService.addOrUpdate(bean));
	 }
	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdate(@RequestBody List<VisitLog> listBean){
		return getSuccessResponseVo(visitLogService.addOrUpdateBatch(listBean));
	 }
	/**
	 * 根据Id查询
	 */
	@RequestMapping("getVisitLogById")
	 public ResponseVO getVisitLogById(Integer id){
		return getSuccessResponseVo(this.visitLogService.getById(id));
	 }
	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateVisitLogById")
	 public ResponseVO updateVisitLogById(VisitLog bean,Integer id){
		return getSuccessResponseVo(this.visitLogService.updateById(bean,id));
	 }
	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteVisitLogById")
	 public ResponseVO deleteVisitLogById(Integer id){
		return getSuccessResponseVo(this.visitLogService.deleteById(id));
	 }

}