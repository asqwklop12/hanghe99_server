package kr.hhplus.be.server.dining.restaurant.application.port.out.scheduler.fake;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.GetKeywordBackupRepository;
import kr.hhplus.be.server.dining.restaurant.model.KeywordBackup;

public class FakeGetKeywordBackupRepository implements GetKeywordBackupRepository {
  private final Map<String, KeywordBackup> store = new ConcurrentHashMap<>();

  public void save(KeywordBackup keywordBackup) {
    store.put(keywordBackup.keyword(), keywordBackup);
  }

  @Override
  public List<KeywordBackup> findAll() {
    return List.copyOf(store.values());
  }
}
