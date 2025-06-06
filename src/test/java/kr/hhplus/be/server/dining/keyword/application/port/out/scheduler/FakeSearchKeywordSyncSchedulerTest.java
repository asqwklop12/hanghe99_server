package kr.hhplus.be.server.dining.keyword.application.port.out.scheduler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static reactor.core.publisher.Mono.when;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kr.hhplus.be.server.dining.keyword.adapter.out.persistence.memory.InMemorySearchKeywordAdapter;
import kr.hhplus.be.server.dining.keyword.adapter.out.persistence.redis.RedisSearchKeywordAdapter;
import kr.hhplus.be.server.dining.keyword.persistence.redis.fake.FakeRedisTemplate;
import kr.hhplus.be.server.dining.keyword.application.port.out.repository.GetKeywordBackupRepository;
import kr.hhplus.be.server.dining.keyword.application.port.out.repository.SearchKeywordRepository;
import kr.hhplus.be.server.dining.keyword.application.port.out.scheduler.fake.FakeGetKeywordBackupRepository;
import kr.hhplus.be.server.dining.keyword.application.port.out.scheduler.fake.FakeSearchKeywordSyncScheduler;
import kr.hhplus.be.server.dining.keyword.model.KeywordBackup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;

class FakeSearchKeywordSyncSchedulerTest {

  private GetKeywordBackupRepository queryRepository;
  private RedisTemplate<String, String> redisTemplate;
  private Map<String, Integer> keywordCounts;
  private FakeSearchKeywordSyncScheduler scheduler;

  @BeforeEach
  void setUp() {
    queryRepository = new FakeGetKeywordBackupRepository();
    redisTemplate = new FakeRedisTemplate();
    keywordCounts = new ConcurrentHashMap<>();

    List<SearchKeywordRepository> repositories = List.of(
        new RedisSearchKeywordAdapter(redisTemplate),
        new InMemorySearchKeywordAdapter(keywordCounts)
    );

    scheduler = new FakeSearchKeywordSyncScheduler(queryRepository, repositories);
  }

  @Test
  @DisplayName("DB의 키워드 데이터를 InMemory에 동기화한다")
  void syncInMemory() {
    // given
    ((FakeGetKeywordBackupRepository) queryRepository).save(
        KeywordBackup.builder()
            .keyword("맛집")
            .count(3)
            .build()
    );

    List<SearchKeywordRepository> repositories = List.of(
        new InMemorySearchKeywordAdapter(keywordCounts)
    );

    scheduler = new FakeSearchKeywordSyncScheduler(queryRepository, repositories);

    // when
    scheduler.sync();

    // then
    assertThat(keywordCounts.get("맛집")).isEqualTo(3);
  }

  @Test
  @DisplayName("DB의 키워드 데이터를 Redis에 동기화한다")
  void syncRedis() {
    // given
    ((FakeGetKeywordBackupRepository) queryRepository).save(
        KeywordBackup.builder()
            .keyword("맛집")
            .count(3)
            .build()
    );

    // when
    scheduler.sync();

    // then
    assertThat(redisTemplate.opsForZSet().score("keywords","맛집")).isEqualTo(3.0);
  }

  @Test
  @DisplayName("여러 키워드를 동기화한다")
  void syncMultipleKeywords() {
    // given
    ((FakeGetKeywordBackupRepository) queryRepository).save(
        KeywordBackup.builder()
            .keyword("맛집")
            .count(2)
            .build()
    );
    ((FakeGetKeywordBackupRepository) queryRepository).save(
        KeywordBackup.builder()
            .keyword("카페")
            .count(3)
            .build()
    );

    // when
    scheduler.sync();

    // then
    assertThat(redisTemplate.opsForZSet().score("keywords","맛집")).isEqualTo(2.0);
    assertThat(redisTemplate.opsForZSet().score("keywords","카페")).isEqualTo(3.0);
  }


} 
