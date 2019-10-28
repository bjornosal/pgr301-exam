package no.kristiania.pgr301.exam.config;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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

  @Bean
  @ConditionalOnMissingBean
  public InfluxMeterRegistry nonProductionMeterRegistry(InfluxConfig config, Clock clock) {
    return new InfluxMeterRegistry(config, clock);
  }
}
