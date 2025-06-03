package kr.hhplus.be.server.dining.restaurant.model;

import lombok.Builder;

@Builder
public record Restaurant(
    Long id,
    String title,
    String link,
    String description,
    String telephone,
    String address,
    String roadAddress,
    String mapx,
    String mapy
) {


}
