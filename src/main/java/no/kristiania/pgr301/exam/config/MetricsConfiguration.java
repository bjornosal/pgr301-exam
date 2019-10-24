package no.kristiania.pgr301.exam.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MetricsConfiguration {

  @Bean
  @Profile("prod")
  public MeterRegistry productionMeterRegistry() {
    // TODO: 20.10.2019
    //  Sett noe standard config på denne?
    return new SimpleMeterRegistry();
  }
}
