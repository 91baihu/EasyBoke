package com.easyboke.controller;

import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.easyboke.service.TagService;
import com.easyboke.entity.po.Tag;
import com.easyboke.entity.query.TagQuery;
import com.easyboke.entity.vo.ResponseVO;
import java.util.List;
/**
 * @author 高98
 * @Description: 文章标签表的Controller类
 * @date: 2026/05/10
 */

@RestController
@RequestMapping("/tag")
public class TagController extends ABaseController{

	@Resource
	private TagService tagService;

	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(TagQuery query) {
		return getSuccessResponseVo(tagService.findListByPage(query));
	}
	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(Tag bean){
		return getSuccessResponseVo(tagService.add(bean));
	 }
	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<Tag> listBean){
		return getSuccessResponseVo(tagService.addBatch(listBean));
	 }
	/**
	 * 新增或者修改
	 */
	@RequestMapping("addOrUpdate")
	public ResponseVO addOrUpdate(Tag bean){
		return getSuccessResponseVo(tagService.addOrUpdate(bean));
	 }
	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdate(@RequestBody List<Tag> listBean){
		return getSuccessResponseVo(tagService.addOrUpdateBatch(listBean));
	 }
	/**
	 * 根据Id查询
	 */
	@RequestMapping("getTagById")
	 public ResponseVO getTagById(Integer id){
		return getSuccessResponseVo(this.tagService.getById(id));
	 }
	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateTagById")
	 public ResponseVO updateTagById(Tag bean,Integer id){
		return getSuccessResponseVo(this.tagService.updateById(bean,id));
	 }
	/**
	 * 更新标签（简化版）
	 */
	@RequestMapping("update")
	public ResponseVO update(Tag bean){
		return getSuccessResponseVo(this.tagService.updateById(bean, bean.getId()));
	}
	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteTagById")
	 public ResponseVO deleteTagById(Integer id){
		return getSuccessResponseVo(this.tagService.deleteById(id));
	 }
	/**
	 * 根据TagName查询
	 */
	@RequestMapping("getTagByTagName")
	 public ResponseVO getTagByTagName(String tagName){
		return getSuccessResponseVo(this.tagService.getByTagName(tagName));
	 }
	/**
	 * 根据TagName更新
	 */
	@RequestMapping("updateTagByTagName")
	 public ResponseVO updateTagByTagName(Tag bean,String tagName){
		return getSuccessResponseVo(this.tagService.updateByTagName(bean,tagName));
	 }
	/**
	 * 根据TagName删除
	 */
	@RequestMapping("deleteTagByTagName")
	 public ResponseVO deleteTagByTagName(String tagName){
		return getSuccessResponseVo(this.tagService.deleteByTagName(tagName));
	 }

}