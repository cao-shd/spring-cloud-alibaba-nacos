package spring.cloud.alibaba.nacos.consumer.service.fallback;

import org.springframework.stereotype.Service;
import spring.cloud.alibaba.nacos.consumer.service.IProviderService;

@Service
public class ProviderServiceFallback implements IProviderService {
    @Override
    public String provider() {
        return "服务不可能崩, 你找找自己的原因吧!";
    }
}
