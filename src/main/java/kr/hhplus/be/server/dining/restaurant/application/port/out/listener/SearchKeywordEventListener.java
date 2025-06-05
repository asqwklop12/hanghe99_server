package kr.hhplus.be.server.dining.restaurant.application.port.out.listener;

import kr.hhplus.be.server.dining.shared.SearchEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchKeywordEventListener {
  private final SearchKeywordPort searchKeywordPort;

  @Async
  @EventListener
  public void handleSearchEvent(SearchEvent event) {
    searchKeywordPort.saveKeyword(event.keyword());
  }
}
