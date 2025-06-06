package kr.hhplus.be.server.dining.keyword.application.service;

import java.util.Collections;
import java.util.List;
import kr.hhplus.be.server.dining.keyword.application.port.in.usecase.KeywordUseCase;
import kr.hhplus.be.server.dining.keyword.application.port.out.repository.SearchKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordService implements KeywordUseCase {

  private final List<SearchKeywordRepository> searchKeywordRepositories;

  @Override
  public List<String> execute() {
    for (SearchKeywordRepository searchKeywordRepository : searchKeywordRepositories) {
      try {
        return searchKeywordRepository.searchKeyword();
      } catch (Exception ignored) {}
    }
    return Collections.emptyList();
  }
}
