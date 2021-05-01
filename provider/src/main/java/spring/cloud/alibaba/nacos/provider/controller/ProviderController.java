package spring.cloud.alibaba.nacos.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/provider")
public class ProviderController {

    @Value("${server.port}")
    private int port;

    @Value("${tag}")
    private String tag;

    @GetMapping
    public String provide() {
        return String.format("Hello Provider! port: %s, tag：%s ", port, tag);
    }

}
