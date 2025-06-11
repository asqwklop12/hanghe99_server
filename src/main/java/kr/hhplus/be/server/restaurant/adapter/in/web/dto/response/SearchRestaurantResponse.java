package kr.hhplus.be.server.restaurant.adapter.in.web.dto.response;

import java.util.List;
import kr.hhplus.be.server.restaurant.adapter.in.web.dto.response.list.SearchRestaurantList;
import kr.hhplus.be.server.restaurant.model.Restaurant;
import lombok.Builder;

@Builder
public record SearchRestaurantResponse(
    List<SearchRestaurantList> items,
    long total,
    long current,
    int page
) {
    public static SearchRestaurantResponse search(List<Restaurant> restaurants) {
        List<SearchRestaurantList> items = restaurants.stream()
            .map(r -> SearchRestaurantList.builder()
                .id(r.id())
                .title(r.title())
                .link(r.link())
                .build())
            .toList();

        return SearchRestaurantResponse.builder()
            .items(items)
            .total(items.size())
            .current(1)
            .page(1)
            .build();
    }
}
