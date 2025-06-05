package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.jpa.command;

import java.util.Optional;
import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.entity.backup.KeywordBackupEntity;
import kr.hhplus.be.server.dining.restaurant.model.KeywordBackup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUpsertKeywordBackupRepository extends JpaRepository<KeywordBackupEntity, Long> {

  Optional<KeywordBackupEntity> findByKeyword(String keyword);
}
