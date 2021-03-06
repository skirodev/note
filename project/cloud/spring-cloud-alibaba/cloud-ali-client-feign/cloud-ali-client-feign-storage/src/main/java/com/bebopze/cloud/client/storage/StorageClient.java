package com.bebopze.cloud.client.storage;

import com.bebopze.cloud.framework.common.constant.ServiceConst;
import com.bebopze.cloud.framework.model.storage.param.StorageParam;
import com.bebopze.framework.common.model.response.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author bebopze
 * @date 2019/10/28
 */
// Dubbo Feign Rest
//@DubboTransported(protocol = "dubbo")
@FeignClient(name = ServiceConst.STORAGE_SERVICE, path = "/v1/storage")
public interface StorageClient {

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultBean<Long> save(@RequestBody StorageParam param);

    @GetMapping("/inventory/decr")
    ResultBean<Void> decrInventory(@RequestParam("productId") Long productId,
                                   @RequestParam("decrNum") int decrNum);

    @GetMapping("/inventory/incr")
    ResultBean<Void> incrInventory(@RequestParam("productId") Long productId,
                                   @RequestParam("incrNum") int incrNum);
}
