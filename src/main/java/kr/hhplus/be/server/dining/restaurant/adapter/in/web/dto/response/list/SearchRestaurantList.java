package kr.hhplus.be.server.dining.restaurant.adapter.in.web.dto.response.list;

import lombok.Builder;

@Builder
public record SearchRestaurantList(
    Long id,
    String link,
    String title
) {
}
