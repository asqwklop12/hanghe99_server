package kr.hhplus.be.server.dining.restaurant.application.port.in.usecase.query;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import org.springframework.data.domain.Pageable;

public interface SearchRestaurantUseCase {
  List<Restaurant> execute(Restaurant restaurant, Pageable pageable);
}

