package kr.hhplus.be.server.restaurant.adapter.out.client.naver;

import java.util.List;
import kr.hhplus.be.server.restaurant.application.port.out.client.LocalApiClient;
import kr.hhplus.be.server.restaurant.application.port.out.repository.query.SearchRestaurantRepository;
import kr.hhplus.be.server.restaurant.criteria.RestaurantCriteria;
import kr.hhplus.be.server.restaurant.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NaverLocalApiClient implements SearchRestaurantRepository {
  private final LocalApiClient localApiClient;

  @Override
  public List<Restaurant> execute(RestaurantCriteria criteria) {
    return localApiClient.execute(criteria);
  }
}
