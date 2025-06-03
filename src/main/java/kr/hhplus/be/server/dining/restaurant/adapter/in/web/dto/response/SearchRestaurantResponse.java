package kr.hhplus.be.server.dining.restaurant.adapter.in.web.dto.response;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.adapter.in.web.dto.response.list.SearchRestaurantList;
import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import lombok.Builder;

@Builder
public record SearchRestaurantResponse(
    List<SearchRestaurantList> items,
    long total,
    long current,
    int page
) {
  public static SearchRestaurantResponse search(List<Restaurant> restaurants) {

    return SearchRestaurantResponse.builder()
        .items(restaurants.stream().map(r -> SearchRestaurantList.builder()
            .id(r.id())
            .title(r.title())
            .link(r.link())
            .build()).toList())
        .total(5)
        .current(1)
        .page(1)
        .build();
  }
}
