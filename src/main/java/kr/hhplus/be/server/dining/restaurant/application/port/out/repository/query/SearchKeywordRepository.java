package kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query;

import java.util.List;

public interface SearchKeywordRepository {
  void saveKeyword(String keyword);
  List<String> searchKeyword();
}
