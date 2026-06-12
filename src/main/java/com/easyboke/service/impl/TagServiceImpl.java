package com.easyboke.service.impl;

import com.easyboke.entity.query.TagQuery;
import com.easyboke.entity.query.SimplePage;
import com.easyboke.entity.po.Tag;
import com.easyboke.entity.vo.PaginationResultVO;
import com.easyboke.mappers.TagMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;
import com.easyboke.entity.enums.PageSize;
import com.easyboke.service.TagService;
/**
 * @author 高98
 * @Description: 文章标签表对应的ServiceImpl
 * @date: 2026/05/10
 */

@Service("tagService")
public class TagServiceImpl implements TagService{

	@Resource
	private TagMapper<Tag,TagQuery> tagMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Tag>findListByParam(TagQuery query){
		return this.tagMapper.selectList(query);
	 }
	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(TagQuery query){
		return this.tagMapper.selectCount(query);
	 }
	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVO<Tag> findListByPage(TagQuery query ){
		Integer count = this.findCountByParam(query); 
		Integer pageSize=query.getPageSize()==null? PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<Tag> list = this.findListByParam(query);
		PaginationResultVO<Tag> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	 }
	/**
	 * 新增
	 */
	@Override
	public Integer add(Tag bean){
		return this.tagMapper.insert(bean);
	 }
	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Tag> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.tagMapper.insertBatch(listBean);
	 }
	/**
	 * 新增或者修改
	 */
	@Override
	public Integer addOrUpdate(Tag tag){
		return this.tagMapper.insertOrUpdate(tag);
	 }
	/**
	 * 批量新增或修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Tag> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.tagMapper.insertOrUpdateBatch(listBean);
	 }
	/**
	 * 根据Id查询
	 */
	@Override
	 public Tag getById(Integer id){
		return this.tagMapper.selectById(id);
	 }
	/**
	 * 根据Id更新
	 */
	@Override
	 public Integer updateById(Tag bean , Integer id){
		return this.tagMapper.updateById(bean,id);
	 }
	/**
	 * 根据Id删除
	 */
	@Override
	 public Integer deleteById(Integer id){
		return this.tagMapper.deleteById(id);
	 }
	/**
	 * 根据TagName查询
	 */
	@Override
	 public Tag getByTagName(String tagName){
		return this.tagMapper.selectByTagName(tagName);
	 }
	/**
	 * 根据TagName更新
	 */
	@Override
	 public Integer updateByTagName(Tag bean , String tagName){
		return this.tagMapper.updateByTagName(bean,tagName);
	 }
	/**
	 * 根据TagName删除
	 */
	@Override
	 public Integer deleteByTagName(String tagName){
		return this.tagMapper.deleteByTagName(tagName);
	 }

}