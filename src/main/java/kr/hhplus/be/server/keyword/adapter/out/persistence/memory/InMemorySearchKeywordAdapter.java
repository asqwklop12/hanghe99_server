package kr.hhplus.be.server.keyword.adapter.out.persistence.memory;

import java.util.List;
import java.util.Map;
import kr.hhplus.be.server.keyword.application.port.out.repository.SearchKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class InMemorySearchKeywordAdapter implements SearchKeywordRepository {
  private final Map<String, Integer> keywordCounts;

  @Override
  public void saveKeyword(String keyword) {
    keywordCounts.merge(keyword, 1, Integer::sum);
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
