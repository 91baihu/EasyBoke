package com.easyboke.service;

import com.easyboke.entity.query.VideoQuery;
import com.easyboke.entity.po.Video;
import com.easyboke.entity.vo.PaginationResultVO;
import java.util.List;
/**
 * @author 高98
 * @Description: 视频表对应的Service
 * @date: 2026/05/10
 */

public interface VideoService{

	/**
	 * 根据条件查询列表
	 */
	List<Video>findListByParam(VideoQuery query);

	/**
	 * 根据条件查询数量
	 */
    Integer findCountByParam(VideoQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Video> findListByPage(VideoQuery query );

	/**
	 * 新增
	 */
	Integer add(Video bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<Video> listBean);
	/**
	 * 新增或修改
	 */
	Integer addOrUpdate(Video bean);
	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<Video> listBean);
	/**
	 * 根据Id查询
	 */
	 Video getById(Integer id);

	/**
	 * 根据Id查询
	 */
	 Integer updateById(Video bean , Integer id);

	// ... existing code ...
	/**
	 * 根据Id删除
	 */
	Integer deleteById(Integer id);

	/**
	 * 发布视频
	 */
	Integer publishVideo(Video video);

	/**
	 * 更新视频
	 */
	Integer updateVideo(Video video);

	/**
	 * 获取视频详情（增加播放量）
	 */
	Video getVideoDetail(Integer id);

	/**
	 * 获取最新视频
	 */
	List<Video> getLatestVideos(Integer limit, Integer status);

	/**
	 * 获取热门视频
	 */
	List<Video> getHotVideos(Integer limit, Integer status);

	/**
	 * 根据分类ID查询视频
	 */
	List<Video> getVideosByCategoryId(Integer categoryId, Integer status);


	// ... existing code ...

	Video getVideoDetail(Integer id, Integer userId, Integer gender, Integer age);

	Boolean likeVideo(Integer videoId, Integer userId) throws com.easyboke.exception.BusinessException;

	Boolean unlikeVideo(Integer videoId, Integer userId) throws com.easyboke.exception.BusinessException;

	Boolean isVideoLiked(Integer videoId, Integer userId);

	/**
	 * 记录视频访问（含防刷机制）
	 */
	Boolean recordView(Integer videoId, Integer userId, String ipAddress, String userAgent, String referer);

	/**
	 * 根据视频链接自动获取时长
	 */
	Integer fetchVideoDuration(String videoUrl);

	/**
	 * 获取视频访问统计摘要
	 */
	java.util.Map<String, Object> getAccessStatsSummary();

	/**
	 * 获取指定视频的日访问统计
	 */
	java.util.List<java.util.Map<String, Object>> getDailyViewsByVideo(Integer videoId, String startDate, String endDate);

	/**
	 * 获取视频总访问排行
	 */
	java.util.List<java.util.Map<String, Object>> getTotalViewsByVideo();

}