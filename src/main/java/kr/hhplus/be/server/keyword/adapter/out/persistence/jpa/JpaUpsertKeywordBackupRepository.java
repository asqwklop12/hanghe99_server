package kr.hhplus.be.server.keyword.adapter.out.persistence.jpa;

import java.util.Optional;
import kr.hhplus.be.server.keyword.adapter.out.persistence.entity.KeywordBackupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUpsertKeywordBackupRepository extends JpaRepository<KeywordBackupEntity, Long> {

  Optional<KeywordBackupEntity> findByKeyword(String keyword);
}
