package kr.hhplus.be.server.dining.restaurant.application.port.out.repository.command;

import kr.hhplus.be.server.dining.restaurant.model.KeywordBackup;

public interface UpsertKeywordBackupRepository {
  KeywordBackup save(String keyword);
}
