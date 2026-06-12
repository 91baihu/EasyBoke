package com.easyboke.service.impl;

import com.easyboke.entity.query.VideoQuery;
import com.easyboke.entity.query.SimplePage;
import com.easyboke.entity.po.Video;
import com.easyboke.entity.po.VideoLike;
import com.easyboke.entity.po.VideoAccessLog;
import com.easyboke.entity.vo.PaginationResultVO;
import com.easyboke.mappers.VideoMapper;
import com.easyboke.mappers.VideoLikeMapper;
import com.easyboke.mappers.VideoAccessLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.easyboke.entity.enums.PageSize;
import com.easyboke.service.VideoService;

@Service("videoService")
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoMapper<Video, VideoQuery> videoMapper;

    @Resource
    private VideoLikeMapper<VideoLike, Object> videoLikeMapper;

    @Resource
    private VideoAccessLogMapper<VideoAccessLog, Object> videoAccessLogMapper;

    @Override
    public List<Video> findListByParam(VideoQuery query) {
        return this.videoMapper.selectList(query);
    }

    @Override
    public Integer findCountByParam(VideoQuery query) {
        return this.videoMapper.selectCount(query);
    }

    @Override
    public PaginationResultVO<Video> findListByPage(VideoQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<Video> list = this.findListByParam(query);
        PaginationResultVO<Video> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(),
                page.getPageTotal(), list);
        return result;
    }

    @Override
    public Integer add(Video bean) {
        return this.videoMapper.insert(bean);
    }

    @Override
    public Integer addBatch(List<Video> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.videoMapper.insertBatch(listBean);
    }

    @Override
    public Integer addOrUpdate(Video video) {
        return this.videoMapper.insertOrUpdate(video);
    }

    @Override
    public Integer addOrUpdateBatch(List<Video> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.videoMapper.insertOrUpdateBatch(listBean);
    }

    @Override
    public Video getById(Integer id) {
        return this.videoMapper.selectById(id);
    }

    @Override
    public Integer updateById(Video bean, Integer id) {
        return this.videoMapper.updateById(bean, id);
    }

    @Override
    public Integer deleteById(Integer id) {
        return this.videoMapper.deleteById(id);
    }

    @Override
    public Integer publishVideo(Video video) {
        video.setStatus(1);
        video.setPublishTime(new java.util.Date());
        video.setCreateTime(new java.util.Date());
        video.setUpdateTime(new java.util.Date());
        if (video.getViewCount() == null) video.setViewCount(0);
        if (video.getMaleViewCount() == null) video.setMaleViewCount(0);
        if (video.getFemaleViewCount() == null) video.setFemaleViewCount(0);
        if (video.getUnknownViewCount() == null) video.setUnknownViewCount(0);
        if (video.getAgeUnder18() == null) video.setAgeUnder18(0);
        if (video.getAge1825() == null) video.setAge1825(0);
        if (video.getAge2635() == null) video.setAge2635(0);
        if (video.getAge3645() == null) video.setAge3645(0);
        if (video.getAgeAbove45() == null) video.setAgeAbove45(0);
        if (video.getLikeCount() == null) video.setLikeCount(0);
        if (video.getCommentCount() == null) video.setCommentCount(0);
        return this.videoMapper.insert(video);
    }

    @Override
    public Integer updateVideo(Video video) {
        video.setUpdateTime(new java.util.Date());
        return this.videoMapper.updateById(video, video.getId());
    }

    @Override
    public Video getVideoDetail(Integer id) {
        return getVideoDetail(id, null, 0, 0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Video getVideoDetail(Integer id, Integer userId, Integer gender, Integer age) {
        Video video = this.videoMapper.selectById(id);
        if (video != null && video.getStatus() == 1) {
            this.videoMapper.incrementViewCount(id);
            if (gender != null && gender == 1) {
                this.videoMapper.incrementMaleViewCount(id);
            } else if (gender != null && gender == 2) {
                this.videoMapper.incrementFemaleViewCount(id);
            } else {
                this.videoMapper.incrementUnknownViewCount(id);
            }
            if (age != null && age > 0) {
                if (age < 18) this.videoMapper.incrementAgeUnder18(id);
                else if (age <= 25) this.videoMapper.incrementAge1825(id);
                else if (age <= 35) this.videoMapper.incrementAge2635(id);
                else if (age <= 45) this.videoMapper.incrementAge3645(id);
                else this.videoMapper.incrementAgeAbove45(id);
            }
            video.setViewCount(video.getViewCount() + 1);
        }
        return video;
    }

    @Override
    public List<Video> getLatestVideos(Integer limit, Integer status) {
        VideoQuery query = new VideoQuery();
        query.setStatus(status);
        query.setOrderBy("publish_time desc");
        query.setPageNo(1);
        query.setPageSize(limit);
        return this.videoMapper.selectList(query);
    }

    @Override
    public List<Video> getHotVideos(Integer limit, Integer status) {
        VideoQuery query = new VideoQuery();
        query.setStatus(status);
        query.setOrderBy("view_count desc");
        query.setPageNo(1);
        query.setPageSize(limit);
        return this.videoMapper.selectList(query);
    }

    @Override
    public List<Video> getVideosByCategoryId(Integer categoryId, Integer status) {
        VideoQuery query = new VideoQuery();
        query.setCategoryId(categoryId);
        query.setStatus(status);
        query.setOrderBy("publish_time desc");
        return this.videoMapper.selectList(query);
    }

    // ==================== 点赞功能（DB持久化） ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean likeVideo(Integer videoId, Integer userId) throws com.easyboke.exception.BusinessException {
        if (userId == null || userId <= 0) {
            throw new com.easyboke.exception.BusinessException("请先登录");
        }
        Video video = this.videoMapper.selectById(videoId);
        if (video == null) {
            throw new com.easyboke.exception.BusinessException("视频不存在");
        }
        if (video.getStatus() != 1) {
            throw new com.easyboke.exception.BusinessException("视频未发布");
        }
        // 检查DB中是否已点赞
        VideoLike existing = this.videoLikeMapper.selectByVideoIdAndUserId(videoId, userId);
        if (existing != null) {
            throw new com.easyboke.exception.BusinessException("已经点赞过");
        }
        // 插入点赞记录
        VideoLike like = new VideoLike();
        like.setVideoId(videoId);
        like.setUserId(userId);
        like.setCreateTime(new java.util.Date());
        this.videoLikeMapper.insert(like);
        // 增加视频点赞数
        this.videoMapper.incrementLikeCount(videoId);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean unlikeVideo(Integer videoId, Integer userId) throws com.easyboke.exception.BusinessException {
        if (userId == null || userId <= 0) {
            throw new com.easyboke.exception.BusinessException("请先登录");
        }
        Video video = this.videoMapper.selectById(videoId);
        if (video == null) {
            throw new com.easyboke.exception.BusinessException("视频不存在");
        }
        VideoLike existing = this.videoLikeMapper.selectByVideoIdAndUserId(videoId, userId);
        if (existing == null) {
            throw new com.easyboke.exception.BusinessException("尚未点赞");
        }
        this.videoLikeMapper.deleteByVideoIdAndUserId(videoId, userId);
        this.videoMapper.decrementLikeCount(videoId);
        return true;
    }

    @Override
    public Boolean isVideoLiked(Integer videoId, Integer userId) {
        if (userId == null || userId <= 0) {
            return false;
        }
        return this.videoLikeMapper.selectByVideoIdAndUserId(videoId, userId) != null;
    }

    // ==================== 访问记录（含IP防刷） ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean recordView(Integer videoId, Integer userId, String ipAddress, String userAgent, String referer) {
        // 防刷：同一IP同一视频1小时内只计1次
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.HOUR_OF_DAY, -1);
        String sinceTime = sdf.format(cal.getTime());

        Integer recentCount = this.videoAccessLogMapper.countByVideoIdAndIpInTime(videoId, ipAddress, sinceTime);
        if (recentCount != null && recentCount > 0) {
            return false; // 1小时内已访问过，不计
        }

        VideoAccessLog log = new VideoAccessLog();
        log.setVideoId(videoId);
        log.setUserId(userId);
        log.setIpAddress(ipAddress);
        log.setUserAgent(userAgent);
        log.setReferer(referer);
        log.setAccessTime(new java.util.Date());
        this.videoAccessLogMapper.insert(log);
        return true;
    }

    // ==================== 视频时长自动获取 ====================

    @Override
    public Integer fetchVideoDuration(String videoUrl) {
        if (videoUrl == null || videoUrl.trim().isEmpty()) {
            return null;
        }
        try {
            // B站视频：通过 oembed API 获取时长
            if (videoUrl.contains("bilibili.com")) {
                String bvMatch = extractBvId(videoUrl);
                if (bvMatch != null) {
                    return fetchBilibiliDuration(bvMatch);
                }
            }
            // YouTube视频：通过 oembed API 获取时长
            if (videoUrl.contains("youtube.com") || videoUrl.contains("youtu.be")) {
                return fetchYoutubeDuration(videoUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从B站URL提取BV号
     */
    private String extractBvId(String url) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("BV[a-zA-Z0-9]{10}");
        java.util.regex.Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /**
     * 通过B站API获取视频时长
     */
    private Integer fetchBilibiliDuration(String bvId) {
        try {
            String apiUrl = "https://api.bilibili.com/x/web-interface/view?bvid=" + bvId;
            java.net.URL url = new java.net.URL(apiUrl);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
            conn.setRequestProperty("Referer", "https://www.bilibili.com/");

            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            conn.disconnect();

            com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSON.parseObject(response.toString());
            if (json.getInteger("code") == 0) {
                com.alibaba.fastjson.JSONObject data = json.getJSONObject("data");
                return data.getInteger("duration");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过YouTube oEmbed API获取视频时长（返回null，因oEmbed不直接提供duration）
     * YouTube需要Data API key，这里仅做基础支持
     */
    private Integer fetchYoutubeDuration(String videoUrl) {
        // YouTube oEmbed不返回时长，需要YouTube Data API v3
        // 此处返回null，前端可提示用户手动填写
        return null;
    }

    // ==================== 访问统计 ====================

    @Override
    public java.util.Map<String, Object> getAccessStatsSummary() {
        return this.videoAccessLogMapper.selectStatsSummary();
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getDailyViewsByVideo(Integer videoId, String startDate,
            String endDate) {
        return this.videoAccessLogMapper.selectDailyViewsByVideo(videoId, startDate, endDate);
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getTotalViewsByVideo() {
        return this.videoAccessLogMapper.selectTotalViewsByVideo();
    }

}
