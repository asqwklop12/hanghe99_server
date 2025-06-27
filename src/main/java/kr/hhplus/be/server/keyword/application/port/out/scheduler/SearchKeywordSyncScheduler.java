package kr.hhplus.be.server.keyword.application.port.out.scheduler;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kr.hhplus.be.server.keyword.application.port.out.repository.GetKeywordBackupRepository;
import kr.hhplus.be.server.keyword.application.port.out.repository.SearchKeywordRepository;
import kr.hhplus.be.server.keyword.model.KeywordBackup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchKeywordSyncScheduler {
  private final GetKeywordBackupRepository getKeywordBackupRepository;
  private final List<SearchKeywordRepository> searchKeywordRepositories;

  private final RedissonClient redissonClient;

  private static final String LOCK_KEY = "searchKeywordSyncLock";

  @PostConstruct
  public void init() {
    sync();
  }

  @Scheduled(fixedRate = 300000) // 5분 = 300,000ms
  public void sync() {
    RLock lock = redissonClient.getLock(LOCK_KEY);
    boolean isLocked = false;
    try {
      isLocked = lock.tryLock(2, 240, TimeUnit.SECONDS); // 4분 유지
      if (!isLocked) {
        log.warn("Failed to acquire distributed lock for sync");
        return;
      }

      // 기존 sync 로직
      List<KeywordBackup> keywords = getKeywordBackupRepository.findAll();
      for (SearchKeywordRepository repository : searchKeywordRepositories) {
        for (KeywordBackup keyword : keywords) {
          for (int i = 0; i < keyword.count(); i++) {
            repository.saveKeyword(keyword.keyword());
          }
        }
      }
    } catch (Exception e) {
      log.error("Failed to sync keyword data: {}", e.getMessage());
    } finally {
      if (isLocked && lock.isHeldByCurrentThread()) {
        lock.unlock();
      }
    }
  }
}
