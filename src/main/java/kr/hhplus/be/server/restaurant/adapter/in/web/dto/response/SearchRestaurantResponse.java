package kr.hhplus.be.server.restaurant.adapter.in.web.dto.response;

import java.util.List;
import kr.hhplus.be.server.restaurant.adapter.in.web.dto.response.list.SearchRestaurantList;
import kr.hhplus.be.server.restaurant.model.Pagination;
import kr.hhplus.be.server.restaurant.model.Restaurant;
import lombok.Builder;

@Builder
public record SearchRestaurantResponse(
    List<SearchRestaurantList> items,
    long total,
    long current,
    int page
) {
    public static SearchRestaurantResponse search(Pagination<Restaurant> restaurants) {
        List<SearchRestaurantList> items = restaurants.getContent().stream()
            .map(r -> SearchRestaurantList.builder()
                .id(r.id())
                .title(r.title())
                .link(r.link())
                .build())
            .toList();

        return SearchRestaurantResponse.builder()
            .items(items)
            .total(restaurants.getTotal())
            .current(restaurants.getSize())
            .page(restaurants.getPage())
            .build();
    }
}
