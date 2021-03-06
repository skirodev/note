package com.bebopze.cloud.client.storage.dubbo;

import com.bebopze.cloud.framework.common.constant.ServiceConst;
import com.bebopze.cloud.framework.model.storage.param.StorageParam;
import com.bebopze.framework.common.model.response.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author bebopze
 * @date 2019/10/28
 */
@FeignClient(name = ServiceConst.STORAGE_SERVICE, path = "/v1/cloud-dubbo/storage")
public interface StorageClient {

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultBean<Long> save(@RequestBody StorageParam param);

    ResultBean<Long> minus(Long productId, int amountToSubtract);

    ResultBean<Long> plus(Long productId, int amountToAdd);
}
