package kr.hhplus.be.server.keyword.adapter.out.persistence.redis;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kr.hhplus.be.server.keyword.application.port.out.repository.SearchKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
@Order(1)
@RequiredArgsConstructor
public class RedisSearchKeywordAdapter implements SearchKeywordRepository {
  private final RedisTemplate<String, String> redisTemplate;
  private static final String KEYWORDS_KEY = "keywords";
  private static final long TTL_MINUTE = 5;  // 5분

  @Override
  public void saveKeyword(String keyword) {
    redisTemplate.opsForZSet().incrementScore(KEYWORDS_KEY, keyword, 1);
    redisTemplate.expire(KEYWORDS_KEY, TTL_MINUTE, TimeUnit.MINUTES);
  }

  @Override
  public List<String> searchKeyword() {
    Set<String> keywords = redisTemplate.opsForZSet().reverseRange(KEYWORDS_KEY, 0, 9);
    return keywords != null ? keywords.stream().toList() : Collections.emptyList();
  }
}
