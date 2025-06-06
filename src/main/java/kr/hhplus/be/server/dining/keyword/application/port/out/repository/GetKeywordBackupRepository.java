package kr.hhplus.be.server.dining.keyword.application.port.out.repository;

import java.util.List;
import kr.hhplus.be.server.dining.keyword.model.KeywordBackup;

public interface GetKeywordBackupRepository {
  List<KeywordBackup> findAll();
}
