package kr.hhplus.be.server.dining.restaurant.criteria;

import lombok.Builder;

@Builder
public record RestaurantCriteria(
    String query,
    int display,
    int start,
    String sort
) {
  
}
