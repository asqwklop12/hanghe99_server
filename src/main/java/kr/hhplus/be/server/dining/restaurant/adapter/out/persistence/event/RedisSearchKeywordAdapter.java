package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kr.hhplus.be.server.dining.restaurant.application.port.out.listener.SearchKeywordPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisSearchKeywordAdapter implements SearchKeywordPort {
  private final RedisTemplate<String, String> redisTemplate;
  private final Map<String, Integer> localCache = new ConcurrentHashMap<>();

  @Override
  public void saveKeyword(String keyword) {
    try {
      redisTemplate.opsForZSet().incrementScore("keywords", keyword, 1);
    } catch (Exception e) {
      // Redis 실패 시 로컬 캐시 사용
      localCache.merge(keyword, 1, Integer::sum);
    }
  }

  protected Integer getLocalCacheCount(String keyword) {
    return localCache.get(keyword);
  }

  protected boolean isInLocalCache(String keyword) {
    return localCache.containsKey(keyword);
  }

  protected int getLocalCacheSize() {
    return localCache.size();
  }

}
