package kr.hhplus.be.server.dining.config.inmemory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryConfig {

  @Bean
  public Map<String, Integer> keywordCounts() {
    return new ConcurrentHashMap<>();
  }
}
