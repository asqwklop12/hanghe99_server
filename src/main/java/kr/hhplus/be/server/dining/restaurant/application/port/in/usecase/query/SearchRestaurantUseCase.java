package kr.hhplus.be.server.dining.restaurant.application.port.in.usecase.query;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;

public interface SearchRestaurantUseCase {
  List<Restaurant> execute(RestaurantCriteria criteria);
}

