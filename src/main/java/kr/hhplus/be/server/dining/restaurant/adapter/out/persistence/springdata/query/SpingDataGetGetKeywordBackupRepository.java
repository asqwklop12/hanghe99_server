package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.springdata.query;

import java.util.List;
import java.util.stream.Collectors;
import kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.jpa.query.JpaGetKeywordBackupRepository;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.GetKeywordBackupRepository;
import kr.hhplus.be.server.dining.restaurant.model.KeywordBackup;
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
