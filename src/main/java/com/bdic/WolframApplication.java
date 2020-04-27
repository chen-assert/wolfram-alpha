package com.bdic;

import com.wolfram.alpha.WAException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@SpringBootApplication
public class WolframApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(WolframApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        Resource resource = new ClassPathResource("/application.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        System.out.println("Hello World from Application Runner");
        System.out.println("Application running in http://localhost:" + props.getProperty("server.port"));
        System.out.println("Page in http://localhost:" + props.getProperty("server.port") + "/wolfram");

        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        String version;
        if (isWindows) {
            version = Helper.SystemCommand.runCommand("ver", null, 5);
        } else {
            version = Helper.SystemCommand.runCommand("uname -a", null, 5);
        }
        System.out.println(new WAException("1"));
        System.out.println(version);

    }

}