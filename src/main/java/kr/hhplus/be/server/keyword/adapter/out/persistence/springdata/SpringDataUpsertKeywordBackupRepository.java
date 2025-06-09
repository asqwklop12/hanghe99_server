package kr.hhplus.be.server.keyword.adapter.out.persistence.springdata;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.keyword.adapter.out.persistence.entity.KeywordBackupEntity;
import kr.hhplus.be.server.keyword.adapter.out.persistence.jpa.JpaUpsertKeywordBackupRepository;
import kr.hhplus.be.server.keyword.application.port.out.repository.UpsertKeywordBackupRepository;
import kr.hhplus.be.server.keyword.model.KeywordBackup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpringDataUpsertKeywordBackupRepository implements UpsertKeywordBackupRepository {
  private final JpaUpsertKeywordBackupRepository repository;

  @Override
  @Transactional
  public KeywordBackup save(String keyword) {

    KeywordBackupEntity entity = repository.findByKeyword(keyword)
        .orElse(KeywordBackupEntity.builder().keyword(keyword).build());

    entity.increase();

    return repository.save(entity).toDomain();
  }
}
