package cn.margin.jz.module.statistics.dal.mysql.trade;

import cn.margin.jz.framework.mybatis.core.mapper.BaseMapperX;
import cn.margin.jz.module.statistics.dal.dataobject.trade.TradeStatisticsDO;
import cn.margin.jz.module.statistics.service.trade.bo.AfterSaleSummaryRespBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * 售后订单的统计 Mapper
 *
 * @author owen
 */
@Mapper
public interface AfterSaleStatisticsMapper extends BaseMapperX<TradeStatisticsDO> {

    // TODO 芋艿：已 review
    AfterSaleSummaryRespBO selectSummaryByRefundTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                                            @Param("endTime") LocalDateTime endTime);

    // TODO 芋艿：已经 review
    Long selectCountByStatus(@Param("status") Integer status);

}
