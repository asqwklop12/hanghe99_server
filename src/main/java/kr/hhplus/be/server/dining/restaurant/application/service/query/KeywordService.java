package kr.hhplus.be.server.dining.restaurant.application.service.query;

import java.util.Collections;
import java.util.List;
import kr.hhplus.be.server.dining.restaurant.application.port.in.usecase.query.KeywordUseCase;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.SearchKeywordRepository;
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
