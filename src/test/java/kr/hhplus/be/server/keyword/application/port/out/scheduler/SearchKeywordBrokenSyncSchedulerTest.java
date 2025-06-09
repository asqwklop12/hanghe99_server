package kr.hhplus.be.server.keyword.application.port.out.scheduler;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kr.hhplus.be.server.keyword.adapter.out.persistence.memory.InMemorySearchKeywordAdapter;
import kr.hhplus.be.server.keyword.adapter.out.persistence.redis.RedisSearchKeywordAdapter;
import kr.hhplus.be.server.keyword.application.port.out.repository.GetKeywordBackupRepository;
import kr.hhplus.be.server.keyword.application.port.out.repository.SearchKeywordRepository;
import kr.hhplus.be.server.keyword.application.port.out.scheduler.fake.FakeGetKeywordBackupRepository;
import kr.hhplus.be.server.keyword.application.port.out.scheduler.fake.FakeSearchKeywordSyncScheduler;
import kr.hhplus.be.server.keyword.model.KeywordBackup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

@ExtendWith(MockitoExtension.class)
public class SearchKeywordBrokenSyncSchedulerTest {

  private GetKeywordBackupRepository queryRepository;
  @Mock
  private RedisTemplate<String, String> mockRedisTemplate;


  @Mock
  private ZSetOperations<String, String> mockZSetOperations;

  private Map<String, Integer> mockKeywordCounts;
  private FakeSearchKeywordSyncScheduler scheduler;

  @BeforeEach
  void setUp() {
    queryRepository = new FakeGetKeywordBackupRepository();
    mockKeywordCounts = new ConcurrentHashMap<>();

    when(mockRedisTemplate.opsForZSet()).thenReturn(mockZSetOperations);

    List<SearchKeywordRepository> repositories = List.of(
        new RedisSearchKeywordAdapter(mockRedisTemplate),
        new InMemorySearchKeywordAdapter(mockKeywordCounts)
    );

    scheduler = new FakeSearchKeywordSyncScheduler(queryRepository, repositories);
  }

  @Test
  @DisplayName("Redis 연동 중 예외가 발생하면 InMemory로 폴백되어 동기화가 수행된다")
  void syncWithRedisError() {
    // given
    ((FakeGetKeywordBackupRepository) queryRepository).save(
        KeywordBackup.builder()
            .keyword("맛집")
            .count(2)
            .build()
    );

    // Redis 저장 시도 시 예외 발생
    doThrow(new RuntimeException("Redis 저장 실패"))
        .when(mockZSetOperations)
        .incrementScore(anyString(), anyString(), any(Double.class));

    // when
    scheduler.sync();

    // then
    // 1. Redis 저장 시도가 있었는지 확인
    verify(mockZSetOperations).incrementScore(anyString(), anyString(), any(Double.class));

    // 2. InMemory에 정상적으로 저장되었는지 확인
    assertThat(mockKeywordCounts.get("맛집")).isEqualTo(2);
  }


}
