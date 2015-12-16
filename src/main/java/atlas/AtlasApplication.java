package atlas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.metrics.atlas.EnableAtlas;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import client.AtlasClientConfiguration;

import com.netflix.servo.publish.MetricPoller;
import com.netflix.servo.publish.MonitorRegistryMetricPoller;
import com.netflix.spectator.api.Registry;

/**
 * @author Jon Schneider
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "atlas", configuration = AtlasClientConfiguration.class)
@EnableAtlas
@EnableScheduling
public class AtlasApplication {
    @Autowired
    Registry registry;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AtlasApplication.class, args);
	}

    @Scheduled(fixedRate = 5000)
    public void incrementCounter() {
        registry.counter("mycounter").increment();
    }

	// TODO can be removed once https://github.com/spring-cloud/spring-cloud-netflix/pull/724 is merged
	@Bean
	MetricPoller poller() {
		return new MonitorRegistryMetricPoller();
	}
}
