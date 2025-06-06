package kr.hhplus.be.server.dining.keyword.application.port.out.repository;

import kr.hhplus.be.server.dining.keyword.model.KeywordBackup;

public interface UpsertKeywordBackupRepository {
  KeywordBackup save(String keyword);
}
