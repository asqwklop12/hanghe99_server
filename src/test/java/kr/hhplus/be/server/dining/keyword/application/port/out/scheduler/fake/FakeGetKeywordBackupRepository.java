package kr.hhplus.be.server.dining.keyword.application.port.out.scheduler.fake;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kr.hhplus.be.server.dining.keyword.application.port.out.repository.GetKeywordBackupRepository;
import kr.hhplus.be.server.dining.keyword.model.KeywordBackup;

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
