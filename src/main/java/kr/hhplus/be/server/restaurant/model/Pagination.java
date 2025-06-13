package kr.hhplus.be.server.restaurant.model;

import java.util.List;
import lombok.Getter;

@Getter
public class Pagination<T> {
  private final List<T> content;
  private final int page;
  private final int size;
  private final long total;
  private final int totalPages;

  private Pagination(List<T> content, int page, int size, long total) {
    this.content = content;
    this.page = page;
    this.size = size;
    this.total = total;
    this.totalPages = (int) Math.ceil((double) total / page);
  }

  public static <T> Pagination<T> of(List<T> content, int page, int size, Long total) {
    return new Pagination<>(content,page, size,  total);
  }

}
