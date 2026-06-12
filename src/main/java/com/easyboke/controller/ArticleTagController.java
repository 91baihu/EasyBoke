package com.easyboke.controller;

import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.easyboke.service.ArticleTagService;
import com.easyboke.entity.po.ArticleTag;
import com.easyboke.entity.query.ArticleTagQuery;
import com.easyboke.entity.vo.ResponseVO;
import java.util.List;
/**
 * @author 高98
 * @Description: 文章标签关联表的Controller类
 * @date: 2026/05/10
 */

@RestController
@RequestMapping("/articleTag")
public class ArticleTagController extends ABaseController{

	@Resource
	private ArticleTagService articleTagService;

	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(ArticleTagQuery query) {
		return getSuccessResponseVo(articleTagService.findListByPage(query));
	}
	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(ArticleTag bean){
		return getSuccessResponseVo(articleTagService.add(bean));
	 }
	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<ArticleTag> listBean){
		return getSuccessResponseVo(articleTagService.addBatch(listBean));
	 }
	/**
	 * 新增或者修改
	 */
	@RequestMapping("addOrUpdate")
	public ResponseVO addOrUpdate(ArticleTag bean){
		return getSuccessResponseVo(articleTagService.addOrUpdate(bean));
	 }
	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdate(@RequestBody List<ArticleTag> listBean){
		return getSuccessResponseVo(articleTagService.addOrUpdateBatch(listBean));
	 }
	/**
	 * 根据Id查询
	 */
	@RequestMapping("getArticleTagById")
	 public ResponseVO getArticleTagById(Integer id){
		return getSuccessResponseVo(this.articleTagService.getById(id));
	 }
	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateArticleTagById")
	 public ResponseVO updateArticleTagById(ArticleTag bean,Integer id){
		return getSuccessResponseVo(this.articleTagService.updateById(bean,id));
	 }
	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteArticleTagById")
	 public ResponseVO deleteArticleTagById(Integer id){
		return getSuccessResponseVo(this.articleTagService.deleteById(id));
	 }
	/**
	 * 根据ArticleIdAndTagId查询
	 */
	@RequestMapping("getArticleTagByArticleIdAndTagId")
	 public ResponseVO getArticleTagByArticleIdAndTagId(Integer articleId,Integer tagId){
		return getSuccessResponseVo(this.articleTagService.getByArticleIdAndTagId(articleId,tagId));
	 }
	/**
	 * 根据ArticleIdAndTagId更新
	 */
	@RequestMapping("updateArticleTagByArticleIdAndTagId")
	 public ResponseVO updateArticleTagByArticleIdAndTagId(ArticleTag bean,Integer articleId,Integer tagId){
		return getSuccessResponseVo(this.articleTagService.updateByArticleIdAndTagId(bean,articleId,tagId));
	 }
	/**
	 * 根据ArticleIdAndTagId删除
	 */
	@RequestMapping("deleteArticleTagByArticleIdAndTagId")
	 public ResponseVO deleteArticleTagByArticleIdAndTagId(Integer articleId,Integer tagId){
		return getSuccessResponseVo(this.articleTagService.deleteByArticleIdAndTagId(articleId,tagId));
	 }

}