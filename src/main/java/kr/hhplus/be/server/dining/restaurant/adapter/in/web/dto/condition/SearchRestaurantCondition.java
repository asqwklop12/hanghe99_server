package kr.hhplus.be.server.dining.restaurant.adapter.in.web.dto.condition;

import kr.hhplus.be.server.dining.restaurant.model.Restaurant;
import lombok.Builder;

@Builder
public record SearchRestaurantCondition(
    Long id,
    String title,
    String address,
    String roadAddress,
    int page,
    int display
) {
  public Restaurant toModel() {
    return Restaurant.builder()
        .id(id)
        .title(title)
        .address(address)
        .roadAddress(roadAddress)
        .build();
  }
}
