package kr.hhplus.be.server.dining.restaurant.adapter.out.persistence.entity.backup;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import kr.hhplus.be.server.dining.restaurant.model.KeywordBackup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurant_keyword_backup")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class KeywordBackupEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String keyword;

  private Integer count;

  private LocalDateTime createdAt;

  public static KeywordBackupEntity of(String keyword, Integer count) {
    return new KeywordBackupEntity(null, keyword, count, LocalDateTime.now());
  }

  public static KeywordBackupEntity fromDomain(KeywordBackup keywordBackup) {
    return KeywordBackupEntity.builder()
        .id(keywordBackup.id())
        .keyword(keywordBackup.keyword())
        .count(keywordBackup.count())
        .createdAt(keywordBackup.createdAt())
        .build();
  }

  public KeywordBackup toDomain() {
    return KeywordBackup.builder()
        .id(id)
        .keyword(keyword)
        .count(count)
        .createdAt(createdAt)
        .build();
  }

  public void increase() {
    if (count == null) {
      count = 0;
    }
    this.count++;
  }
}
