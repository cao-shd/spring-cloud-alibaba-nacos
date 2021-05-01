package spring.cloud.alibaba.nacos.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import spring.cloud.alibaba.nacos.consumer.service.fallback.ProviderServiceFallback;

@FeignClient(value = "service-provider/provider", fallback = ProviderServiceFallback.class)
public interface IProviderService {

    @GetMapping
    String provider();

}
