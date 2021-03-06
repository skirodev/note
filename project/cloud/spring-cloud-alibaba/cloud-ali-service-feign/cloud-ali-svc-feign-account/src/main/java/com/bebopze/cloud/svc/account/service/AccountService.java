package com.bebopze.cloud.svc.account.service;

import com.bebopze.cloud.framework.model.account.param.AccountParam;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author bebopze
 * @date 2019/10/28
 */
public interface AccountService {

    void save(AccountParam param);

    void decrAmount(@NotNull(message = "userId不能为空") Long userId,
                    @NotNull(message = "decrAmount不能为空") BigDecimal decrAmount);

    void incrAmount(@NotNull(message = "userId不能为空") Long userId,
                    @NotNull(message = "incrAmount不能为空") BigDecimal incrAmount);
}
