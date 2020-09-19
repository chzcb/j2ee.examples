package org.dhp.examples.rpcdemo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"org.dhp.examples.rpcdemo"})
@EnableScheduling
public class RpcServerDemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RpcServerDemoApplication.class).web(WebApplicationType.NONE).run(args);
    }

}
