package com.easyboke.service.impl;

import com.easyboke.entity.query.CommentQuery;
import com.easyboke.entity.query.SimplePage;
import com.easyboke.entity.po.Comment;
import com.easyboke.entity.vo.PaginationResultVO;
import com.easyboke.mappers.CommentMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;
import com.easyboke.entity.enums.PageSize;
import com.easyboke.service.CommentService;
/**
 * @author 高98
 * @Description: 评论表对应的ServiceImpl
 * @date: 2026/05/10
 */

@Service("commentService")
public class CommentServiceImpl implements CommentService{

	@Resource
	private CommentMapper<Comment,CommentQuery> commentMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Comment>findListByParam(CommentQuery query){
		return this.commentMapper.selectList(query);
	 }
	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(CommentQuery query){
		return this.commentMapper.selectCount(query);
	 }
	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVO<Comment> findListByPage(CommentQuery query ){
		Integer count = this.findCountByParam(query);
		Integer pageSize=query.getPageSize()==null? PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<Comment> list = this.findListByParam(query);
		PaginationResultVO<Comment> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	 }
	/**
	 * 新增
	 */
	@Override
	public Integer add(Comment bean){
		return this.commentMapper.insert(bean);
	 }
	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Comment> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.commentMapper.insertBatch(listBean);
	 }
	/**
	 * 新增或者修改
	 */
	@Override
	public Integer addOrUpdate(Comment comment){
		return this.commentMapper.insertOrUpdate(comment);
	 }
	/**
	 * 批量新增或修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Comment> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.commentMapper.insertOrUpdateBatch(listBean);
	 }
	/**
	 * 根据Id查询
	 */
	@Override
	 public Comment getById(Integer id){
		return this.commentMapper.selectById(id);
	 }
	/**
	 * 根据Id更新
	 */
	@Override
	 public Integer updateById(Comment bean , Integer id){
		return this.commentMapper.updateById(bean,id);
	 }
	/**
	 * 根据Id删除
	 */
	@Override
	 public Integer deleteById(Integer id){
		return this.commentMapper.deleteById(id);
	 }
	/**
	 * 发表评论
	 */
	@Override
	public Integer publishComment(Comment comment) {
		comment.setStatus(1);
		comment.setParentId(0);
		comment.setLikeCount(0);
		java.util.Date now = new java.util.Date();
		comment.setCreateTime(now);
		comment.setUpdateTime(now);
		return this.commentMapper.insert(comment);
	}

	/**
	 * 回复评论
	 */
	@Override
	public Integer replyComment(Comment comment) {
		comment.setStatus(1);
		comment.setLikeCount(0);
		java.util.Date now = new java.util.Date();
		comment.setCreateTime(now);
		comment.setUpdateTime(now);
		return this.commentMapper.insert(comment);
	}

	/**
	 * 根据文章ID查询评论列表（含用户头像）
	 */
	@Override
	public List<Comment> getCommentsByArticleId(Integer articleId) {
		return this.commentMapper.selectByArticleIdWithAvatar(articleId, 1);
	}

	/**
	 * 根据视频ID查询评论列表（含用户头像）
	 */
	@Override
	public List<Comment> getCommentsByVideoId(Integer videoId) {
		return this.commentMapper.selectByVideoIdWithAvatar(videoId, 1);
	}

	/**
	 * 审核评论
	 */
	@Override
	public Integer auditComment(Integer id, Integer status) {
		Comment comment = new Comment();
		comment.setStatus(status);
		comment.setUpdateTime(new java.util.Date());
		return this.commentMapper.updateById(comment, id);
	}
}