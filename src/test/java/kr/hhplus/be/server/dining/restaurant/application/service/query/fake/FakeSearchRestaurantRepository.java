package kr.hhplus.be.server.dining.restaurant.application.service.query.fake;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.SearchRestaurantRepository;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import org.springframework.data.domain.Pageable;

public class FakeSearchRestaurantRepository implements SearchRestaurantRepository {

  private final List<Restaurant> restaurants = List.of(
      Restaurant.builder()
          .id(1L)
          .title("title1")
          .address("address1")
          .link("link1")
          .build(),
      Restaurant.builder()
          .id(2L)
          .title("title2")
          .address("address2")
          .link("link2")
          .build()
  );

  @Override
  public List<Restaurant> execute(Restaurant restaurant, Pageable pageable) {
    int start = (int) pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), restaurants.size());
    return restaurants.subList(start, end);
  }
}
