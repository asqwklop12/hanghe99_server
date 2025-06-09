package kr.hhplus.be.server.keyword.adapter.out.persistence.jpa;

import kr.hhplus.be.server.keyword.adapter.out.persistence.entity.KeywordBackupEntity;
import kr.hhplus.be.server.keyword.model.KeywordBackup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaGetKeywordBackupRepository extends JpaRepository<KeywordBackupEntity, Long> {
    @Query("SELECT k FROM KeywordBackupEntity k WHERE k.keyword = :keyword")
    KeywordBackup findByKeyword(@Param("keyword") String keyword);
} 
