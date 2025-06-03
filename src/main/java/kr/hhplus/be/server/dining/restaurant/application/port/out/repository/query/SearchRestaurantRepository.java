package kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import org.springframework.data.domain.Pageable;

public interface SearchRestaurantRepository {

  List<Restaurant> execute(Restaurant restaurant, Pageable pageable);
}
