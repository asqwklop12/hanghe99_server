package kr.hhplus.be.server.dining.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
  @Value("${client.naver.X-Naver-Client-Id}")
  private String X_NAVER_CLIENT_ID;

  @Value("${client.naver.X-Naver-Client-Secret}")
  private String X_NAVER_CLIENT_SECRET;

  private WebClient createWebClient(String baseUrl) {
    return WebClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader("X-Naver-Client-Id", X_NAVER_CLIENT_ID)
        .defaultHeader("X-Naver-Client-Secret", X_NAVER_CLIENT_SECRET)
        .build();
  }

  @Bean
  public WebClient naverWebClient(@Value("${client.naver.url}") String url) {
    return createWebClient(url);
  }
}
