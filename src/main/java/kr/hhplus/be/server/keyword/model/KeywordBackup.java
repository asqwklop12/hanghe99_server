package kr.hhplus.be.server.keyword.model;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record KeywordBackup(
    Long id,
    String keyword,
    Integer count,
    LocalDateTime createdAt
) {

  public static KeywordBackup of(String keyword, Integer count) {
    return KeywordBackup.builder()
        .keyword(keyword)
        .count(count)
        .createdAt(LocalDateTime.now())
        .build();
  }
}
