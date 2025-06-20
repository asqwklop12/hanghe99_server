package kr.hhplus.be.server.restaurant.application.service.query;

import java.util.List;
import kr.hhplus.be.server.dining.shared.SearchEvent;
import kr.hhplus.be.server.restaurant.application.port.in.usecase.query.SearchRestaurantUseCase;
import kr.hhplus.be.server.restaurant.application.port.out.repository.query.SearchRestaurantRepository;
import kr.hhplus.be.server.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.restaurant.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchRestaurantService implements SearchRestaurantUseCase {
  private final SearchRestaurantRepository searchRestaurantRepository;
  private final ApplicationEventPublisher eventPublisher;

  @Override
  public List<Restaurant> execute(RestaurantCriteria criteria) {
    // 이벤트 리스너로 넘긴다.
    eventPublisher.publishEvent(new SearchEvent(criteria.query()));
    return searchRestaurantRepository.execute(criteria);
  }
}
