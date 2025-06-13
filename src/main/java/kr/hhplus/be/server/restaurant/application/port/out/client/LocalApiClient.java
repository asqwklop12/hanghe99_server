package kr.hhplus.be.server.restaurant.application.port.out.client;

import kr.hhplus.be.server.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.restaurant.model.Pagination;
import kr.hhplus.be.server.restaurant.model.Restaurant;

public interface LocalApiClient {

  Pagination<Restaurant> execute(RestaurantCriteria criteria);
}
