package kr.hhplus.be.server.dining.restaurant.adapter.out.client.naver;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.SearchRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class NaverLocalApiClient implements SearchRestaurantRepository {
  @Override
  public List<Restaurant> execute(RestaurantCriteria criteria) {
    return List.of();
  }
}
