package kr.hhplus.be.server.restaurant.application.port.in.usecase.query;

import java.util.List;
import kr.hhplus.be.server.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.restaurant.model.Restaurant;

public interface SearchRestaurantUseCase {
  List<Restaurant> execute(RestaurantCriteria criteria);
}

