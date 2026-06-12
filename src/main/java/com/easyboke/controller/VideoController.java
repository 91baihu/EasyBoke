package com.easyboke.controller;

import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import com.easyboke.service.VideoService;
import com.easyboke.entity.po.Video;
import com.easyboke.entity.query.VideoQuery;
import com.easyboke.entity.vo.ResponseVO;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description: 视频表的Controller类
 */
@RestController
@RequestMapping("/video")
public class VideoController extends ABaseController {

    @Resource
    private VideoService videoService;

    /**
     * 根据条件分页查询
     */
    @RequestMapping("loadDataList")
    public ResponseVO loadDataList(VideoQuery query) {
        return getSuccessResponseVo(videoService.findListByPage(query));
    }

    /**
     * 新增视频
     */
    @RequestMapping("addVideo")
    public ResponseVO addVideo(@RequestBody Video bean) {
        return getSuccessResponseVo(videoService.add(bean));
    }

    /**
     * 批量新增
     */
    @RequestMapping("addBatch")
    public ResponseVO addBatch(@RequestBody List<Video> listBean) {
        return getSuccessResponseVo(videoService.addBatch(listBean));
    }

    /**
     * 新增或者修改
     */
    @RequestMapping("addOrUpdate")
    public ResponseVO addOrUpdate(@RequestBody Video bean) {
        return getSuccessResponseVo(videoService.addOrUpdate(bean));
    }

    /**
     * 批量新增或修改
     */
    @RequestMapping("addOrUpdateBatch")
    public ResponseVO addOrUpdateBatch(@RequestBody List<Video> listBean) {
        return getSuccessResponseVo(videoService.addOrUpdateBatch(listBean));
    }

    /**
     * 根据Id查询
     */
    @RequestMapping("getVideoById")
    public ResponseVO getVideoById(Integer id) {
        return getSuccessResponseVo(this.videoService.getById(id));
    }

    /**
     * 根据Id更新视频
     */
    @RequestMapping("updateVideo")
    public ResponseVO updateVideo(@RequestBody Video bean) {
        return getSuccessResponseVo(this.videoService.updateById(bean, bean.getId()));
    }

    /**
     * 根据Id删除视频
     */
    @RequestMapping("deleteVideo")
    public ResponseVO deleteVideo(Integer id) {
        return getSuccessResponseVo(this.videoService.deleteById(id));
    }

    /**
     * 上传视频封面
     */
    @RequestMapping("uploadCover")
    public ResponseVO uploadCover(@RequestParam("file") MultipartFile file,
            @RequestParam(required = false) Integer videoId) {
        if (file == null || file.isEmpty()) {
            return getErrorResponseVo("请选择封面图片");
        }
        String originalFilename = file.getOriginalFilename();
        String suffix = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        }
        if (!".jpg".equals(suffix) && !".jpeg".equals(suffix) && !".png".equals(suffix)) {
            return getErrorResponseVo("仅支持jpg/png格式的图片");
        }
        if (file.getSize() > 5 * 1024 * 1024) {
            return getErrorResponseVo("文件大小不能超过5MB");
        }
        try {
            String projectDir = System.getProperty("user.dir");
            File dir = new File(projectDir, "uploads/video_cover/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String newFileName = "cover_" + UUID.randomUUID().toString() + suffix;
            File dest = new File(dir, newFileName);
            file.transferTo(dest);
            String coverUrl = "/uploads/video_cover/" + newFileName;
            if (videoId != null) {
                Video updateVideo = new Video();
                updateVideo.setCoverImage(coverUrl);
                videoService.updateById(updateVideo, videoId);
            }
            return getSuccessResponseVo(coverUrl);
        } catch (IOException e) {
            return getErrorResponseVo("上传失败: " + e.getMessage());
        }
    }

    // ==================== 点赞功能（DB持久化） ====================

    /**
     * 点赞视频
     */
    @RequestMapping("like")
    public ResponseVO likeVideo(@RequestBody Map<String, Integer> params, HttpSession session) {
        try {
            com.easyboke.entity.po.User user = (com.easyboke.entity.po.User) session
                    .getAttribute(com.easyboke.common.Constants.SESSION_KEY_USER);
            if (user == null) {
                return getErrorResponseVo("请先登录");
            }
            Integer videoId = params.get("videoId");
            if (videoId == null) {
                return getErrorResponseVo("视频ID不能为空");
            }
            videoService.likeVideo(videoId, user.getId());
            return getSuccessResponseVo(true);
        } catch (com.easyboke.exception.BusinessException e) {
            return getErrorResponseVo(e.getMessage());
        }
    }

