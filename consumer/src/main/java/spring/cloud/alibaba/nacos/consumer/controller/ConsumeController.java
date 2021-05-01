package spring.cloud.alibaba.nacos.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import spring.cloud.alibaba.nacos.consumer.service.IProviderService;

@RestController
@RequestMapping("/consumer")
public class ConsumeController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IProviderService echoService;

    // 访问地址 http://localhost:11000/consumer/template/provider
    @GetMapping("/template/provider")
    public String templateCallProvider() {
        return restTemplate.getForObject("http://service-provider/provider", String.class);
    }

    // 访问地址 http://localhost:11000/consumer/feign/provider
    @GetMapping("/feign/provider")
    public String feignCallProvider() {
        return echoService.provider();
    }

}

