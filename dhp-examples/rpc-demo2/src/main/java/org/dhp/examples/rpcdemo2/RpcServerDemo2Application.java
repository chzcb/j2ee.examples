package org.dhp.examples.rpcdemo2;

import org.dhp.core.spring.DhpProperties;
import org.dhp.core.spring.EnableDhpRpcServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"org.dhp.examples.rpcdemo2"})
@EnableConfigurationProperties(DhpProperties.class)
@EnableDhpRpcServer
@EnableScheduling
public class RpcServerDemo2Application implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RpcServerDemo2Application.class).web(WebApplicationType.NONE).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.in.read();
    }
}
