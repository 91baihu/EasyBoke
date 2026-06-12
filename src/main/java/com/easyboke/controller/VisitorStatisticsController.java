package com.easyboke.controller;

import com.easyboke.controller.ABaseController;
import com.easyboke.entity.query.VisitorStatisticsQuery;
import com.easyboke.entity.query.SimplePage;
import com.easyboke.entity.po.VisitorStatistics;
import com.easyboke.entity.vo.PaginationResultVO;
import com.easyboke.entity.vo.ResponseVO;
import com.easyboke.mappers.VisitorStatisticsMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import javax.annotation.Resource;
import com.easyboke.entity.enums.PageSize;
import com.easyboke.service.VisitorStatisticsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// ... existing code ...

/**
 * @author 高98
 * @Description: 访客统计表的Controller类
 * @date: 2026/05/10
 */

@RestController
@RequestMapping("/visitorStatistics")
public class VisitorStatisticsController extends ABaseController {

	@Resource
	private VisitorStatisticsService visitorStatisticsService;

	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(VisitorStatisticsQuery query) {
		return getSuccessResponseVo(visitorStatisticsService.findListByPage(query));
	}
	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(VisitorStatistics bean){
		return getSuccessResponseVo(visitorStatisticsService.add(bean));
	 }
	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<VisitorStatistics> listBean){
		return getSuccessResponseVo(visitorStatisticsService.addBatch(listBean));
	 }
	/**
	 * 新增或者修改
	 */
	@RequestMapping("addOrUpdate")
	public ResponseVO addOrUpdate(VisitorStatistics bean){
		return getSuccessResponseVo(visitorStatisticsService.addOrUpdate(bean));
	 }
	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdate(@RequestBody List<VisitorStatistics> listBean){
		return getSuccessResponseVo(visitorStatisticsService.addOrUpdateBatch(listBean));
	 }
	/**
	 * 根据Id查询
	 */
	@RequestMapping("getVisitorStatisticsById")
	 public ResponseVO getVisitorStatisticsById(Integer id){
		return getSuccessResponseVo(this.visitorStatisticsService.getById(id));
	 }
	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateVisitorStatisticsById")
	 public ResponseVO updateVisitorStatisticsById(VisitorStatistics bean,Integer id){
		return getSuccessResponseVo(this.visitorStatisticsService.updateById(bean,id));
	 }
	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteVisitorStatisticsById")
	 public ResponseVO deleteVisitorStatisticsById(Integer id){
		return getSuccessResponseVo(this.visitorStatisticsService.deleteById(id));
	 }
	/**
	 * 根据VisitDate查询
	 */
	@RequestMapping("getVisitorStatisticsByVisitDate")
	 public ResponseVO getVisitorStatisticsByVisitDate(Date visitDate){
		return getSuccessResponseVo(this.visitorStatisticsService.getByVisitDate(visitDate));
	 }
	/**
	 * 根据VisitDate更新
	 */
	@RequestMapping("updateVisitorStatisticsByVisitDate")
	 public ResponseVO updateVisitorStatisticsByVisitDate(VisitorStatistics bean,Date visitDate){
		return getSuccessResponseVo(this.visitorStatisticsService.updateByVisitDate(bean,visitDate));
	 }
	/**
	 * 根据VisitDate删除
	 */
	@RequestMapping("deleteVisitorStatisticsByVisitDate")
	 public ResponseVO deleteVisitorStatisticsByVisitDate(Date visitDate){
		return getSuccessResponseVo(this.visitorStatisticsService.deleteByVisitDate(visitDate));
	 }

}