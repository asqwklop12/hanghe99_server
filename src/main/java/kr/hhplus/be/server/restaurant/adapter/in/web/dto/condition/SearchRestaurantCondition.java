package kr.hhplus.be.server.restaurant.adapter.in.web.dto.condition;

import kr.hhplus.be.server.restaurant.criteria.RestaurantCriteria;
import lombok.Builder;

@Builder
public record SearchRestaurantCondition(
    String query,
    int page,
    int display,
    String sort
) {

  public RestaurantCriteria toCriteria() {
    return RestaurantCriteria.builder()
        .query(query)
        .start(page)
        .display(display)
        .sort(sort)
        .build();
  }
}
