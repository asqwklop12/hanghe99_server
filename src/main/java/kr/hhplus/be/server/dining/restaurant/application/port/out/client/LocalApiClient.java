package kr.hhplus.be.server.dining.restaurant.application.port.out.client;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;

public interface LocalApiClient {

  List<Restaurant> execute(RestaurantCriteria criteria);
}
