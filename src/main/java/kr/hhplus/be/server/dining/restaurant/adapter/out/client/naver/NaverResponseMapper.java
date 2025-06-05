package kr.hhplus.be.server.dining.restaurant.adapter.out.client.naver;

import java.util.List;
import java.util.stream.Collectors;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class NaverResponseMapper {
  public List<Restaurant> toRestaurants(NaverSearchResponse response) {
    return response.items().stream()
        .map(this::toRestaurant)
        .collect(Collectors.toList());
  }

  private Restaurant toRestaurant(NaverSearchItem item) {
    return Restaurant.builder()
        .title(item.title())
        .link(item.link())
        .address(item.address())
        .roadAddress(item.roadAddress())
        .build();
  }
}
