package kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query;

import kr.hhplus.be.server.dining.restaurant.model.KeywordBackup;
import org.springframework.data.repository.query.Param;

public interface GetKeywordBackupRepository {
  KeywordBackup findByKeyword(@Param("keyword") String keyword);
}
