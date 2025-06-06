package kr.hhplus.be.server.dining.keyword.adapter.out.persistence.springdata;

import java.util.List;
import java.util.stream.Collectors;
import kr.hhplus.be.server.dining.keyword.adapter.out.persistence.jpa.JpaGetKeywordBackupRepository;
import kr.hhplus.be.server.dining.keyword.application.port.out.repository.GetKeywordBackupRepository;
import kr.hhplus.be.server.dining.keyword.model.KeywordBackup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpingDataGetGetKeywordBackupRepository implements GetKeywordBackupRepository {
  private final JpaGetKeywordBackupRepository repository;

  @Override
  public List<KeywordBackup> findAll() {
    return repository.findAll().stream().map(
        k -> KeywordBackup.builder()
            .id(k.getId())
            .keyword(k.getKeyword())
            .count(k.getCount())
            .createdAt(k.getCreatedAt())
            .build()
    ).collect(Collectors.toList());
  }
}
