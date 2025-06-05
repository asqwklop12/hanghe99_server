package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.redis;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.SearchKeywordRepository;
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

  @Override
  public void saveKeyword(String keyword) {
    redisTemplate.opsForZSet().incrementScore(KEYWORDS_KEY, keyword, 1);
  }

  @Override
  public List<String> searchKeyword() {
    Set<String> keywords = redisTemplate.opsForZSet().reverseRange(KEYWORDS_KEY, 0, 9);
    return keywords != null ? keywords.stream().toList() : Collections.emptyList();
  }
}
