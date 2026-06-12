package com.easyboke.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface VideoAccessLogMapper<T, P> extends BaseMapper {
    T selectById(@Param("id") Integer id);
    Integer updateById(@Param("bean") T t, @Param("id") Integer id);
    Integer deleteById(@Param("id") Integer id);

    /** 检查同一IP在指定时间内是否已访问过该视频 */
    Integer countByVideoIdAndIpInTime(@Param("videoId") Integer videoId,
                                       @Param("ipAddress") String ipAddress,
                                       @Param("sinceTime") String sinceTime);

    /** 按视频统计总访问量 */
    List<Map<String, Object>> selectTotalViewsByVideo();

    /** 按视频统计日访问量 */
    List<Map<String, Object>> selectDailyViewsByVideo(@Param("videoId") Integer videoId,
                                                       @Param("startDate") String startDate,
                                                       @Param("endDate") String endDate);

    /** 统计视频访问用户分布 */
    List<Map<String, Object>> selectUserDistributionByVideo(@Param("videoId") Integer videoId);

    /** 总体统计摘要 */
    Map<String, Object> selectStatsSummary();
}
