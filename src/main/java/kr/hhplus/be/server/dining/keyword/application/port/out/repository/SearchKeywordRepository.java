package kr.hhplus.be.server.dining.keyword.application.port.out.repository;

import java.util.List;

public interface SearchKeywordRepository {
  void saveKeyword(String keyword);
  List<String> searchKeyword();
}
