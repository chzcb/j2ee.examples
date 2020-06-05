package org.dhp.examples.rpcdemo;

import org.dhp.core.spring.DhpProperties;
import org.dhp.core.spring.EnableDhpRpcClient;
import org.dhp.core.spring.EnableDhpRpcServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"org.dhp.examples.rpcdemo"})
@EnableConfigurationProperties(DhpProperties.class)
@EnableDhpRpcClient
@EnableDhpRpcServer
@EnableScheduling
public class RpcServerDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RpcServerDemoApplication.class).web(WebApplicationType.NONE).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Thread.sleep(1000);
//        System.exit(0);
    }
}
