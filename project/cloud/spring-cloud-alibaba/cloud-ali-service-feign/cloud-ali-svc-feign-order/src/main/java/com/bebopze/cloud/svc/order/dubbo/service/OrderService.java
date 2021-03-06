package com.bebopze.cloud.svc.order.dubbo.service;

import com.bebopze.cloud.framework.model.order.entity.OrderDO;
import com.bebopze.cloud.framework.model.order.param.OrderParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author bebopze
 * @date 2019/10/28
 */
public interface OrderService {

    /**
     * 创建/修改
     *
     * @param param
     * @return
     */
    Long save(@Valid OrderParam param);

    OrderDO detail(@NotNull Long orderId);
}