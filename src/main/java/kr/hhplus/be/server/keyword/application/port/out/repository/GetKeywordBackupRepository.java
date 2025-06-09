package kr.hhplus.be.server.keyword.application.port.out.repository;

import java.util.List;
import kr.hhplus.be.server.keyword.model.KeywordBackup;

public interface GetKeywordBackupRepository {
  List<KeywordBackup> findAll();
}
