package org.dhp.examples.rpcdemo;

import org.dhp.core.spring.DhpRpcClientScanner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"org.dhp.examples.rpcdemo"})
@EnableScheduling
@DhpRpcClientScanner(basePackages={"org.dhp.examples.rpcdemo.comp"})
public class RpcServerDemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RpcServerDemoApplication.class).web(WebApplicationType.NONE).run(args);
    }

}
