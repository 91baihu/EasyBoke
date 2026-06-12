package com.easyboke.service;

import com.easyboke.entity.query.CommentQuery;
import com.easyboke.entity.po.Comment;
import com.easyboke.entity.vo.PaginationResultVO;
import java.util.List;
/**
 * @author 高98
 * @Description: 评论表对应的Service
 * @date: 2026/05/10
 */

public interface CommentService{

	/**
	 * 根据条件查询列表
	 */
	List<Comment>findListByParam(CommentQuery query);

	/**
	 * 根据条件查询数量
	 */
    Integer findCountByParam(CommentQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Comment> findListByPage(CommentQuery query );

	/**
	 * 新增
	 */
	Integer add(Comment bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<Comment> listBean);
	/**
	 * 新增或修改
	 */
	Integer addOrUpdate(Comment bean);
	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<Comment> listBean);
	/**
	 * 根据Id查询
	 */
	 Comment getById(Integer id);

	/**
	 * 根据Id查询
	 */
	 Integer updateById(Comment bean , Integer id);

	/**
	 * 根据Id删除
	 */
	 Integer deleteById(Integer id);
	/**
	 * 发表评论
	 */
	Integer publishComment(Comment comment);

	/**
	 * 回复评论
	 */
	Integer replyComment(Comment comment);

	/**
	 * 根据文章ID查询评论列表
	 */
	List<Comment> getCommentsByArticleId(Integer articleId);

	/**
	 * 根据视频ID查询评论列表
	 */
	List<Comment> getCommentsByVideoId(Integer videoId);

	/**
	 * 审核评论
	 */
	Integer auditComment(Integer id, Integer status);

}