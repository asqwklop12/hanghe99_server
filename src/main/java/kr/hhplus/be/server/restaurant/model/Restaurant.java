package kr.hhplus.be.server.restaurant.model;

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
