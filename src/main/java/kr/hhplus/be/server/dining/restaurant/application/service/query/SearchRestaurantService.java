package kr.hhplus.be.server.dining.restaurant.application.service.query;

import java.util.Collections;
import java.util.List;
import kr.hhplus.be.server.dining.restaurant.application.port.in.usecase.query.SearchRestaurantUseCase;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.SearchRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchRestaurantService implements SearchRestaurantUseCase {
  private final SearchRestaurantRepository searchRestaurantRepository;

  @Override
  public List<Restaurant> execute(Restaurant restaurant, Pageable pageable) {
    return searchRestaurantRepository.execute(restaurant, pageable);
  }
}
