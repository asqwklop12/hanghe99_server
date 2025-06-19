package kr.hhplus.be.server.keyword.adapter.out.persistence.springdata;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import kr.hhplus.be.server.keyword.adapter.out.persistence.entity.KeywordBackupEntity;
import kr.hhplus.be.server.keyword.adapter.out.persistence.jpa.JpaUpsertKeywordBackupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SpringDataUpsertKeywordBackupRepositoryTest {

  JpaUpsertKeywordBackupRepository jpaRepository;
  SpringDataUpsertKeywordBackupRepository springDataUpsertKeywordBackupRepository;

  @BeforeEach
  void setUp() {
    jpaRepository = Mockito.spy(JpaUpsertKeywordBackupRepository.class);
    springDataUpsertKeywordBackupRepository = new SpringDataUpsertKeywordBackupRepository(jpaRepository);

    Mockito.when(jpaRepository.findByKeyword(any())).thenReturn(Optional.of(new KeywordBackupEntity()));
    Mockito.when(jpaRepository.save(any())).thenReturn(new KeywordBackupEntity());
  }

  @Test
  @DisplayName("여러 스레드가 동시에 save(카운트 증가) 요청 시 동시성 이슈 발생 여부 테스트")
  void concurrentSaveKeyword_rdb() throws InterruptedException {
    // given
    String keyword = "동시성맛집";
    jpaRepository.save(KeywordBackupEntity.builder().keyword(keyword).count(0).build());

    int threadCount = 20;
    int repeat = 100;
    CountDownLatch latch = new CountDownLatch(threadCount);

    Runnable task = () -> {
      for (int i = 0; i < repeat; i++) {
        springDataUpsertKeywordBackupRepository.save(keyword);
      }
      latch.countDown();
    };

    for (int i = 0; i < threadCount; i++) {
      new Thread(task).start();
    }
    latch.await();

    KeywordBackupEntity result = jpaRepository.findByKeyword(keyword).orElseThrow();
    assertThat(result.getCount()).isEqualTo(threadCount * repeat);
  }
}
