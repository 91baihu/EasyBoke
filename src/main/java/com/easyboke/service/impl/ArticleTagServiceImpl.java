package com.easyboke.service.impl;

import com.easyboke.entity.query.ArticleTagQuery;
import com.easyboke.entity.query.SimplePage;
import com.easyboke.entity.po.ArticleTag;
import com.easyboke.entity.vo.PaginationResultVO;
import com.easyboke.mappers.ArticleTagMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;
import com.easyboke.entity.enums.PageSize;
import com.easyboke.service.ArticleTagService;
/**
 * @author 高98
 * @Description: 文章标签关联表对应的ServiceImpl
 * @date: 2026/05/10
 */

@Service("articleTagService")
public class ArticleTagServiceImpl implements ArticleTagService{

	@Resource
	private ArticleTagMapper<ArticleTag,ArticleTagQuery> articleTagMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<ArticleTag>findListByParam(ArticleTagQuery query){
		return this.articleTagMapper.selectList(query);
	 }
	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(ArticleTagQuery query){
		return this.articleTagMapper.selectCount(query);
	 }
	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVO<ArticleTag> findListByPage(ArticleTagQuery query ){
		Integer count = this.findCountByParam(query); 
		Integer pageSize=query.getPageSize()==null? PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<ArticleTag> list = this.findListByParam(query);
		PaginationResultVO<ArticleTag> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	 }
	/**
	 * 新增
	 */
	@Override
	public Integer add(ArticleTag bean){
		return this.articleTagMapper.insert(bean);
	 }
	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<ArticleTag> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.articleTagMapper.insertBatch(listBean);
	 }
	/**
	 * 新增或者修改
	 */
	@Override
	public Integer addOrUpdate(ArticleTag articleTag){
		return this.articleTagMapper.insertOrUpdate(articleTag);
	 }
	/**
	 * 批量新增或修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<ArticleTag> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.articleTagMapper.insertOrUpdateBatch(listBean);
	 }
	/**
	 * 根据Id查询
	 */
	@Override
	 public ArticleTag getById(Integer id){
		return this.articleTagMapper.selectById(id);
	 }
	/**
	 * 根据Id更新
	 */
	@Override
	 public Integer updateById(ArticleTag bean , Integer id){
		return this.articleTagMapper.updateById(bean,id);
	 }
	/**
	 * 根据Id删除
	 */
	@Override
	 public Integer deleteById(Integer id){
		return this.articleTagMapper.deleteById(id);
	 }
	/**
	 * 根据ArticleIdAndTagId查询
	 */
	@Override
	 public ArticleTag getByArticleIdAndTagId(Integer articleId,Integer tagId){
		return this.articleTagMapper.selectByArticleIdAndTagId(articleId,tagId);
	 }
	/**
	 * 根据ArticleIdAndTagId更新
	 */
	@Override
	 public Integer updateByArticleIdAndTagId(ArticleTag bean , Integer articleId,Integer tagId){
		return this.articleTagMapper.updateByArticleIdAndTagId(bean,articleId,tagId);
	 }
	/**
	 * 根据ArticleIdAndTagId删除
	 */
	@Override
	 public Integer deleteByArticleIdAndTagId(Integer articleId,Integer tagId){
		return this.articleTagMapper.deleteByArticleIdAndTagId(articleId,tagId);
	 }

}