package kr.hhplus.be.server.keyword.adapter.out.persistence.memory;

import java.util.List;
import java.util.Map;
import kr.hhplus.be.server.keyword.application.port.out.repository.SearchKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.concurrent.locks.ReentrantLock;

/**
 * In-memory 기반의 검색 키워드 저장소 구현체.
 * <p>
 * 동시성 환경에서 안전하게 키워드 카운트를 증가시키기 위해 ReentrantLock을 사용합니다.
 * 여러 스레드가 동시에 saveKeyword를 호출해도 카운트가 정확히 누적됩니다.
 */
@Component
@Order(2)
@RequiredArgsConstructor
public class InMemorySearchKeywordAdapter implements SearchKeywordRepository {
  private final Map<String, Integer> keywordCounts;
  private final ReentrantLock lock = new ReentrantLock();

  /**
   * 키워드의 검색 카운트를 1 증가시킵니다.
   * <p>
   * 동시성 환경에서 카운트 값이 꼬이지 않도록 lock으로 임계영역을 보호합니다.
   *
   * @param keyword 저장 또는 증가시킬 키워드
   */
  @Override
  public void saveKeyword(String keyword) {
    lock.lock();
    try {
      keywordCounts.merge(keyword, 1, Integer::sum);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public List<String> searchKeyword() {
    return keywordCounts.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
        .map(Map.Entry::getKey)
        .limit(10)
        .toList();
  }
}
