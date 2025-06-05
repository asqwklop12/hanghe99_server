package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.springdata.command;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.entity.backup.KeywordBackupEntity;
import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.jpa.command.JpaUpsertKeywordBackupRepository;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.command.UpsertKeywordBackupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpringDataUpsertKeywordBackupRepository implements UpsertKeywordBackupRepository {
  private final JpaUpsertKeywordBackupRepository repository;

  @Override
  @Transactional
  public void save(String keyword) {

    KeywordBackupEntity entity = repository.findByKeyword(keyword)
        .orElse(KeywordBackupEntity.builder().keyword(keyword).build());

    entity.increase();

    repository.save(entity).toDomain();
  }
}
