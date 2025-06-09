package kr.hhplus.be.server.restaurant.application.port.out.client;

import java.util.List;
import kr.hhplus.be.server.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.restaurant.model.Restaurant;

public interface LocalApiClient {

  List<Restaurant> execute(RestaurantCriteria criteria);
}
