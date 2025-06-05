package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.springdata.query;

import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.jpa.query.JpaGetKeywordBackupRepository;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.GetKeywordBackupRepository;
import kr.hhplus.be.server.dining.restaurant.model.KeywordBackup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpingDataGetGetKeywordBackupRepository implements GetKeywordBackupRepository {
  private final JpaGetKeywordBackupRepository repository;

  @Override
  public KeywordBackup findByKeyword(String keyword) {
    return repository.findByKeyword(keyword);
  }
}
