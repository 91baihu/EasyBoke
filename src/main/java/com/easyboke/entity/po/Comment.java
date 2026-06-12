package com.easyboke.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easyboke.entity.enums.DateTimePatternEnum;
import com.easyboke.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author 高98
 * @Description: 评论表
 * @date: 2026/05/10
 */
public class Comment implements Serializable{

	/**
	 * 评论ID
	 */
	private Integer id;

	/**
	 * 评论内容
	 */
	private String content;

	/**
	 * 评论者ID，0表示游客
	 */
	private Integer userId;

	/**
	 * 评论者用户名（游客填写）
	 */
	private String username;

	/**
	 * 关联文章ID
	 */
	private Integer articleId;

	/**
	 * 关联视频ID
	 */
	private Integer videoId;

	/**
	 * 父评论ID，0表示一级评论
	 */
	private Integer parentId;

	/**
	 * 回复的评论ID
	 */
	private Integer replyCommentId;

	/**
	 * 状态：0-待审核，1-已通过，2-已删除
	 */
	@JsonIgnore
	private Integer status;

	/**
	 * 点赞数
	 */
	private Integer likeCount;

	/**
	 * 评论IP
	 */
	private String ipAddress;

	/**
	 * 评论时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * 用户头像URL（非数据库字段，仅用于返回）
	 */
	private String userAvatar;

	/**
	 * 被回复用户名（非数据库字段，仅用于返回）
	 */
	private String replyUsername;

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getReplyUsername() {
		return replyUsername;
	}

	public void setReplyUsername(String replyUsername) {
		this.replyUsername = replyUsername;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getArticleId() {
		return this.articleId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public Integer getVideoId() {
		return this.videoId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setReplyCommentId(Integer replyCommentId) {
		this.replyCommentId = replyCommentId;
	}

	public Integer getReplyCommentId() {
		return this.replyCommentId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getLikeCount() {
		return this.likeCount;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	@Override
	public String toString() {
		return "评论ID:" + (id == null ? "空" : id) + ",评论内容:" + (content == null ? "空" : content) + ",评论者ID，0表示游客:" + (userId == null ? "空" : userId) + ",评论者用户名（游客填写）:" + (username == null ? "空" : username) + ",关联文章ID:" + (articleId == null ? "空" : articleId) + ",关联视频ID:" + (videoId == null ? "空" : videoId) + ",父评论ID，0表示一级评论:" + (parentId == null ? "空" : parentId) + ",回复的评论ID:" + (replyCommentId == null ? "空" : replyCommentId) + ",状态：0-待审核，1-已通过，2-已删除:" + (status == null ? "空" : status) + ",点赞数:" + (likeCount == null ? "空" : likeCount) + ",评论IP:" + (ipAddress == null ? "空" : ipAddress) + ",评论时间:" + (createTime == null ? "空" : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + ",更新时间:" + (updateTime == null ? "空" : DateUtils.format(updateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}

}