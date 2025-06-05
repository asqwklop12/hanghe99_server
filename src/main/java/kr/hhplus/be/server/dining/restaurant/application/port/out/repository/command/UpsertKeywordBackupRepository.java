package kr.hhplus.be.server.dining.restaurant.application.port.out.repository.command;

public interface UpsertKeywordBackupRepository {
  void save(String keyword);
}
