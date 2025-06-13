package kr.hhplus.be.server.restaurant.application.port.in.usecase.query;

import kr.hhplus.be.server.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.restaurant.model.Pagination;
import kr.hhplus.be.server.restaurant.model.Restaurant;

public interface SearchRestaurantUseCase {
  Pagination<Restaurant> execute(RestaurantCriteria criteria);
}

