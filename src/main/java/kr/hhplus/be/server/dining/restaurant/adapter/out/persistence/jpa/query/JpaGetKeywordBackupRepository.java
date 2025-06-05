package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.jpa.query;

import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.entity.backup.KeywordBackupEntity;
import kr.hhplus.be.server.dining.restaurant.model.KeywordBackup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaGetKeywordBackupRepository extends JpaRepository<KeywordBackupEntity, Long> {
    @Query("SELECT k FROM KeywordBackupEntity k WHERE k.keyword = :keyword")
    KeywordBackup findByKeyword(@Param("keyword") String keyword);
} 
