package atlas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.metrics.atlas.EnableAtlas;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

import client.AtlasClientConfiguration;

import com.netflix.servo.publish.MetricPoller;
import com.netflix.servo.publish.MonitorRegistryMetricPoller;

/**
 * @author Jon Schneider
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "atlas", configuration = AtlasClientConfiguration.class)
@EnableAtlas
public class AtlasApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(AtlasApplication.class, args);
	}

	// TODO can be removed once https://github.com/spring-cloud/spring-cloud-netflix/pull/724 is merged
	@Bean
	MetricPoller poller() {
		return new MonitorRegistryMetricPoller();
	}
}