    /**
     * 取消点赞
     */
    @RequestMapping("unlike")
    public ResponseVO unlikeVideo(@RequestBody Map<String, Integer> params, HttpSession session) {
        try {
            com.easyboke.entity.po.User user = (com.easyboke.entity.po.User) session
                    .getAttribute(com.easyboke.common.Constants.SESSION_KEY_USER);
            if (user == null) {
                return getErrorResponseVo("请先登录");
            }
            Integer videoId = params.get("videoId");
            if (videoId == null) {
                return getErrorResponseVo("视频ID不能为空");
            }
            videoService.unlikeVideo(videoId, user.getId());
            return getSuccessResponseVo(true);
        } catch (com.easyboke.exception.BusinessException e) {
            return getErrorResponseVo(e.getMessage());
        }
    }

    /**
     * 检查是否已点赞
     */
    @RequestMapping("isLiked")
    public ResponseVO isLiked(@RequestParam Integer videoId, HttpSession session) {
        com.easyboke.entity.po.User user = (com.easyboke.entity.po.User) session
                .getAttribute(com.easyboke.common.Constants.SESSION_KEY_USER);
        if (user == null) {
            return getSuccessResponseVo(false);
        }
        return getSuccessResponseVo(videoService.isVideoLiked(videoId, user.getId()));
    }

    // ==================== 访问记录（含IP防刷） ====================

    /**
     * 记录视频访问（含IP防刷：同一IP同一视频1小时只计1次）
     */
    @RequestMapping("view/{videoId}")
    public ResponseVO recordView(@PathVariable Integer videoId, HttpSession session, HttpServletRequest request) {
        try {
            com.easyboke.entity.po.User user = (com.easyboke.entity.po.User) session
                    .getAttribute(com.easyboke.common.Constants.SESSION_KEY_USER);
            Integer userId = (user != null) ? user.getId() : null;

            String ipAddress = getClientIp(request);
            String userAgent = request.getHeader("User-Agent");
            String referer = request.getHeader("Referer");

            boolean counted = videoService.recordView(videoId, userId, ipAddress, userAgent, referer);
            java.util.Map<String, Object> resultMap = new java.util.HashMap<>();
            resultMap.put("counted", counted);
            return getSuccessResponseVo(resultMap);
        } catch (Exception e) {
            return getErrorResponseVo(e.getMessage());
        }
    }

    /**
     * 获取客户端真实IP
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 多级代理取第一个IP
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }

    // ==================== 视频时长自动获取 ====================

    /**
     * 根据视频链接自动获取时长（支持B站等平台）
     */
    @RequestMapping("fetchDuration")
    public ResponseVO fetchDuration(@RequestParam String videoUrl) {
        try {
            Integer duration = videoService.fetchVideoDuration(videoUrl);
            java.util.Map<String, Object> resultMap = new java.util.HashMap<>();
            if (duration != null) {
                resultMap.put("duration", duration);
                return getSuccessResponseVo(resultMap);
            }
            resultMap.put("duration", 0);
            return getSuccessResponseVo(resultMap);
        } catch (Exception e) {
            return getErrorResponseVo("获取时长失败: " + e.getMessage());
        }
    }

    // ==================== 访问统计 ====================

    /**
     * 访问统计摘要
     */
    @RequestMapping("statsSummary")
    public ResponseVO statsSummary() {
        return getSuccessResponseVo(videoService.getAccessStatsSummary());
    }

    /**
     * 按视频获取日访问统计
     */
    @RequestMapping("dailyViews")
    public ResponseVO dailyViews(@RequestParam Integer videoId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        return getSuccessResponseVo(videoService.getDailyViewsByVideo(videoId, startDate, endDate));
    }

    /**
     * 视频总访问排行
     */
    @RequestMapping("totalViewsRank")
    public ResponseVO totalViewsRank() {
        return getSuccessResponseVo(videoService.getTotalViewsByVideo());
    }
}
