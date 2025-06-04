package kr.hhplus.be.server.dining.restaurant.adapter.out.client.naver;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.application.port.out.client.LocalApiClient;
import kr.hhplus.be.server.dining.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;

public class NaverLocalApiClient implements LocalApiClient {
  @Override
  public List<Restaurant> getRestaurants(RestaurantCriteria criteria) {
    return List.of();
  }
}
