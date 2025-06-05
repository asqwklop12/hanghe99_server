package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.event.fake.FakeZSetOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

@ExtendWith(MockitoExtension.class)
class RedisSearchKeywordAdapterTest {

  @Mock
  RedisTemplate<String, String> fakeRedisTemplate;

  @InjectMocks
  private RedisSearchKeywordAdapter adapter;

  private FakeZSetOperations fakeZSetOps;


  @BeforeEach
  void setUp() {
    fakeZSetOps = new FakeZSetOperations();
    when(fakeRedisTemplate.opsForZSet()).thenReturn(fakeZSetOps);
  }

  @Test
  @DisplayName("검색어가 정상적으로 저장되어야 한다")
  void saveKeywordTest() {
    //given

    //when
    RedisSearchKeywordAdapter adapter = new RedisSearchKeywordAdapter(fakeRedisTemplate);

    String keyword = "맛집";
    adapter.saveKeyword(keyword);

    //then
    assertThat(fakeZSetOps.score("keywords", keyword)).isEqualTo(1.0);
  }

  @Test
  @DisplayName("동일한 검색어가 여러 번 저장되면 빈도가 증가해야 한다")
  void saveKeywordMultipleTimesTest() {
    // given
    String keyword = "맛집";

    // when
    adapter.saveKeyword(keyword);
    adapter.saveKeyword(keyword);
    adapter.saveKeyword(keyword);

    // then
    Double score = fakeRedisTemplate.opsForZSet().score("keywords", keyword);
    assertThat(score).isEqualTo(3.0);
  }

  @Test
  @DisplayName("Redis 실패 시 로컬 캐시에 저장되어야 한다")
  void localCacheTest() {
    // given
    String keyword = "맛집";

    // Redis 실패 상황 만들기
    when(fakeRedisTemplate.opsForZSet().score(keyword, keyword))
        .thenThrow(new RuntimeException("Redis 연결 실패"));

    // when
    adapter.saveKeyword(keyword);

    // then
    assertThat(adapter.getLocalCacheCount(keyword)).isEqualTo(1);
    assertThat(adapter.isInLocalCache(keyword)).isTrue();
    assertThat(adapter.getLocalCacheSize()).isEqualTo(1);
  }

}
