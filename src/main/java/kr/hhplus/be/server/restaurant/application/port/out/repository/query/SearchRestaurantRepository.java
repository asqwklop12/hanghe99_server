package kr.hhplus.be.server.restaurant.application.port.out.repository.query;

import java.util.List;
import kr.hhplus.be.server.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.restaurant.model.Restaurant;

public interface SearchRestaurantRepository {

  List<Restaurant> execute(RestaurantCriteria criteria);
}
