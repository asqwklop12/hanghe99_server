package kr.hhplus.be.server.keyword.application.port.out.listener;

import java.util.List;
import kr.hhplus.be.server.dining.shared.SearchEvent;
import kr.hhplus.be.server.keyword.application.port.out.repository.SearchKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchKeywordEventListener {
  private final List<SearchKeywordRepository> searchKeywordRepositories;

  @Async
  @EventListener
  public void handleSearchEvent(SearchEvent event) {
    for (SearchKeywordRepository repository : searchKeywordRepositories) {
      try {
        repository.saveKeyword(event.keyword());
      } catch (Exception ignored) {
      }
    }
  }
}
