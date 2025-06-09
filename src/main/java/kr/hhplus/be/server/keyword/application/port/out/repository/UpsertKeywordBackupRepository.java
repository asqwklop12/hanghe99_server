package kr.hhplus.be.server.keyword.application.port.out.repository;

import kr.hhplus.be.server.keyword.model.KeywordBackup;

public interface UpsertKeywordBackupRepository {
  KeywordBackup save(String keyword);
}
